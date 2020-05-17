package org.yunfactory.bank_account_management.bank_account;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter @NoArgsConstructor
public class Account {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accountId;

    private String purpose;
    
    private String bankName;
    
    private ACCOUNT_TYPE type;
    
    private String number;
    
    private String description;
    
    private long balance;

	public void updateOverride(Account account) {
        this.purpose = account.purpose;
        this.bankName = account.bankName;
        this.type = account.type;
        this.number = account.number;
        this.description = account.description;
        this.balance = account.balance;
    }
    
    Account(String purpose, String bankName, ACCOUNT_TYPE type, String number, long balance){
        this.purpose = purpose;
        this.bankName = bankName;
        this.type = type;
        this.number = number;
        this.balance = balance;
    }
    
}
