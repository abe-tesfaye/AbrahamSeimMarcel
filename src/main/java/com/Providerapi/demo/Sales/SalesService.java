package com.Providerapi.demo.Sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SalesService {

    @Autowired
    private SalesRepository salesRepository;

    public List<Sales> getAllSales() {
        return salesRepository.findAll();
    }

    public Sales getSalesById(int id) {
        return salesRepository.findById(id).orElse(null);
    }

    public Sales addSales(Sales sales) {
        return salesRepository.save(sales);
    }

    public Sales updateSales(int id, Sales updatedSales) {
        Sales existingSales = salesRepository.findById(id).orElse(null);
        if (existingSales != null) {
            existingSales.setDate(updatedSales.getDate());
            existingSales.setQuantity(updatedSales.getQuantity());
            existingSales.setProductId(updatedSales.getProductId());
            return salesRepository.save(existingSales);
        }
        return null;
    }

    public void deleteSales(int id) {
        salesRepository.deleteById(id);
    }
}
