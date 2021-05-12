package org.yunfactory.bank_account_management.distribution;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor @Getter @Setter
public class Distribution {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    private String purpose;

    private long amount;

    private int transferDay;

}