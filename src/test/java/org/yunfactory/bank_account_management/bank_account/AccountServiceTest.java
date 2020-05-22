package org.yunfactory.bank_account_management.bank_account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.yunfactory.bank_account_management.bank_account.dto.AccountCreationDto;
import org.yunfactory.bank_account_management.bank_account.dto.AccountDto;
import org.yunfactory.bank_account_management.bank_account.dto.AccountMapper;
import org.yunfactory.bank_account_management.bank_account.service.AccountService;
import org.yunfactory.bank_account_management.bank_account.service.AccountServiceImpl;
import org.yunfactory.bank_account_management.excpetions.AccountNotFoundException;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    
    // 테스트 대상
    private AccountService accountService;

    @Mock
    private AccountRepository repository;

    @Mock
    private AccountMapper accountMapper;

    private List<Account> accounts;

    @BeforeEach
    public void setup(){
        accountService = new AccountServiceImpl(repository, accountMapper);
        List<Account> accountList = new ArrayList<>();
        accountList.add(new Account("purpose", "bankName", ACCOUNT_TYPE.CHECKING, "number", 0));
        accountList.add(new Account("purpose", "bankName", ACCOUNT_TYPE.CHECKING, "number", 0));
        accountList.add(new Account("purpose", "bankName", ACCOUNT_TYPE.CHECKING, "number", 0));
        accounts = accountList;
    }

    @Test
    @DisplayName("Account 모두 조회 시, Account 리스트를 반환한다.")
    public void Given_none_When_Get_All_Then_Returned_List(){
        Mockito.when(repository.findAll()).thenReturn(accounts);

        List<AccountDto> results = accountService.getAll();
        
        assertEquals(accounts.size(), results.size());
    }

    @Test
    @DisplayName("Account ID로, 해당 Account를 삭제하면, true를 반환한다.")
    public void Given_Id_When_Delete_Account_Then_Return_True(){
        // given
        long accountId = 1L;
        Mockito.when(repository.findById(accountId)).thenReturn(Optional.of(new Account()));

        // when
        boolean actual = accountService.deleteById(accountId);

        // then
        assertTrue(actual);
    }

    @Test
    @DisplayName("존재하지 않는 Account Id로, Account를 삭제하면, NotFoundException을 Throw 한다")
    public void Given_Invalid_AccountId_When_Delete_Account_Then_Throw_NotFoundException(){
        // given
        long id = -1L;

        // when then
        assertThrows(AccountNotFoundException.class, ()->accountService.deleteById(id));

    }

    @Test
    @DisplayName("Account 생성 DTO로, 저장을 하면, AccountDTO를 반환한다.")
    public void Given_AccountCreationDTO_When_Save_Then_Return_AccountDTO(){
        // given
        AccountCreationDto creationDto = new AccountCreationDto("purpose", "bankName", ACCOUNT_TYPE.CHECKING, "number", "description", 0);
        AccountDto accountDto = new AccountDto(1L, creationDto.getPurpose(), creationDto.getBankName(), creationDto.getType(), creationDto.getNumber(), creationDto.getDescription(), creationDto.getBalance());
        Mockito.when(accountMapper.map(any(AccountCreationDto.class)))
                .thenReturn(new Account());
        Mockito.when(accountMapper.map(any(Account.class)))
                .thenReturn(accountDto);
        
        // when
        AccountDto returnedDto = accountService.save(creationDto);

        // then
        assertEquals(creationDto.getBalance(), returnedDto.getBalance());
        assertEquals(creationDto.getBankName(), returnedDto.getBankName());
        assertEquals(creationDto.getDescription(), returnedDto.getDescription());
        assertEquals(creationDto.getNumber(), returnedDto.getNumber());
        assertEquals(creationDto.getPurpose(), returnedDto.getPurpose());
        assertEquals(creationDto.getType(), returnedDto.getType());
    }
}   