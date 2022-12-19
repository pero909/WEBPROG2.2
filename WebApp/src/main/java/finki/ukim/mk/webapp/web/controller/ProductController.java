package finki.ukim.mk.webapp.web.controller;

import finki.ukim.mk.webapp.model.Category;
import finki.ukim.mk.webapp.model.Manufacturer;
import finki.ukim.mk.webapp.model.Product;
import finki.ukim.mk.webapp.service.CategoryService;
import finki.ukim.mk.webapp.service.ManufacturerService;
import finki.ukim.mk.webapp.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ManufacturerService manufacturerService;

    public ProductController(ProductService productService,
                             CategoryService categoryService,
                             ManufacturerService manufacturerService) {
        this.productService = productService;
        this.categoryService=categoryService;
        this.manufacturerService=manufacturerService;

    }

    @GetMapping
    public String getProductPage(@RequestParam(required = false) String error, Model model){
        if(error !=null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
         List<Product> productList = productService.findAll();
         model.addAttribute("products",productList);
         model.addAttribute("bodyContent","products");
         return "master-template";
    }
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        this.productService.deleteById(id);

        return "redirect:/products";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ADMIN')")
    public String addProductPage(Model model){
        List<Category> categories = this.categoryService.listCategories();
        List<Manufacturer> manufacturers =this.manufacturerService.findAll();
        model.addAttribute("categories",categories);
        model.addAttribute("manufacturers",manufacturers);
        model.addAttribute("bodyContent","Addproducts");
        return "master-template";
    }

    @GetMapping("/edit-form/{id}")
    public String editProductPage(@PathVariable Long id,
                                  Model model){

        if(this.productService.findById(id).isPresent()){
            Product product = this.productService.findById(id).get();
            List<Category> categories = this.categoryService.listCategories();
            List<Manufacturer> manufacturers =this.manufacturerService.findAll();
            model.addAttribute("categories",categories);
            model.addAttribute("manufacturers",manufacturers);
            model.addAttribute("product",product);
            model.addAttribute("bodyContent","Addproducts");
            return "master-template";
        }
        return "redirect:/products?error=ProductNotFound";

    }

    @PostMapping("/add")
    public String saveProduct(@RequestParam String name,
                              @RequestParam Double price,
                              @RequestParam Integer quantity,
                              @RequestParam Long category,
                              @RequestParam Long manufacturer,
                              Model model){
        this.productService.save(name,price,quantity,category,manufacturer);
        model.addAttribute("products",this.productService.findAll());
        model.addAttribute("bodyContent","products");
        return "master-template";

    }

}
