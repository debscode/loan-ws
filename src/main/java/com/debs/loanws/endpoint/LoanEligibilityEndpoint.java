package com.debs.loanws.endpoint;

import com.debs.loanws.service.LoanEligibilityService;
import com.debscode.loan_ws.CustomerRequest;
import com.debscode.loan_ws.LoanResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@RequiredArgsConstructor
@Endpoint
public class LoanEligibilityEndpoint {
    private final LoanEligibilityService loanEligibilityService;
    private static final String NAMESPACE = "http://debscode.com/loan-ws";

    @PayloadRoot(namespace = NAMESPACE, localPart = "CustomerRequest")
    @ResponsePayload
    public LoanResponse getLoanStatus(@RequestPayload CustomerRequest request) {
        return loanEligibilityService.checkLoanEligibility(request);
    }
}
