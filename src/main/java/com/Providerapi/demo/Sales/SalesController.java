package com.Providerapi.demo.Sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SalesController {

    @Autowired
    private SalesService salesService;

    @GetMapping("/all")
    public List<Sales> getAllSales() {
        return salesService.getAllSales();
    }

    @GetMapping("/{id}")
    public Sales getSalesById(@PathVariable int id) {
        return salesService.getSalesById(id);
    }

    @PostMapping("/new")
    public Sales addSales(@RequestBody Sales sales) {
        return salesService.addSales(sales);
    }

    @PutMapping("/update/{id}")
    public Sales updateSales(@PathVariable int id, @RequestBody Sales sales) {
        return salesService.updateSales(id, sales);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSales(@PathVariable int id) {
        salesService.deleteSales(id);
    }
}
