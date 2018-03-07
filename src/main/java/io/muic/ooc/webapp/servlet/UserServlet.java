package io.muic.ooc.webapp.servlet;

import io.muic.ooc.webapp.Routable;
import io.muic.ooc.webapp.service.DatabaseUtil;
import io.muic.ooc.webapp.service.SecurityService;
import io.muic.ooc.webapp.service.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserServlet extends HttpServlet implements Routable {

    private SecurityService securityService;

    @Override
    public String getMapping() {
        return "/user";
    }

    @Override
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean authorized = securityService.isAuthorized(request);
        System.out.println("isAuthorised: " + authorized);
        System.out.println((String) request.getSession().getAttribute("username"));
        if (authorized) {
            // do MVC in here
            String username = (String) request.getSession().getAttribute("username");
            request.setAttribute("username", username);
            List<User> userList = DatabaseUtil.getUser();
            request.setAttribute("userList",userList);
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/home.jsp");
            rd.include(request, response);
        } else {
            response.sendRedirect("/login");
        }
    }
}
