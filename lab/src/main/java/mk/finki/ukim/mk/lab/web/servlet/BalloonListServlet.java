package mk.finki.ukim.mk.lab.web.servlet;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Exceptions.ColorNotChosenException;
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

@WebServlet(name = "home",urlPatterns = "")
public class BalloonListServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final BaloonService baloonService;
    private final OrderService orderService;

    public BalloonListServlet(SpringTemplateEngine springTemplateEngine,
                              BaloonService baloonService,
                              OrderService orderService) {
        this.springTemplateEngine = springTemplateEngine;
        this.baloonService = baloonService;
        this.orderService=orderService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext =  new WebContext(req,resp, req.getServletContext());
        webContext.setVariable("balloons",this.baloonService.listAll());

        this.springTemplateEngine.process("listBalloons",webContext,resp.getWriter());


    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String balloonColor = req.getParameter("color");
        WebContext webContext= new WebContext(req,resp, req.getServletContext());
           req.getSession().setAttribute("balloonColor",balloonColor);
           resp.sendRedirect("/selectBalloon");

    }
}
