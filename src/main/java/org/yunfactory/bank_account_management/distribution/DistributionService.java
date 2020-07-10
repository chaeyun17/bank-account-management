package org.yunfactory.bank_account_management.distribution;

import java.util.Collection;

public interface DistributionService {

    Collection<Distribution> getAll();

    Distribution save(Distribution distribution);

    void delete(Long id);

    Distribution modify(Long id, Distribution distribution);
    
}