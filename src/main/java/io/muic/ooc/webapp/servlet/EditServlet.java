package io.muic.ooc.webapp.servlet;

import io.muic.ooc.webapp.Routable;
import io.muic.ooc.webapp.service.DatabaseUtil;
import io.muic.ooc.webapp.service.Encryption;
import io.muic.ooc.webapp.service.SecurityService;

import org.apache.commons.lang3.StringUtils;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class EditServlet extends HttpServlet implements Routable {

    private SecurityService securityService;
    private String userToEdit;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userToEdit = request.getParameter("toEdit");
//        System.out.println(username+"star dust");
        request.setAttribute("toEdit",userToEdit);
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/edit.jsp");
        rd.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println(request.getParameter("toEdit"));

//        String username = request.getParameter("user");
        String password = request.getParameter("password");
        String Name = request.getParameter("name");
        String Email = request.getParameter("email");
        String LastName = request.getParameter("lastname");
        PrintWriter out = response.getWriter();
        System.out.println(userToEdit+" gg wp");
        System.out.println(password+"pass");
        if(!StringUtils.isAllBlank(password) && !StringUtils.isAllBlank(Name) && !StringUtils.isAllBlank(Email) && !StringUtils.isAllBlank(LastName)) {
            if(DatabaseUtil.editUser(userToEdit, Encryption.encryptPassword(password), Name, Email, LastName)){
                System.out.println("create account successful");
                response.sendRedirect("/user");
            }
//            else{
//                String error = "username is already exist!";
//                request.setAttribute("error", error);
//                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/register.jsp");
//                rd.include(request, response);
//            }
        }
        else{
            String error = "some information is missing.";
            request.setAttribute("error", error);
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/edit.jsp");
            rd.include(request, response);
        }

    }
    @Override
    public String getMapping() {
        return "/edit";
    }

    @Override
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}