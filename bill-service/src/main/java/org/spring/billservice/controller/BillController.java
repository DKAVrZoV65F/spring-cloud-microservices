package org.spring.billservice.controller;

import org.spring.billservice.controller.DTO.BillRequestDTO;
import org.spring.billservice.controller.DTO.BillResponseDTO;
import org.spring.billservice.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BillController {

    private final BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping("/{billId}")
    private BillResponseDTO getBill(@PathVariable Long billId) {
        return new BillResponseDTO(billService.getBillById(billId));
    }

    @PostMapping("/")
    public Long createBill(@RequestBody BillRequestDTO billRequestDTO) {
        return billService.createBill(
                billRequestDTO.getAccountId(),
                billRequestDTO.getAmount(),
                billRequestDTO.getIsDefault(),
                billRequestDTO.getOverdraftEnabled()
        );
    }

    @PutMapping("/{billId}")
    public BillResponseDTO updateBill(@PathVariable Long billId, @RequestBody BillRequestDTO billRequestDTO) {
        return new BillResponseDTO(billService.updateBill(
                billId,
                billRequestDTO.getAccountId(),
                billRequestDTO.getAmount(),
                billRequestDTO.getIsDefault(),
                billRequestDTO.getOverdraftEnabled()
        ));
    }

    @DeleteMapping("/{billId}")
    public BillResponseDTO deleteBill(@PathVariable Long billId) {
        return new BillResponseDTO(billService.deleteBill(billId));
    }

    @GetMapping("/account/{accountId}")
    public List<BillResponseDTO> getBillsByAccountId(@PathVariable Long accountId) {
        return billService.getBillsByAccountId(accountId)
                .stream()
                .map(BillResponseDTO::new)
                .toList();
    }
}
