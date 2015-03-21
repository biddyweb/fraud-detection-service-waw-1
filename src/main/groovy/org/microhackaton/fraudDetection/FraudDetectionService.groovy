package org.microhackaton.fraudDetection;

import com.netflix.hystrix.HystrixCommand.Setter;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.nurkiewicz.asyncretry.AsyncRetryExecutor;
import com.ofg.infrastructure.web.resttemplate.fluent.ServiceRestClient;
import groovy.lang.Closure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Async
public class FraudDetectionService {

    private final ServiceRestClient serviceRestClient;
    private final AsyncRetryExecutor asyncRetryExecutor;
    private Logger logger = LoggerFactory.getLogger(FraudDetectionService.class);

    @Autowired
    public FraudDetectionService(ServiceRestClient serviceRestClient, AsyncRetryExecutor asyncRetryExecutor) {
        this.serviceRestClient = serviceRestClient;
        this.asyncRetryExecutor = asyncRetryExecutor;
    }

    public void verifyStatus(String id, FraudVerification fraudVerification){
        String result = serviceRestClient.forService("decisionMaker")
                .retryUsing(asyncRetryExecutor.withMaxRetries(5))
                .put()
                .withCircuitBreaker(
                        Setter
                                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("fraudDetection"))
                                .andCommandKey(HystrixCommandKey.Factory.asKey("notifyDecisionMaker"))
                        , { 'dupa' }
                )
                .onUrl("/api/loanApplication_del/$id")
                .body(fraudVerification)
                .withHeaders()
                    .contentTypeJson()
                .andExecuteFor()
                    .anObject()
                    .ofType(String);
        logger.info("result = $result");
    }

}
