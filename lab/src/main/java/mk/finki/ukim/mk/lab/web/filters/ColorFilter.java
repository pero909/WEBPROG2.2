package mk.finki.ukim.mk.lab.web.filters;

import mk.finki.ukim.mk.lab.model.Order;
import org.springframework.context.annotation.Profile;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
@Profile("servlet")
public class ColorFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;


     //  String color= (String) request.getParameter("color");
   //    String path= request.getServletPath();
  //     String color = (String) request.getSession().getAttribute("balloonColor");

   //    filterChain.doFilter(servletRequest,servletResponse);

  //     if(color==null&&"/balloons/add-form".equals(path)){
    //       filterChain.doFilter(servletRequest,servletResponse);
    //   }if(color==null&&!"/balloons/add-form".equals(path)&&!"/balloons".equals(path)){
    //       response.sendRedirect("/balloons");
   //     }else {
        //    filterChain.doFilter(servletRequest, servletResponse);
 //       }
     //  if(!"".equals(path)&&color==null&&!"/balloons".equals(path)){
       //    response.sendRedirect("");
     //  }else{
     //      filterChain.doFilter(servletRequest,servletResponse);
     //  }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
