package org.yunfactory.bank_account_management.bank_account;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.yunfactory.bank_account_management.bank_account.dto.AccountCreationDto;
import org.yunfactory.bank_account_management.bank_account.dto.AccountDto;
import org.yunfactory.bank_account_management.bank_account.dto.AccountMapper;

public class AccountMapperTest {

    private AccountMapper accountMapper;

    private Account account;

    private AccountDto accountDto;

    private AccountCreationDto accountCreationDto;

    @BeforeEach
    public void setup(){
        accountMapper = new AccountMapper();
        account = new Account(1L, "적금용", "신한", ACCOUNT_TYPE.CHECKING, "1", "description", 1001);
        accountDto = new AccountDto(account.getAccountId(), account.getPurpose(), account.getBankName(), account.getType(), account.getNumber(), account.getDescription(), account.getBalance());
        accountCreationDto = new AccountCreationDto(account.getPurpose(), account.getBankName(), account.getType(), account.getNumber(), account.getDescription(), account.getBalance());
    }

    @Test
    @DisplayName("Account로, 맵핑하면, AccountDto를 반환한다.")
    public void Given_Account_When_Map_Then_Returned_AccountDto(){
        // Given

        // When
        AccountDto returnedAccountDto = accountMapper.map(account);
        // Then
        assertEquals(accountDto, returnedAccountDto);
    }

    @Test
    @DisplayName("AccountCreationDTO로, 맵핑하면, Account를 반환한다.")
    public void Given_AccountDto_When_Map_Then_Returned_Account(){
        // Given

        // When
        Account returned = accountMapper.map(accountCreationDto);

        // Then
        assertEquals(account.getPurpose(), returned.getPurpose());
        assertEquals(account.getBankName(), returned.getBankName());
        assertEquals(account.getType(), returned.getType());
        assertEquals(account.getNumber(), returned.getNumber());
        assertEquals(account.getDescription(), returned.getDescription());
        assertEquals(account.getBalance(), returned.getBalance());
        
    }
    
}