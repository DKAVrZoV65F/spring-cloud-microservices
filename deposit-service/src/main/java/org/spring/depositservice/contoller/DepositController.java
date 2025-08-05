package org.spring.depositservice.contoller;

import org.spring.depositservice.contoller.dto.DepositRequestDTO;
import org.spring.depositservice.contoller.dto.DepositResponseDTO;
import org.spring.depositservice.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepositController {

    private final DepositService depositService;

    @Autowired
    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    @PostMapping("/")
    public DepositResponseDTO deposit(@RequestBody DepositRequestDTO depositRequestDTO) {
        return depositService.deposit(
                depositRequestDTO.getAccountId(),
                depositRequestDTO.getBillId(),
                depositRequestDTO.getAmount()
         );
    }
}
