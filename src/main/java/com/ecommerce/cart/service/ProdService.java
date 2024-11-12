package com.ecommerce.cart.service;


import com.ecommerce.cart.model.Product;
import com.ecommerce.cart.repo.ProdRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProdService {

    //constructor dependency injection to create object/java bean
    ProdRepo prodRepo;

    private ProdService(ProdRepo prodRepo) {
        this.prodRepo = prodRepo;
    }

    public List<Product> getAllProducts() {
        return prodRepo.findAll();
    }

    public Product addProducts(Product product) {
        return prodRepo.save(product);
    }
    public Product getProductById(int id) {
        return prodRepo.findById(id).orElse(null);
    }
    public void updateProducts(int prodId,Product prod1) {
        Product checkprod  = getProductById(prodId);
        if (checkprod!=null) {
            prodRepo.save(prod1);
        }

    }
    public void deleteProductById(int prodId) {
          prodRepo.deleteById(prodId);
    }

    public List<Product> searchProducts(String keyword) {
        return prodRepo.search(keyword);
    }
}
