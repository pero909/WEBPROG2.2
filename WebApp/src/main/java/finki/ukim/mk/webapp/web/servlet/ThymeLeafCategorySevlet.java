package finki.ukim.mk.webapp.web.servlet;

import finki.ukim.mk.webapp.service.CategoryService;
import finki.ukim.mk.webapp.service.impl.CategoryServiceImp;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "thymeleaf-category-servlet",urlPatterns = "/servlet/thymeleaf/category")
public class ThymeLeafCategorySevlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final CategoryService categoryService;

    public ThymeLeafCategorySevlet(SpringTemplateEngine springTemplateEngine
    ,CategoryServiceImp categoryService){

        this.springTemplateEngine=springTemplateEngine;
        this.categoryService=categoryService;

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context =  new WebContext(req,resp, req.getServletContext());
        context.setVariable("ipAdress",req.getRemoteAddr());
        context.setVariable("clientAgent",req.getHeader("User-Agent"));
        context.setVariable("categories",this.categoryService.listCategories());

        this.springTemplateEngine.process("categories.html",context,resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryName = req.getParameter("name");
        String categoryDesc =req.getParameter("desc");
        categoryService.create(categoryName,categoryDesc);
        resp.sendRedirect("/servlet/category");
    }
}
