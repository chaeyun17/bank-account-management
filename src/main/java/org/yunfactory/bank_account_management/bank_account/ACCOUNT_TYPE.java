package org.yunfactory.bank_account_management.bank_account;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ACCOUNT_TYPE {
    
    CHECKING("CHECKING"), // 입출금
    SAVING("SAVING"),  // 적금
    TERM_DEPOSIT("TERM_DEPOSIT"); // 정기예금
    
    private String name;
}