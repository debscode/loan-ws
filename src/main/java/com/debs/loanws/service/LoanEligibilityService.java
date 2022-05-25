package com.debs.loanws.service;

import com.debscode.loan_ws.CustomerRequest;
import com.debscode.loan_ws.LoanResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanEligibilityService {

    public LoanResponse checkLoanEligibility(CustomerRequest request) {
        LoanResponse loanResponse = new LoanResponse();
        List<String> mismatchCriteriaList = loanResponse.getCriteriaMismatch();

        if (!(request.getAge() > 30 && request.getAge() <= 60)) {
            mismatchCriteriaList.add("Person age should in between 30 to 60");
        }
        if (!(request.getYearlyIncome() > 200000)) {
            mismatchCriteriaList.add("minimum income should be more than 200000");
        }
        if (!(request.getCivilScore() > 500)) {
            mismatchCriteriaList.add("Low CIBIL Score please try after 6 month");
        }

        if (mismatchCriteriaList.size() > 0) {
            loanResponse.setApprovedAmount(0);
            loanResponse.setIsEligible(false);
        } else {
            loanResponse.setApprovedAmount(500000);
            loanResponse.setIsEligible(true);
            mismatchCriteriaList.clear();
        }
        return loanResponse;
    }
}
