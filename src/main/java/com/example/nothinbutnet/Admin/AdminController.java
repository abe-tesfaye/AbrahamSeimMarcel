package com.example.nothinbutnet.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Render the Admin Management Page
    @GetMapping
    public String getAllAdmins(Model model) {
        model.addAttribute("admins", adminService.getAllAdmins());
        return "admins"; // Corresponds to a Thymeleaf template named "admins.html"
    }

    // Render Add Admin Form
    @GetMapping("/add")
    public String renderAddAdminForm(Model model) {
        model.addAttribute("admin", new Admin());
        return "add-admin"; // Corresponds to "add-admin.html"
    }

    // Handle Add Admin Form Submission
    @PostMapping("/new")
    public String addNewAdmin(@ModelAttribute("admin") Admin admin, Model model) {
        try {
            adminService.saveAdmin(admin);
            return "redirect:/admin";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to add admin: " + e.getMessage());
            return "error-page";
        }
    }

    // Render Login Page
    @GetMapping("/login")
    public String renderLoginPage(Model model) {
        model.addAttribute("admin", new Admin());
        return "admin-login"; // Corresponds to "admin-login.html"
    }

    // Handle Login Submission
    @PostMapping("/login")
    public String handleLogin(@ModelAttribute("admin") Admin admin, Model model) {
        Optional<Admin> existingAdmin = adminService.login(admin.getUsername(), admin.getPassword());
        if (existingAdmin.isPresent()) {
            // Login success
            return "redirect:/admin/dashboard";
        } else {
            // Login failed
            model.addAttribute("errorMessage", "Invalid username or password.");
            return "admin-login";
        }
    }

    // Render Admin Dashboard
    @GetMapping("/dashboard")
    public String renderDashboard() {
        return "admin-dashboard"; // Corresponds to "admin-dashboard.html"
    }
}