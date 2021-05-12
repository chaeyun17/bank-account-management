package org.yunfactory.bank_account_management.bank_account.excpetions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "잘못 요청하였습니다")
public class AccountBadRequestException extends RuntimeException{

    private static final long serialVersionUID = 1210456168574037802L;

    public AccountBadRequestException(){
        super();
    }
    
}