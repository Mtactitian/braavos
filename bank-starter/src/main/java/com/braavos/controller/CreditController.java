package com.braavos.controller;

import com.braavos.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@ConditionalOnProperty(prefix = "braavos", name = "controller", havingValue = "enabled")
@RestController
public class CreditController {

    @Autowired
    private CreditService creditService;

    @GetMapping(value = "/credit")
    public String getCredit(@RequestParam(name = "username") final String userName,
                            @RequestParam(name = "amount") final long amount) {
        if (creditService.getCredit(userName, amount)) {
            return userName + " got credit: " + amount;
        } else {
            return userName + " can't get credit at the moment!";
        }
    }

    @GetMapping(value = "/deposit")
    public String deposit(@RequestParam(name = "username") final String userName,
                          @RequestParam(name = "amount") final long amount) {
        if (creditService.makeDeposit(userName, amount)) {
            return userName + " successfully made deposit: " + amount;
        } else {
            return "deposit can't be made at the moment";
        }
    }

    @GetMapping(value = "/banned")
    public List<String> getBannedUsers() {
        return creditService.getBannedUsers();
    }

    @GetMapping(value = "/balance")
    public long balance() {
        return creditService.getBalance();
    }

    @GetMapping(value = "/debts")
    public Map<String,Long> getUserDebtors() {
        return creditService.getUserDebts();
    }
}
