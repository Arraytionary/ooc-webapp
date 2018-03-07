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

public class DeleteServlet extends HttpServlet implements Routable {

    private SecurityService securityService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/delete.jsp");
        //rd.include(request, response);
        if(DatabaseUtil.deleteUser(request.getParameter("username"),(String)(request.getSession().getAttribute("username")))){
            response.sendRedirect("/");
        }
        else{
            String error = "Cannot remove your own user.";
            request.setAttribute("error", error);
            request.setAttribute("username", (String)(request.getSession().getAttribute("username")));
            List<User> userList = DatabaseUtil.getUser();
            request.setAttribute("userList",userList);
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/home.jsp");
            rd.include(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println(request.getParameter("username"));

    }
    @Override
    public String getMapping() {
        return "/delete";
    }

    @Override
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}