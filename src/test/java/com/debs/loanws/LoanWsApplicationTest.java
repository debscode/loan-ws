package com.debs.loanws;

import com.debscode.loan_ws.CustomerRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.util.ClassUtils;
import org.springframework.ws.client.core.WebServiceTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LoanWsApplicationTest {

    private final Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    @LocalServerPort
    private int port = 0;

    @BeforeEach
    public void init() throws Exception {
        marshaller.setPackagesToScan(ClassUtils.getPackageName(CustomerRequest.class));
        marshaller.afterPropertiesSet();
    }

    @Test
    public void testSendAndReceive() {
        WebServiceTemplate ws = new WebServiceTemplate(marshaller);
        CustomerRequest request = new CustomerRequest();
        request.setAge(20);
        request.setCivilScore(300);
        request.setCustomerName("DEBS");
        request.setEmploymentMode("AUF");
        request.setYearlyIncome(120_000);

        Assertions.assertNotNull(ws.marshalSendAndReceive("http://localhost:" + port + "/ws", request));
    }
}
