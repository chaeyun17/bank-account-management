package org.yunfactory.bank_account_management.bank_account;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.yunfactory.bank_account_management.bank_account.dto.AccountDto;
import org.yunfactory.bank_account_management.bank_account.service.AccountService;
import org.yunfactory.bank_account_management.bank_account.service.AccountServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AccountTest {
    
    // 테스트 대상
    private AccountService accountService;

    @Mock
    private AccountRepository repository;

    private List<Account> accounts;

    @BeforeEach
    public void setup(){
        accountService = new AccountServiceImpl(repository);
        List<Account> accountList = new ArrayList<>();
        accountList.add(new Account("purpose", "bankName", ACCOUNT_TYPE.CHECKING, "number", 0));
        accountList.add(new Account("purpose", "bankName", ACCOUNT_TYPE.CHECKING, "number", 0));
        accountList.add(new Account("purpose", "bankName", ACCOUNT_TYPE.CHECKING, "number", 0));
        accounts = accountList;
    }

    @Test
    public void 데이터를_모두_리턴한다(){
        Mockito.when(repository.findAll()).thenReturn(accounts);

        List<AccountDto> results = accountService.getAll();
        
        assertEquals(accounts.size(), results.size());
    }

    @Test
    public void 유효한아이디_삭제한다(){
        // Mockito.when(repository.findById(id))
    }
    
}