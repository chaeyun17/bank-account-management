package org.yunfactory.bank_account_management.bank_account;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.yunfactory.bank_account_management.bank_account.dto.AccountCreationDto;
import org.yunfactory.bank_account_management.bank_account.dto.AccountDto;
import org.yunfactory.bank_account_management.bank_account.service.AccountService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class AccountController {
    
    private final AccountService accountService;

    /**
     * <p>RestAPI를 맵핑해서 함수를 실행한다.(라이브러리)
     * <p>데이터를 JSON으로 변환한다.(라이브러리)
     */
    @GetMapping("/api/accounts")
    public List<AccountDto> getAll(){
        return accountService.getAll();
    }

    @GetMapping("/api/accounts/{id}")
    public AccountDto getById(@PathVariable Long id){
        return accountService.getById(id);
    }

    @DeleteMapping("/api/accounts/{id}")
    public boolean deleteById(@PathVariable long id){
        return accountService.deleteById(id);
    }
    /**
     * <p>HTTP REQUETS BODY를 파싱해서 객체 데이터로 변경한다. (라이브러리)
     * <p>처리한 데이터 결과를 JSON 으로 변환하여 HTTP Response Body에 추가한다. (라이브러리)
     */
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/api/accounts")
    public AccountDto save(@Validated @RequestBody AccountCreationDto dto){
        return accountService.save(dto);
    }

    @PutMapping("/api/accounts/{id}")
    public AccountDto modify(@PathVariable long id,
                            @Validated @RequestBody AccountCreationDto dto){
        return accountService.modify(id, dto);
    }
    
}