package org.yunfactory.bank_account_management.bank_account.dto;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.yunfactory.bank_account_management.bank_account.Account;

@Component
public class AccountMapper {
    
    private ModelMapper modelMapper = new ModelMapper();

    public AccountDto map(Account account){
        return modelMapper.map(account, AccountDto.class);
    }

    public Account map(AccountCreationDto dto){
        return modelMapper.map(dto, Account.class);
    }
}