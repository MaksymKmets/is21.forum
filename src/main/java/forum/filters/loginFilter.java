package forum.filters;


import forum.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by Макс on 31.07.14.
 */
public class loginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response =(HttpServletResponse) servletResponse;
        User session =(User)request.getSession().getAttribute("user");
        String url =request.getRequestURI();

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0); // Proxies.

        //if not login
        if(session == null)
        {
            //if not login and look on security files
            if(url.indexOf("/content/")>=0)
            {
                response.sendRedirect(request.getContextPath()+"/security/login.xhtml");
            }
            else
            {
                filterChain.doFilter(request,response);
            }
        }
        else
        {
            //if login and look for security file
            if (url.indexOf("register.xhtml")>=0 || url.indexOf("/login.xhtml")>=0)
            {
                response.sendRedirect(request.getContextPath() + "../content/news.xhtml");
            }
            else
            //if log out
                if (url.indexOf("logout.xhtml")>=0)
                {
                    request.getSession().removeAttribute("user");
                    response.sendRedirect(request.getContextPath()+"/login.xhtml");
                }
            else
                {
                    filterChain.doFilter(request,response);
                }
        }
    }

    @Override
    public void destroy() {

    }
}
