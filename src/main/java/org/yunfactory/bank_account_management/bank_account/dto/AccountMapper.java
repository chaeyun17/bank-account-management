package org.yunfactory.bank_account_management.bank_account.dto;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import org.yunfactory.bank_account_management.bank_account.Account;

@Component
public class AccountMapper {
    
    private final ModelMapper modelMapper = new ModelMapper();

    public AccountDto map(Account account){
        return modelMapper.map(account, AccountDto.class);
    }

    public Account map(AccountCreationDto dto){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(dto, Account.class);
    }
}