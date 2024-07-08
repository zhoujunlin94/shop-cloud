package io.github.zhoujunlin94.server.account.controller;

import io.github.zhoujunlin94.server.account.dto.AccountReduceBalanceDTO;
import io.github.zhoujunlin94.server.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/external/api/v1/account")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/reduceBalance")
    public void reduceBalance(@RequestBody @Valid AccountReduceBalanceDTO accountReduceBalanceDTO) {
        accountService.reduceBalance(accountReduceBalanceDTO.getUserId(), accountReduceBalanceDTO.getReducePrice());
    }

}
