package org.yunfactory.bank_account_management.distribution;

import java.util.Collection;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DistributionServiceImpl implements DistributionService {

    final private DistributionRepo distributionRepo;

    @Override
    public Collection<Distribution> getAll() {
        return distributionRepo.findAll();
    }

    @Override
    public Distribution save(Distribution distribution) {
        return distributionRepo.save(distribution);
    }

    @Override
    public void delete(Long id) {
        distributionRepo.deleteById(id);
    }

    @Override
    public Distribution modify(Long id, Distribution distribution) {
        Distribution persist = distributionRepo.findById(id).get();
        persist.update(distribution);
        return distributionRepo.save(persist);
    }
    
}