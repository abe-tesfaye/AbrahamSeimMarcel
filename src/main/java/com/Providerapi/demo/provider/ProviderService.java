package com.Providerapi.demo.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    public List<Provider> getAllProviders() {
        return providerRepository.findAll();
    }

    public Provider getProviderById(int id) {
        return providerRepository.findById(id).orElse(null);
    }

    public Provider addProvider(Provider provider) {
        return providerRepository.save(provider);
    }

    public Provider updateProvider(int id, Provider updatedProvider) {
        Provider existingProvider = providerRepository.findById(id).orElse(null);
        if (existingProvider != null) {
            existingProvider.setUsername(updatedProvider.getUsername());
            existingProvider.setPassword(updatedProvider.getPassword());
            existingProvider.setUpdatedAt(updatedProvider.getUpdatedAt());
            return providerRepository.save(existingProvider);
        }
        return null;
    }

    public void deleteProvider(int id) {
        providerRepository.deleteById(id);
    }
}
