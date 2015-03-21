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
        fraudDetectionService.verifyStatus(id, loanApplication);
        return ResponseEntity.ok("dupa");
    }

}
