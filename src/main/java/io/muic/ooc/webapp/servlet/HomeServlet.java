/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.muic.ooc.webapp.servlet;

import io.muic.ooc.webapp.Routable;
import io.muic.ooc.webapp.service.DatabaseUtil;
import io.muic.ooc.webapp.service.SecurityService;
import io.muic.ooc.webapp.service.User;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gigadot
 */
public class HomeServlet extends HttpServlet implements Routable {

    private SecurityService securityService;

    @Override
    public String getMapping() {
        return "/index.jsp";
    }

    @Override
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //boolean authorized = securityService.isAuthorized(request);
        //System.out.println("isAuthorised: " + authorized);
//        if (authorized) {
//            // do MVC in here
//            String username = (String) request.getSession().getAttribute("username");
//            request.setAttribute("username", username);
//            List<User> userList = DatabaseUtil.getUser();
//            request.setAttribute("userList",userList);
//            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/home.jsp");
//            rd.include(request, response);
//        } else {
//            response.sendRedirect("/login");
//        }
        response.sendRedirect("/user");
    }
}
