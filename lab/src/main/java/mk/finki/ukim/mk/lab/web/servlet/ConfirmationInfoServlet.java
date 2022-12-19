package mk.finki.ukim.mk.lab.web.servlet;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.service.BaloonService;
import mk.finki.ukim.mk.lab.service.OrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ConfirmationServlet",urlPatterns = "/ConfirmationInfo")
public class ConfirmationInfoServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final OrderService orderService;
    private final BaloonService baloonService;

    public ConfirmationInfoServlet(SpringTemplateEngine springTemplateEngine,
                                   OrderService orderService,
                                   BaloonService baloonService) {
        this.springTemplateEngine = springTemplateEngine;
        this.orderService = orderService;
        this.baloonService = baloonService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext =  new WebContext(req,resp, req.getServletContext());

        String clientName=(String) req.getSession().getAttribute("clientName");
        String clientAddress=(String) req.getSession().getAttribute("clientAddress");



        webContext.setVariable("clientName",clientName);
        webContext.setVariable("clientAddress",clientAddress);
        req.getSession().setAttribute("clientIP",req.getRemoteAddr());
        req.getSession().setAttribute("clientBrowser",req.getHeader("User-Agent"));


        springTemplateEngine.process("confirmationInfo.html",webContext,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect("/balloons");
    }
}
