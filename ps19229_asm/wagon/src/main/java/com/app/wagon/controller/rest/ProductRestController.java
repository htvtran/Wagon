package com.app.wagon.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.wagon.model.Product;
import com.app.wagon.service.CategoryService;
import com.app.wagon.service.ProductService;
import com.app.wagon.util.MapperFactory;
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

    private ObjectMapper mapper = MapperFactory.getMapper();

    @RequestMapping(value = { "/rest/product", "/rest/product/{id}" })
    public String requestMethodName(@PathVariable(required = false) String id) throws JsonProcessingException {

        if (id == null)
            return mapper.writeValueAsString(null);

        List<Product> p = pService.findByCategoryId(id);

        return mapper.writeValueAsString(p);

    }

    @GetMapping(value = { "/rest/product/detail/{id}" })
    public String getProductDetail(@PathVariable String id) {
        Product p = pService.findById(Integer.parseInt(id));
        try {
            System.out.println("request");
            return mapper.writeValueAsString(p);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
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

}
