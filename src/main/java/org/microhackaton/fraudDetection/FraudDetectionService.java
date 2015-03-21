package org.microhackaton.fraudDetection;

import com.netflix.hystrix.HystrixCommand.Setter;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.nurkiewicz.asyncretry.AsyncRetryExecutor;
import com.ofg.infrastructure.web.resttemplate.fluent.ServiceRestClient;
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

    public void verifyStatus(String id, LoanApplication loanApplication){
        String result = serviceRestClient.forService("dupa")
                .retryUsing(asyncRetryExecutor.withMaxRetries(5))
                .put()
                .withCircuitBreaker(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("dupa")), ResponseFactory.create("dupa"))
                .onUrl("/api/dupa")
                .body(new FraudVerification("a","b","c","d","e"))
                .withHeaders()
                    .contentTypeJson()
                .andExecuteFor()
                    .anObject()
                    .ofType(String.class);
        logger.info("result = " + result);
    }

}
