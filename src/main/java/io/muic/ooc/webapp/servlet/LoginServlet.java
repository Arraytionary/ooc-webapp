package io.muic.ooc.webapp.servlet;

import io.muic.ooc.webapp.service.SecurityService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import io.muic.ooc.webapp.Routable;

public class LoginServlet extends HttpServlet implements Routable {

    private SecurityService securityService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean authorized = securityService.isAuthorized(request);
        if (authorized) {response.sendRedirect("/user");}
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/login.jsp");
        rd.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // do login post logic
        // extract username and password from request
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/user?useSSL=false","root","twpwbq71");
//

            String username = request.getParameter("username");
            String password = request.getParameter("password");
//
////        String sql = "insert into user(UserName,Password) values(?,?)";
////        PreparedStatement ps = con.prepareStatement(sql);
////        ps.setString(1,username);
////        ps.setString(2,password);
////        ps.executeUpdate();
////        PrintWriter out = response.getWriter();
////        out.println("successfully login!");
//
//            String sql = "select * from USERINF where UserName=? and Password=?";
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setString(1,username);
//            ps.setString(2,password);
//            ResultSet rs = ps.executeQuery();
//            PrintWriter out = response.getWriter();
//
//            String dName = null;
//            String dbPassword = null;
//            while(rs.next()){
//                dName = rs.getString(1);
//                dbPassword = rs.getString(2);
//                System.out.println(dName);
//                System.out.printf("%s\n",dbPassword);
//            }
//            System.out.println(dName);
//            System.out.printf("%s\n",dbPassword);
//
//            if(username.equals(dName)&&password.equals(dbPassword)){
//                out.println("successfully login");
//                request.getSession().setAttribute("username", username);
//                response.sendRedirect("/");
//            }
//
//            else{
//                response.sendRedirect("login.jsp");
//            }
//
//
//            out.println("successfully login!");


            if (!StringUtils.isBlank(username) && !StringUtils.isBlank(password)) {
                if (securityService.authenticate(username, password, request)) {
                    response.sendRedirect("/");
                } else {
                    String error = "Wrong username or password.";
                    request.setAttribute("error", error);
                    RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/login.jsp");
                    rd.include(request, response);
                }
            } else {
                String error = "Username or password is missing.";
                request.setAttribute("error", error);
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/login.jsp");
                rd.include(request, response);
            }

        // check username and password against database
        // if valid then set username attribute to session via securityService
        // else put error message to render error on the login form

    }

    @Override
    public String getMapping() {
        return "/login";
    }

    @Override
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
