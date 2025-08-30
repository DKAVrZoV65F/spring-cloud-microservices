package org.spring.depositservice.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.spring.depositservice.contoller.dto.DepositResponseDTO;
import org.spring.depositservice.exception.DepositServiceException;
import org.spring.depositservice.repository.DepositRepository;
import org.spring.depositservice.rest.AccountResponseDTO;
import org.spring.depositservice.rest.AccountServiceClient;
import org.spring.depositservice.rest.BillResponseDTO;
import org.spring.depositservice.rest.BillServiceClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
public class DepositServiceTest {

    @Mock
    private DepositRepository depositRepository;

    @Mock
    private AccountServiceClient accountServiceClient;

    @Mock
    private BillServiceClient billServiceClient;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private DepositService depositService;

    @Test
    public void depositServiceTest_withBillId() {
        BillResponseDTO billResponseDTO = createBillResponseDTO();
        AccountResponseDTO accountResponseDTO = createAccountResponseDTO();

        Mockito.when(billServiceClient.getBillById(ArgumentMatchers.anyLong())).thenReturn(billResponseDTO);
        Mockito.when(accountServiceClient.getAccountById(ArgumentMatchers.anyLong())).thenReturn(accountResponseDTO);

        DepositResponseDTO deposit = depositService.deposit(null, 1L, BigDecimal.TEN);

        Assertions.assertEquals("ivanov@email.com", deposit.getMail());
    }

    @Test
    public void depositServiceTest_exception() {
        DepositServiceException exception = Assertions.assertThrows(
                DepositServiceException.class,
                () -> depositService.deposit(null, null, BigDecimal.TEN)
        );

        Assertions.assertEquals("Account is null and Bill is null", exception.getMessage());
    }

    private AccountResponseDTO createAccountResponseDTO() {
        AccountResponseDTO accountResponseDTO = new AccountResponseDTO();

        accountResponseDTO.setAccountId(1L);
        accountResponseDTO.setBills(Arrays.asList(1L, 2L, 3L));
        accountResponseDTO.setCreationDate(OffsetDateTime.now());
        accountResponseDTO.setName("Ivanov");
        accountResponseDTO.setEmail("ivanov@email.com");
        accountResponseDTO.setPhone("+1234567890");

        return accountResponseDTO;
    }

    private BillResponseDTO createBillResponseDTO() {
        BillResponseDTO billResponseDTO = new BillResponseDTO();

        billResponseDTO.setBillId(1L);
        billResponseDTO.setAccountId(1L);
        billResponseDTO.setAmount(BigDecimal.TEN);
        billResponseDTO.setCreationDate(OffsetDateTime.now());
        billResponseDTO.setIsDefault(true);
        billResponseDTO.setOverdraftEnabled(true);

        return billResponseDTO;
    }
}
