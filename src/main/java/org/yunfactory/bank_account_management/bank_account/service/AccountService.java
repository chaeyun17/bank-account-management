package org.yunfactory.bank_account_management.bank_account.service;

import java.util.List;

import org.yunfactory.bank_account_management.bank_account.dto.AccountCreationDto;
import org.yunfactory.bank_account_management.bank_account.dto.AccountDto;

public interface AccountService{

	/**
	 * <p>DB의 모든 데이터를 가져온다.
	 */
	List<AccountDto> getAll();
	
	/**
	 * <p>Id 값의 데이터를 DB에서 삭제요청한다.
	 * <p>존재하지 않는 ID 일 경우, 에러를 리턴한다.
	 */
	boolean deleteById(long id);

	/**
	 * <p>요청한 데이터를 DB에 저장한다.
	 */
	AccountDto save(AccountCreationDto dto);
	
	/**
     * <p>요청한 데이터를 기준으로 DB 데이터를 수정한다.
     * <p>존재하지 않는 id 값을 요청했을 시, 에러를 리턴한다.
     */
	AccountDto modify(long id, AccountCreationDto dto);

	AccountDto getById(Long id);
    
}