package org.yunfactory.bank_account_management.bank_account.dto;

import org.yunfactory.bank_account_management.bank_account.ACCOUNT_TYPE;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class AccountDto {

    private long accountId;

    private String purpose;
    
    private String bankName;
    
    private ACCOUNT_TYPE type;
    
    private String number;
    
    private String description;
    
    private long balance; 
}
