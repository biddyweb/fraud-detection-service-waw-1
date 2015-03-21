package org.microhackaton.fraudDetection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoanApplicationController {

    @RequestMapping(value = "/api/loanApplication/{id}", method = RequestMethod.PUT)
    public ResponseEntity verifyLoanApplication(@PathVariable String id, @RequestBody LoanApplication loanApplication){
        return ResponseEntity.ok("dupa");
    }

}
