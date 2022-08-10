package io.filluk.food.controller;

import io.filluk.food.entity.Product;
import io.filluk.food.repository.ProductRepository;
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

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/index")
    public String showProductList(Model model) {
        model.addAttribute("products", productRepository.findAll());
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

        productRepository.save(product);
        return "redirect:/products/index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));

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

        productRepository.save(product);
        return "redirect:/products/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:"+id));
        productRepository.delete(product);
        return "redirect:/products/index";
    }
}
