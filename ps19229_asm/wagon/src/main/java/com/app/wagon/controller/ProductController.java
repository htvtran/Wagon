package com.app.wagon.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.wagon.model.Category;
import com.app.wagon.model.Product;
import com.app.wagon.service.CategoryService;
import com.app.wagon.service.ProductService;
import com.app.wagon.util.PageinationUtil;

@Controller
public class ProductController extends BaseViewController<ProductController> {

    @Autowired
    CategoryService catSer;

    @Autowired
    @Qualifier("productService")
    ProductService pService;

    @Autowired
    PageinationUtil pageHepler;

    private List<Product> pList;

    private final String LIST_ATTRIBUTE_NAME = "pList";

    private final int LIST_SHOW_ITEM_NUM = 9;

    @RequestMapping("/prodcuts")
    public String search(@RequestParam String keyword, Model model) {
        List<Product> results = pService.findByNameLike(keyword);

        String mess = (results == null || results.isEmpty()) ? "Can't find any products with name like " + keyword : "";

        model.addAttribute("mesaage", mess);
        setProductList(results, model);
        return getShopTemplateViewName("product");

    }

    @RequestMapping({ "/products", "/product/{id}" })
    public String getView(@PathVariable(required = false) String id,
            @RequestParam(required = false, value = "p") Optional<Integer> page, Model model) {

        // get Product list
        List<Product> pl = id == null ? pService.findAll() : pService.findByCategoryId(id);

        // selected category object, null if url is /products
        Category cat = (pl == null || pl.size() == 0 || id == null) ? null : pl.get(0).getCategory();

        PagedListHolder ph = pageHepler.getPageHolder(Optional.of(LIST_SHOW_ITEM_NUM), page, pl);

        List<Product> list = pageHepler.getList(ph, Product.class);
        System.out.println(" page param " + page);
        System.out.println(" page count " + ph.getPageCount());
        System.out.println("list size " + list.get(0));
        System.out.println("page " + ph.getPage());

        setProductList(list, model);

        // mv.addObject("products", list);

        model.addAttribute("page", ph);
        model.addAttribute("selectedCat", cat);

        return getShopTemplateViewName("product");

    }

    @ModelAttribute("title")
    public String getTitle() {
        return getDefaultTitle();
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
