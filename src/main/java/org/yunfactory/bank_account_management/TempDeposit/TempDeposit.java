package org.yunfactory.bank_account_management.TempDeposit;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor @Getter @Setter
public class TempDeposit {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    private long amount;

    private int interestRate;
    
    private LocalDate openingDate;
    
    private LocalDate maturityDate;
    
}