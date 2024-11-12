package com.ecommerce.cart.repo;

import com.ecommerce.cart.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdRepo extends JpaRepository<Product,Integer> {

//    @Query("SELECT p from Product p WHERE " +"LOWER(p.name) LIKE LOWER(CONCAT('%',:name,'%')) OR "+
//            "LOWER(p.brand) LIKE LOWER(CONCAT('%',:name,'%'))  OR "+
//            "LOWER(p.description) LIKE LOWER(CONCAT('%',:name,'%')) OR "+
//             "LOWER(p.price) LIKE LOWER(CONCAT('%',:name,'%')) ")

    @Query("SELECT p from Product p ")
     List<Product> search(String name);
}
