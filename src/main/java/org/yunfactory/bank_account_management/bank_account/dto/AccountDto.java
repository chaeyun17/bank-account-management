package org.yunfactory.bank_account_management.bank_account.dto;

import java.util.Objects;

import org.yunfactory.bank_account_management.bank_account.ACCOUNT_TYPE;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    private long accountId;

    private String purpose;

    private String bankName;

    private ACCOUNT_TYPE type;

    private String number;

    private String description;

    private long balance;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AccountDto)) {
            return false;
        }
        AccountDto accountDto = (AccountDto) o;
        return accountId == accountDto.accountId && Objects.equals(purpose, accountDto.purpose) && Objects.equals(bankName, accountDto.bankName) && Objects.equals(type, accountDto.type) && Objects.equals(number, accountDto.number) && Objects.equals(description, accountDto.description) && balance == accountDto.balance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, purpose, bankName, type, number, description, balance);
    }

    
}
