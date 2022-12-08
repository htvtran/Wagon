package com.app.wagon.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.wagon.model.Category;
import com.app.wagon.model.Product;
import com.app.wagon.service.CategoryService;
import com.app.wagon.service.ProductService;

@Controller
public class ProductController extends BaseViewController {

    @Autowired
    CategoryService catSer;

    @Autowired
    @Qualifier("productService")
    ProductService pService;

    private List<Product> pList;

    private final String LIST_ATTRIBUTE_NAME = "pList";

    @RequestMapping({ "/products", "/product/{id}" })
    public String getView(@PathVariable(required = false) String id, Model model) {
        Integer idP;
        List<Product> pl = id == null ? pService.findAll() : pService.findByCategoryId(id);

        Category cat = (pl == null || pl.size() == 0 || id == null) ? null : pl.get(0).getCategory();

        setProductList(pl, model);
        model.addAttribute("selectedCat", cat);

        return getShopTemplateViewName("product");

    }

    @GetMapping({ "/products/{name}" })
    public String getProductPage(@PathVariable(required = false, value = "name") String name, Model model) {
        // System.out.println("name " + name);

        Category c = catSer.findByName(name);

        setProductList(c.getProdList(), model);
        model.addAttribute("selectedCat", c);

        return getShopTemplateViewName("product");
    }

    @GetMapping({ "/product/detail/{name}" })
    public String getProductDetailPage(@PathVariable(required = true, value = "name") String name, Model model) {
        // System.out.println("name " + name);

        try {
            Product p = pService.findByName(name);
            List<Product> related = p.getCategory().getProdList();
            Collections.shuffle(related);

            model.addAttribute("product", p);
            model.addAttribute("related", related.stream().limit(4).collect(Collectors.toList()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getShopTemplateViewName("product-single");
    }

    // @ModelAttribute("pList")
    public List<Product> getPList() {
        return this.pList;
    }

    private void setList(Model model) {
        model.addAttribute(LIST_ATTRIBUTE_NAME, this.pList);
    }

    public List<Product> setProductList(List<Product> list, Model model) {
        this.pList = list;
        setList(model);
        return pList;
    }

}
