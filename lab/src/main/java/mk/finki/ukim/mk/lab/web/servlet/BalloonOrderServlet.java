package mk.finki.ukim.mk.lab.web.servlet;

import mk.finki.ukim.mk.lab.model.*;
import mk.finki.ukim.mk.lab.service.BaloonService;
import mk.finki.ukim.mk.lab.service.ManufacturerService;
import mk.finki.ukim.mk.lab.service.OrderService;
import mk.finki.ukim.mk.lab.service.ShoppingCartService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ballonOrderServlet",urlPatterns = "/BalloonOrder.do")
public class BalloonOrderServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final BaloonService baloonService;
    private final OrderService orderService;
    private final ManufacturerService manufacturerService;
    private final ShoppingCartService shoppingCartService;

    public BalloonOrderServlet(SpringTemplateEngine springTemplateEngine,
                               BaloonService baloonService, OrderService orderService, ManufacturerService manufacturerService, ShoppingCartService shoppingCartService) {
        this.springTemplateEngine = springTemplateEngine;
        this.baloonService =baloonService ;
        this.orderService = orderService;
        this.manufacturerService = manufacturerService;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext= new WebContext(req,resp, req.getServletContext());
        List<Manufacturer> manufacturerList=this.manufacturerService.findAll();
        req.getSession().setAttribute("manufacturers",manufacturerList);


        springTemplateEngine.process("deliveryInfo.html",webContext,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String clientAddress = req.getParameter("clientAddress");
        String name=(String)req.getParameter("balloonName");
        String description = (String) req.getParameter("balloonDesc");
        String balloonSize=(String) req.getSession().getAttribute("balloonSize");
        String balloonColor=(String) req.getSession().getAttribute("balloonColor");
        User user =(User) req.getSession().getAttribute("user");
        String username=user.getUsername();


        req.getSession().setAttribute("manId",1);
        req.getSession().setAttribute("userName",username);
        req.getSession().setAttribute("clientAddress",clientAddress);
        Balloon balloon= new Balloon(name,description,balloonColor,balloonSize,manufacturerService.findAll().get(0));
        this.baloonService.save(balloon);
        req.getSession().setAttribute("balloon",balloon);
        Order order=(Order)req.getSession().getAttribute("order");
        this.orderService.save(new Order(balloon.getBalloonColor(),balloon.getBalloonSize()));
        ShoppingCart shoppingCart=this.shoppingCartService.getCurrentUserCart(username);
        req.getSession().setAttribute("userCart",shoppingCart);
        req.getSession().setAttribute("cartId",shoppingCart.getId());
        req.getSession().setAttribute("order",order);


        resp.sendRedirect("/ConfirmationInfo");
    }
}
