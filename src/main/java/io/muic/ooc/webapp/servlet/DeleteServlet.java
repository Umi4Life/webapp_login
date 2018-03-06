package io.muic.ooc.webapp.servlet;

import io.muic.ooc.webapp.database.MySQL;
import io.muic.ooc.webapp.database.User;
import io.muic.ooc.webapp.Routable;
import io.muic.ooc.webapp.service.SecurityService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteServlet extends HttpServlet implements Routable {
    private SecurityService securityService;
    @Override
    public String getMapping() {
        return "/delete";
    }

    @Override
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        boolean authorized = securityService.isAuthorized(request);
        String currentUsername = (String) request.getSession().getAttribute("username");
        String deletingUsername = request.getParameter("username");
        if (!authorized) {
            response.sendRedirect("/");
            return;
        }
        if(currentUsername.equals(deletingUsername)){
            String error = "Cannot delete own account.";
            request.setAttribute("error", error);
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/users.jsp");
            rd.include(request, response);
            return;
        }
        MySQL.removeUser(deletingUsername);
        response.sendRedirect("/");


    }
}
