package org.yunfactory.bank_account_management.bank_account;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Account {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accountId;

    private String purpose;
    
    private String bankName;
    
    private ACCOUNT_TYPE type;
    
    // account-number. ex) 111-23-14457
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
    
    Account(long id, String purpose, String bankName, ACCOUNT_TYPE type, String number, String description, long balance){
        this.accountId = id;
        this.purpose = purpose;
        this.bankName = bankName;
        this.type = type;
        this.number = number;
        this.description = description;
        this.balance = balance;
    }

}
