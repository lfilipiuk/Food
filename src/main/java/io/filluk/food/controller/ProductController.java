package io.filluk.food.controller;

import io.filluk.food.entity.Product;
import io.filluk.food.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/index")
    public String showProductList(Model model) {
        model.addAttribute("products", productService.getProducts());
        return "index";
    }

    @GetMapping("/signup")
    public String showSignUpForm(Product product){
        return "add-product";
    }

    @PostMapping("/addproduct")
    public String addProduct(@Validated Product product, BindingResult result, Model model){
        if(result.hasErrors()){
            return "add-product";
        }

        productService.addProduct(product);
        return "redirect:/products/index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model){
        Product product = productService.findProductById(id);
        model.addAttribute("product", product);
        return "update-product";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable("id") long id, @Validated Product product,
                                BindingResult result, Model model){
        if(result.hasErrors()){
            product.setId(id);
            return "update-product";
        }

        productService.addProduct(product);
        return "redirect:/products/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") long id, Model model){
        Product product = productService.findProductById(id);
        productService.deleteProduct(product);
        return "redirect:/products/index";
    }
}
