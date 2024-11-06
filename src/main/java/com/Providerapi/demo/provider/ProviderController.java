package com.Providerapi.demo.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/providers")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @GetMapping("/all")
    public List<Provider> getAllProviders() {
        return providerService.getAllProviders();
    }

    @GetMapping("/{id}")
    public Provider getProviderById(@PathVariable int id) {
        return providerService.getProviderById(id);
    }

    @PostMapping("/new")
    public Provider addProvider(@RequestBody Provider provider) {
        return providerService.addProvider(provider);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Provider> updateProvider(@PathVariable("id") int id, @RequestBody Provider provider) {
        return ResponseEntity.ok(providerService.updateProvider(id, provider));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProvider(@PathVariable int id) {
        providerService.deleteProvider(id);
    }
}
