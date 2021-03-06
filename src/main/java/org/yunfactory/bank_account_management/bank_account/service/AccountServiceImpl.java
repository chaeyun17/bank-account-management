package org.yunfactory.bank_account_management.bank_account.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yunfactory.bank_account_management.bank_account.Account;
import org.yunfactory.bank_account_management.bank_account.AccountRepository;
import org.yunfactory.bank_account_management.bank_account.dto.AccountCreationDto;
import org.yunfactory.bank_account_management.bank_account.dto.AccountDto;
import org.yunfactory.bank_account_management.bank_account.dto.AccountMapper;
import org.yunfactory.bank_account_management.bank_account.excpetions.AccountBadRequestException;
import org.yunfactory.bank_account_management.bank_account.excpetions.AccountNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper mapper;

    @Override
    public List<AccountDto> getAll() {
        List<Account> accounts = accountRepository.findAll();
        ModelMapper mapper = new ModelMapper();
        return accounts.stream()
                        .map(account-> mapper.map(account, AccountDto.class))
                        .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public boolean deleteById(long id) {
        Account account = accountRepository.findById(id)
                                            .orElseThrow(AccountNotFoundException::new);
        accountRepository.delete(account);
        return true;
    }

    @Override  
    @Transactional         
    public AccountDto save(AccountCreationDto dto) {
        Account account = mapper.map(dto);
        accountRepository.save(account);
        return mapper.map(account);
    }


    @Override
    @Transactional
    public AccountDto modify(long id, AccountCreationDto dto) {
        Account update = mapper.map(dto);
        Account persist = accountRepository.findById(id)
                                            .orElseThrow(AccountBadRequestException::new);
        persist.updateOverride(update);
        accountRepository.save(persist);
        return mapper.map(persist);
    }

    @Override
    public AccountDto getById(Long id) {
        return mapper.map(accountRepository.findById(id).orElseThrow());
    }
    
}