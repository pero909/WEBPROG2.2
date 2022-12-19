package finki.ukim.mk.webapp.web.controller;

import finki.ukim.mk.webapp.model.Exceptions.InvalidArgumentsException;
import finki.ukim.mk.webapp.model.Exceptions.PasswordsDontMatchException;
import finki.ukim.mk.webapp.model.Role;
import finki.ukim.mk.webapp.service.AuthService;
import finki.ukim.mk.webapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final AuthService authService;
    private final UserService userService;



    public RegisterController(AuthService authService, UserService userService) {
        this.authService = authService;


        this.userService = userService;
    }

    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam Role role){
        try{
            this.userService.register(username,password,repeatedPassword,name,surname,role);
            return "redirect:/login";
        }catch(PasswordsDontMatchException | InvalidArgumentsException ex){
            return "redirect:/register?error=" + ex.getMessage();
        }


    }

    @GetMapping
    public String getRegisterPager(@RequestParam(required = false) String error, Model model){
        if(error !=null && !error.isEmpty() ){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        model.addAttribute("bodyContent","register");
        return "master-template";
    }
}
