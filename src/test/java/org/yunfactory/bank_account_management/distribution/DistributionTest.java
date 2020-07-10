package org.yunfactory.bank_account_management.distribution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DistributionTest {

    Long id;
    String purpose;
    Long amount;
    Integer transferDay;

    @BeforeEach
    public void setup(){
        this.id = 1L;
        this.purpose = "purpose";
        this.amount = 123L;
        this.transferDay = 5;
    }
    
    @Test
    public void Given_None_When_Get_Props_Then_Returned(){
        Distribution distribution = new Distribution(id, purpose, amount, transferDay);
        
        equals(distribution);   
    }
    
    @Test
    public void Given_Empty_Entity_When_Set_Props_Then_UpdatedProps(){
        Distribution distribution = new Distribution();

        distribution.setId(id);
        distribution.setPurpose(purpose);
        distribution.setAmount(amount);
        distribution.setTransferDay(transferDay);

        equals(distribution);
    }

    private void equals(Distribution distribution){
        assertEquals(id, distribution.getId());
        assertEquals(purpose, distribution.getPurpose());
        assertEquals(amount, distribution.getAmount());
        assertEquals(transferDay, distribution.getTransferDay());
    }

    @Test
    public void Given_Distribution_When_Update_Then_UpdatedProps(){
        Distribution persist = new Distribution(id, "p", 0L, 0);
        Distribution distribution = new Distribution(id, purpose, amount, transferDay);

        persist.update(distribution);

        equals(persist);
    }

    @Test
    public void Given_NullProps_When_Update_Then_NotUpdated(){
        Distribution persist = new Distribution(id, purpose, amount, transferDay);
        Distribution distribution = new Distribution(id, null, null, null);

        persist.update(distribution);

        equals(persist);
    }

}