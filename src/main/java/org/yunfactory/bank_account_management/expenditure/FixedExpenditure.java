package org.yunfactory.bank_account_management.expenditure;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.yunfactory.bank_account_management.bank_account.Account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor @Getter @Setter 
public class FixedExpenditure {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    private String name;

    private String institutionName;

    private long amount;

    private long transferDay;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account transferAccount;
    
}