package io.muic.ooc.webapp.servlet;

import com.mysql.jdbc.StringUtils;
import io.muic.ooc.webapp.Routable;
import io.muic.ooc.webapp.service.DatabaseUtil;
import io.muic.ooc.webapp.service.SecurityService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RegistServlet extends HttpServlet implements Routable {

    private SecurityService securityService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean authorized = securityService.isAuthorized(request);
        if (!authorized) {response.sendRedirect("/user");}
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/register.jsp");
        rd.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String Name = request.getParameter("name");
            String Email = request.getParameter("email");
            String LastName = request.getParameter("lastname");
            PrintWriter out = response.getWriter();

            System.out.println(username);
            if (!StringUtils.isEmptyOrWhitespaceOnly(username) && !StringUtils.isEmptyOrWhitespaceOnly(password) && !StringUtils.isEmptyOrWhitespaceOnly(Name) && !StringUtils.isEmptyOrWhitespaceOnly(Email) && !StringUtils.isEmptyOrWhitespaceOnly(LastName)) {
                if (DatabaseUtil.addUser(username, password, Name, Email, LastName)) {
                    out.println("create account successful");
                    response.sendRedirect("/login");
                } else {
                    String error = "username is already taken!";
                    request.setAttribute("error", error);
                    RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/register.jsp");
                    rd.include(request, response);
                }
            } else {
                String error = "some information is missing.";
                request.setAttribute("error", error);
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/register.jsp");
                rd.include(request, response);
            }
    }
    @Override
    public String getMapping() {
        return "/register";
    }

    @Override
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
