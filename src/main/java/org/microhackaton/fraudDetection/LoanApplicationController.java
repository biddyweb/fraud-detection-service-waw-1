package org.microhackaton.fraudDetection;

import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoanApplicationController {

    private final FraudDetectionService fraudDetectionService;
    private final Counter goodLoanApplication;
    private final Counter fraudLoanApplication;
    private final Counter fishyLoanApplication;

    @Autowired
    public LoanApplicationController(FraudDetectionService fraudDetectionService, MetricRegistry metricRegistry) {
        this.fraudDetectionService = fraudDetectionService;
        this.goodLoanApplication = metricRegistry.counter("frauddetection.decision.good");
        this.fraudLoanApplication = metricRegistry.counter("frauddetection.decision.fraud");
        this.fishyLoanApplication = metricRegistry.counter("frauddetection.decision.fishy");
    }

    @RequestMapping(value = "/api/loanApplication/{id}", method = RequestMethod.PUT)
        public ResponseEntity verifyLoanApplication(@PathVariable String id, @RequestBody LoanApplication loanApplication){

        String fraudStatus = "good";
        goodLoanApplication.inc();

        FraudVerification fraudVerification = new FraudVerification(
                loanApplication.firstName,
                loanApplication.lastName,
                fraudStatus,
                loanApplication.job,
                loanApplication.amount);

        fraudDetectionService.verifyStatus(id, fraudVerification);
        return ResponseEntity.ok().build();
    }

}
