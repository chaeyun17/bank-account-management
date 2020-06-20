package org.yunfactory.bank_account_management.saving;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.yunfactory.bank_account_management.bank_account.Account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor @Getter @Setter
public class SavingAccount{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long savingId;

    private Long amount;
    
    private Integer transferDay;
    
    private Integer interestRate;
    
    private LocalDate openingDate;
    
    private LocalDate maturityDate;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account transferAccont;

}