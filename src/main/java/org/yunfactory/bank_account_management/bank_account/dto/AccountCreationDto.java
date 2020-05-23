package org.yunfactory.bank_account_management.bank_account.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.yunfactory.bank_account_management.bank_account.ACCOUNT_TYPE;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountCreationDto {

    @NotNull
    private String purpose;
    
    @NotNull
    private String bankName;
    
    @NotNull
    private ACCOUNT_TYPE type;
    
    @NotNull
    private String number;
    
    @NotNull
    private String description;
    
    @NotNull
    @Min(0)
    private long balance; 
    
}
