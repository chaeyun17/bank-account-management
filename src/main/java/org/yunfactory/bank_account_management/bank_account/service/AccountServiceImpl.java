package org.yunfactory.bank_account_management.bank_account.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.yunfactory.bank_account_management.bank_account.Account;
import org.yunfactory.bank_account_management.bank_account.AccountRepository;
import org.yunfactory.bank_account_management.bank_account.dto.AccountCreationDto;
import org.yunfactory.bank_account_management.bank_account.dto.AccountDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public List<AccountDto> getAll() {
        List<Account> accounts = accountRepository.findAll();
        ModelMapper mapper = new ModelMapper();
        return accounts.stream()
                        .map(account-> mapper.map(account, AccountDto.class))
                        .collect(Collectors.toList());
    }

    @Override
    public boolean deleteById(long id) {
        Account account = accountRepository.findById(id).orElseThrow();
        accountRepository.delete(account);
        return true;
    }

    @Override
    public AccountDto save(AccountCreationDto dto) {
        ModelMapper mapper = new ModelMapper();
        Account account = mapper.map(dto, Account.class);
        accountRepository.save(account);
        return mapper.map(account, AccountDto.class);
    }


    @Override
    public AccountDto modify(long id, AccountCreationDto dto) {
        ModelMapper mapper = new ModelMapper();
        Account update = mapper.map(dto, Account.class);
        Account persist = accountRepository.findById(id).orElseThrow();
        persist.updateOverride(update);
        accountRepository.save(persist);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(persist, AccountDto.class);
    }
    
}