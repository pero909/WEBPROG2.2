package finki.ukim.mk.webapp.web.controller;

import finki.ukim.mk.webapp.model.Exceptions.InvalidUserCredentialsException;
import finki.ukim.mk.webapp.model.User;
import finki.ukim.mk.webapp.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String getLoginPage(Model model){
        model.addAttribute("bodyContent","login");
        return "master-template";
    }
    @PostMapping
    public String login(HttpServletRequest request, Model model){
        User user = null;

        try{
            user = this.authService.login(request.getParameter("username"),
                    request.getParameter("password"));
            request.getSession().setAttribute("user",user);
            return "redirect:/home";
        }catch(InvalidUserCredentialsException ex){
             model.addAttribute("hasError",true);
             model.addAttribute("error",ex.getMessage());
             return "login";
        }
    }
}
