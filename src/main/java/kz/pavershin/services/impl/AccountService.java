package kz.pavershin.services.impl;

import kz.pavershin.models.Account;
import kz.pavershin.repository.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountDAO accountDAO;

    public void save(Double amount, boolean isIncrement) {
        Iterable<Account> accountIterable = accountDAO.findAll();
        Account account = accountIterable.iterator().next();

        if (!isIncrement) {
            amount = amount * -1;
        }

        account.setValue(account.getValue() + amount);

        accountDAO.save(account);
    }

    public void setAccountDAO(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }
}
