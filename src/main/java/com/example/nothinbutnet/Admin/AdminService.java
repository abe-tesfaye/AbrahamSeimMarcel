package com.example.nothinbutnet.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    // Get all admins
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    // Get an admin by ID
    public Optional<Admin> getAdminById(Long adminId) {
        return adminRepository.findById(adminId);
    }

    // Save or update an admin
    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    // Delete an admin by ID
    public void deleteAdminById(Long adminId) {
        adminRepository.deleteById(adminId);
    }

    // Authenticate an admin by username and password
    public Optional<Admin> login(String username, String password) {
        Admin admin = adminRepository.findByUsername(username);
        if (admin != null && admin.getPassword().equals(password)) {
            return Optional.of(admin); // Successful login
        }
        return Optional.empty(); // Login failed
    }
}