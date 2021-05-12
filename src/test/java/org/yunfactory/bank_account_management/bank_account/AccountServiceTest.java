package org.yunfactory.bank_account_management.bank_account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.yunfactory.bank_account_management.bank_account.dto.AccountCreationDto;
import org.yunfactory.bank_account_management.bank_account.dto.AccountDto;
import org.yunfactory.bank_account_management.bank_account.dto.AccountMapper;
import org.yunfactory.bank_account_management.bank_account.excpetions.AccountBadRequestException;
import org.yunfactory.bank_account_management.bank_account.excpetions.AccountNotFoundException;
import org.yunfactory.bank_account_management.bank_account.service.AccountService;
import org.yunfactory.bank_account_management.bank_account.service.AccountServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    
    // 테스트 대상
    private AccountService accountService;

    @Mock
    private AccountRepository repository;

    @Mock
    private AccountMapper accountMapper;

    private List<Account> accounts;

    private AccountCreationDto creationDto;

    private AccountDto accountDto;

    private Account account;

    @BeforeEach
    public void setup(){
        accountService = new AccountServiceImpl(repository, accountMapper);
        List<Account> accountList = new ArrayList<>();
        accountList.add(new Account(1L, "purpose", "bankName", ACCOUNT_TYPE.CHECKING, "number", "description", 0));
        accountList.add(new Account(2L, "purpose", "bankName", ACCOUNT_TYPE.CHECKING, "number", "description", 0));
        accountList.add(new Account(3L, "purpose", "bankName", ACCOUNT_TYPE.CHECKING, "number", "description", 0));
        accounts = accountList;

        this.account = new Account(4L, "purpose", "bankName", ACCOUNT_TYPE.CHECKING, "number", "description", 0);
        this.creationDto = new AccountCreationDto(account.getPurpose(), account.getBankName(), account.getType(), account.getNumber(), account.getDescription(), 0);
        this.accountDto = new AccountDto(account.getAccountId(), account.getPurpose(), account.getBankName(), account.getType(), account.getNumber(), account.getDescription(), account.getBalance());
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
    @DisplayName("AccountCreationDTO로, 저장을 하면, AccountDTO를 반환한다.")
    public void Given_AccountCreationDTO_When_Save_Then_Return_AccountDTO(){
        // given
        Mockito.when(accountMapper.map(any(AccountCreationDto.class)))
                .thenReturn(account);
        Mockito.when(accountMapper.map(any(Account.class)))
                .thenReturn(accountDto);
        
        // when
        AccountDto returnedDto = accountService.save(creationDto);

        // then
        assertIsEqauls(creationDto, returnedDto);
    }

    @Test
    @DisplayName("ID와 AccountCreationDTO로, 수정을 하면, AccountDto를 반환한다.")
    public void Given_AccountCreationDTO_When_Modify_Then_Returned_AccountDto(){
        // given
        long accountId = account.getAccountId();
        Mockito.when(accountMapper.map(any(AccountCreationDto.class)))
                .thenReturn(account);
        Mockito.when(repository.findById(accountId)).thenReturn(Optional.of(account));
        Mockito.when(accountMapper.map(any(Account.class)))
                .thenReturn(accountDto);
        
        // when
        AccountDto returnedDto = accountService.modify(accountId, creationDto);

        // then
        assertIsEqauls(creationDto, returnedDto);
    }

    private void assertIsEqauls(AccountCreationDto creationDto, AccountDto accountDto){
        assertEquals(creationDto.getBalance(), accountDto.getBalance());
        assertEquals(creationDto.getBankName(), accountDto.getBankName());
        assertEquals(creationDto.getDescription(), accountDto.getDescription());
        assertEquals(creationDto.getNumber(), accountDto.getNumber());
        assertEquals(creationDto.getPurpose(), accountDto.getPurpose());
        assertEquals(creationDto.getType(), accountDto.getType());
    }                                                                                                                                   

    @Test
    @DisplayName("유효하지 않은 ID로, 수정을 하면, BadRequestExcpetion을 반환한다.")
    public void Given_Invalid_Id_When_Modify_Then_Throw_BadRequestException(){
        // given
        long invalidId = -1L;

        // when & then
        assertThrows(AccountBadRequestException.class, ()->accountService.modify(invalidId, creationDto));

    }


}   