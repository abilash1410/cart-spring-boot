package com.ecommerce.cart.controller;




import com.ecommerce.cart.model.Product;
import com.ecommerce.cart.service.ProdService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/api")
@RestController
@CrossOrigin
public class ProductController {

    //Constructor injection to auto-create the object/java bean
    public ProdService prodService;
    private ProductController(ProdService prodService){
        this.prodService = prodService;
    }
    @RequestMapping("/")
    public String testHomePage(HttpServletRequest request) {
        return "This is the test home page of our e-commerce product" +request.getSession().getId();
    }
    @RequestMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<> (prodService.getAllProducts(), HttpStatus.OK);
    }
    @PostMapping("/addProducts")
    public ResponseEntity<?> addProducts(@RequestBody Product product) {
        try{
            return new ResponseEntity<> (prodService.addProducts(product),HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/{prodId}")
    public ResponseEntity<byte[]> getProductImageById(@PathVariable int prodId) {
        Product product = prodService.getProductById(prodId);
        System.out.println(product);
        byte[] productImg  = product.getImageData();
        return ResponseEntity.ok().contentType(MediaType.valueOf(product.getImageType())).
                body(productImg);
    }

    @GetMapping("/getProduct/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        return new ResponseEntity<>(prodService.getProductById(id),HttpStatus.OK);
    }

    @PostMapping("/updateproducts/{prodId}")
    public ResponseEntity<?> updateProduct(@PathVariable int prodId, @RequestBody Product product) {

        try{
            Product prod1  = prodService.getProductById(prodId);
            if(prod1 !=null){
                prodService.updateProducts(prodId, product);
                return new ResponseEntity<String> ("Product updated",HttpStatus.OK);
            }else {
                return new ResponseEntity<String> ("Product not found to update, hence new product added",HttpStatus.OK);
            }
        }catch(Exception e){
            return new ResponseEntity<String> ("Failed to update",HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteProducts/{prodId}")
    public ResponseEntity<?> deleteProductById(@PathVariable int prodId) {
        prodService.deleteProductById(prodId);
        return new ResponseEntity<String>("Product has been deleted",HttpStatus.OK);

    }

    @GetMapping("/search/products")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
        return new ResponseEntity<>(prodService.searchProducts(keyword),HttpStatus.OK);
    }

    @GetMapping("/csrfToken")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }
}
