package com.app.wagon.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.wagon.model.Category;
import com.app.wagon.model.Product;
import com.app.wagon.service.CategoryService;
import com.app.wagon.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin("*")
public class ProductRestController {

    @Autowired
    CategoryService catService;

    @Autowired
    @Qualifier("productService")
    ProductService pService;

    @Autowired
    private ObjectMapper mapper;

    @RequestMapping(value = { "/rest/product", "/rest/product/{id}" })
    public String requestMethodName(@PathVariable(required = false) String id) throws JsonProcessingException {
        if (id == null)
            return mapper.writeValueAsString(null);

        List<Product> p = pService.findByCategoryId(id);

        return mapper.writeValueAsString(p);

    }

    @RequestMapping(value = { "/rest/products" })
    public List<Product> getAll() throws JsonProcessingException {

        List<Product> p = pService.findAll();

        return p;

    }

    @PostMapping("/rest/products")
    public Product create(@RequestBody Product product) {
        System.out.println(product);
        return pService.create(product);

    }

    @PutMapping("/rest/products/{id}")
    public Product update(@PathVariable Integer id, @RequestBody Product product) {
        System.out.println(product);
        return pService.update(product);

    }

    @DeleteMapping("/rest/products/{id}")
    public void delete(@PathVariable Integer id) {

        pService.delete(id);

    }

    @RequestMapping(value = { "/rest/products/{id}" })
    public Product getById(@PathVariable(required = true) String id) throws JsonProcessingException {

        Product p = pService.findById(Integer.parseInt(id));

        return p;

    }

    @GetMapping(value = { "/rest/product/detail/{id}" })
    public String getProductDetail(@PathVariable String id) {
        Product p = pService.findById(Integer.parseInt(id));
        try {

            return mapper.writeValueAsString(p);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping(value = { "/rest/category/{name}" })
    public ResponseEntity<?> getCategory(@PathVariable String name) {
        return new ResponseEntity<List<Product>>(
                catService.findByName(name).getProdList(),
                HttpStatus.OK);

    }

    @GetMapping(value = "/rest/category")
    public List<Category> getAllCategory() {
        return catService.findAll();
    }

}
