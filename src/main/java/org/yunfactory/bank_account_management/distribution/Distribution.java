package org.yunfactory.bank_account_management.distribution;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor @Getter @Setter @AllArgsConstructor
public class Distribution {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    // 용도
    private String purpose;
    // 금액
    private Long amount;
    // 이체일
    private Integer transferDay;

	public void update(Distribution distribution) {
        
        if(Objects.nonNull(distribution.getPurpose()))
            this.purpose = distribution.getPurpose();
        if(Objects.nonNull(distribution.getAmount()))
            this.amount = distribution.getAmount();
        if(Objects.nonNull(distribution.getTransferDay()))
            this.transferDay = distribution.getTransferDay();
    }

}