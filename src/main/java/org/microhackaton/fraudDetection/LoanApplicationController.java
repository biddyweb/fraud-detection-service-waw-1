package org.microhackaton.fraudDetection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoanApplicationController {

    private final FraudDetectionService fraudDetectionService;

    @Autowired
    public LoanApplicationController(FraudDetectionService fraudDetectionService) {
        this.fraudDetectionService = fraudDetectionService;
    }

    @RequestMapping(value = "/api/loanApplication/{id}", method = RequestMethod.PUT)
        public ResponseEntity verifyLoanApplication(@PathVariable String id, @RequestBody LoanApplication loanApplication){

        String fraudStatus = "good";

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
