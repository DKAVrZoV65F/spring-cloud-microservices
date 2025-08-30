package org.spring.depositservice.controller;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.spring.depositservice.config.SpringH2DBConfig;
import org.spring.depositservice.rest.AccountServiceClient;
import org.spring.depositservice.rest.BillServiceClient;
import org.spring.depositservice.service.DepositService;
import org.spring.depositservice.service.DepositServiceTest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
//import org.springframework.context.annotation.Before;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DepositServiceTest.class, SpringH2DBConfig.class})
public class DepositControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private DepositService depositService;

    @MockBean
    private BillServiceClient billServiceClient;

    @MockBean
    private AccountServiceClient accountServiceClient;

    @MockBean
    private RabbitTemplate rabbitTemplate;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
}
