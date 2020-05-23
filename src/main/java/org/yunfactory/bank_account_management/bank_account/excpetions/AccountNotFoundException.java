package org.yunfactory.bank_account_management.bank_account.excpetions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "찾을 수 없습니다.")
public class AccountNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 2219062050062491685L;

    public AccountNotFoundException(){
        super();
    }
    
}