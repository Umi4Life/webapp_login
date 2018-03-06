package io.muic.ooc.webapp.servlet;

import io.muic.ooc.webapp.database.MySQL;
import io.muic.ooc.webapp.Routable;
import io.muic.ooc.webapp.service.SecurityService;
import org.apache.commons.lang.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/edit")
public class EditServlet extends HttpServlet implements Routable {
    private SecurityService securityService;
    private String toEdit;
    @Override
    public String getMapping() {
        return "/edit";
    }

    @Override
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        toEdit = request.getParameter("toEdit");
        request.setAttribute("toEdit", toEdit);
        if(StringUtils.isBlank(toEdit)||!securityService.isAuthorized(request)){
            response.sendRedirect("/users");
        }
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/edit.jsp");
        rd.include(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String email = request.getParameter("email").trim();
        String password = request.getParameter("password").trim();

        Boolean blankFields =
                StringUtils.isBlank(email) ||
                        StringUtils.isBlank(password) ;
        if (!blankFields) {
            MySQL.editUser(toEdit, password,email);
            response.sendRedirect("/users");
        } else {
            String error = "Please fill in all the fields.";
            request.setAttribute("error", error);
            request.setAttribute("toEdit", toEdit);
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/edit.jsp");
            rd.include(request, response);
        }


    }
}
