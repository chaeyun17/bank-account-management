package org.yunfactory.bank_account_management.saving;

import org.springframework.data.repository.CrudRepository;

public interface SavingAccountRepo extends CrudRepository<SavingAccount, Long>{
    
}