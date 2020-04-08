package com.ynz.hplusapp.restcontrollers;

import com.ynz.hplusapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@Controller //from JEE
@RestController //introduced by Spring
public class ProductRestController {

    @Autowired
    private ProductRepository productRepository;

//    @GetMapping("/hplus/rest/products")
//    @ResponseBody
//    public List<Product> getProducts() {
//        List<Product> products = new ArrayList<>();
//        productRepository.findAll().forEach(p -> products.add(p));
//        return products;
//    }

    @GetMapping("/hplus/rest/products")
    public ResponseEntity getProductsByRequestParameter(@RequestParam("name") String name) {
        return new ResponseEntity<>(productRepository.findByName(name), HttpStatus.OK);
    }

    @GetMapping("/hplus/rest/products/{name}")
    public ResponseEntity getProductsByPathVariable(@PathVariable("name") String name) {
        return new ResponseEntity<>(productRepository.findByName(name), HttpStatus.OK);
    }


}
