package finki.ukim.mk.webapp.web.controller;

import finki.ukim.mk.webapp.model.ShoppingCart;
import finki.ukim.mk.webapp.model.User;
import finki.ukim.mk.webapp.service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public String getShoppingCartPage(@RequestParam(required = false) String error,
                                      HttpServletRequest req, Model model){
        if(error!=null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        String username=req.getRemoteUser();
        ShoppingCart shoppingCart = this.shoppingCartService.getActiveShoppingCart(username);
        model.addAttribute("products",
                this.shoppingCartService.listAllProductsInShoppingCart(shoppingCart.getId()));

        return "shopping-cart";

    }
    @PostMapping("/add-product/{id}")
    public String addProductShoppingCart(@PathVariable Long id,
                                         HttpServletRequest req){
        try{
            String username=req.getRemoteUser();
            ShoppingCart shoppingCart = this.shoppingCartService
                    .addProductToShoppingCart(username,id);
            return "redirect:/shopping-cart";
        }catch(RuntimeException exception){
            return "redirect:/shopping-cart?error="+exception.getMessage();
        }

    }
}
