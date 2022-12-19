package mk.finki.ukim.mk.lab.web.controller;


import mk.finki.ukim.mk.lab.model.*;
import mk.finki.ukim.mk.lab.model.Enum.Role;
import mk.finki.ukim.mk.lab.model.Exceptions.InvalidArgumentException;
import mk.finki.ukim.mk.lab.model.Exceptions.ManufacturerNotFoundException;
import mk.finki.ukim.mk.lab.service.*;
import org.h2.engine.Mode;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/balloons")
public class BalloonController {

    private final BaloonService baloonService;
    private final ManufacturerService manufacturerService;
    private final OrderService orderService;
    private final AuthService authService;
    private final ShoppingCartService shoppingCartService;

    public BalloonController(BaloonService baloonService, ManufacturerService manufacturerService, OrderService orderService, AuthService authService, ShoppingCartService shoppingCartService) {
        this.baloonService = baloonService;
        this.manufacturerService = manufacturerService;
        this.orderService = orderService;
        this.authService = authService;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model, HttpServletRequest request) {
        return "registerPage";
    }

    @PostMapping("/register")
    public String registerUser(Model model, HttpServletRequest request
            , @RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String repeatedPassword,
                               @RequestParam String name,
                               @RequestParam String surname,
                               @RequestParam Role role) {

        this.authService.register(username, password, repeatedPassword, name, surname, role);

        return "redirect:/balloons/login";

    }

    @GetMapping("/login")
    public String getLoginPage(HttpServletRequest request, Model model) {


        return "login_page";
    }

    @PostMapping("/login")
    public String getHomePage(HttpServletRequest request, Model model) {
        User user = null;
        try {

            user = this.authService.login(request.getParameter("username"),
                    request.getParameter("password"));
            Order order = new Order();
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("order", order);
            return "redirect:/balloons/home";
        } catch (InvalidArgumentException e) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", e.getMessage());

            return "login_page";
        }

    }


    @GetMapping("/home")
    public String getBalloonPage(HttpServletRequest request,
                                 Model model) {
        List<Balloon> balloons = this.baloonService.listAll();
        model.addAttribute("balloons", balloons);
        return "listBalloons";
    }

    @PostMapping("/add")
    public String saveBalloon(@RequestParam String name,
                              @RequestParam String description,
                              @RequestParam Long manufacturer,
                              HttpServletRequest request) {

        Manufacturer manufacturer1 = this.manufacturerService.findById(manufacturer)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturer));
        if (this.baloonService.searchByNameAndDescription(name, description).isPresent()) {
            Balloon balloon = this.baloonService.searchByNameAndDescription(name, description)
                    .orElseThrow(InvalidArgumentException::new);
            balloon.setDescription(description);
            balloon.setName(name);
        } else this.baloonService.save(new Balloon(name, description, "", "", manufacturer1));


        return "redirect:/balloons/home";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBalloon(@PathVariable Long id) {
        this.baloonService.deleteById(id);
        return "redirect:/balloons/home";
    }

    @GetMapping("/add-form")
    public String getAddBalloon(Model model) {
        List<Manufacturer> manufacturers = this.manufacturerService.findAll();
        model.addAttribute("manufacturers", manufacturers);
        return "addBalloon";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditBalloonPage(@PathVariable Long id, Model model,
                                     HttpServletRequest request) {
        Balloon balloon = this.baloonService.findById(id).get();
        List<Manufacturer> manufacturers = this.manufacturerService.findAll();
        model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("balloon", balloon);
        request.getSession().setAttribute("balloonId", balloon.getId());
        return "addBalloon";

    }

    @PostMapping("/orders")
    public String getOrdersPage(HttpServletRequest request, Model model) {
        Long id = (Long) request.getSession().getAttribute("cartId");
        List<Order> orders = this.shoppingCartService.listAllOrders(id);
        request.getSession().setAttribute("orders", orders);

        return "userOrders";
    }

    @PostMapping("/submitOrder")
    public String submitOrder(HttpServletRequest request, Model model) {
        Balloon balloon = (Balloon) request.getSession().getAttribute("balloon");
        ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("userCart");
        Order currenOrder = (Order) request.getSession().getAttribute("order");
        this.orderService.save(currenOrder);

        this.shoppingCartService.addOrder(this.orderService.findById(currenOrder.getOrderId()).orElse(null), cart.getId());


        return "redirect:/balloons/home";
    }

    @GetMapping("/logout")
    public String logoutUser(HttpServletRequest request, Model model) {
        request.getSession().invalidate();
        return "redirect:/balloons/login";
    }
}
