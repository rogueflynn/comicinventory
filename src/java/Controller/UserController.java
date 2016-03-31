/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import db.data.entity.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Victor
 */
@WebServlet(name = "ControllerServlet", 
                                        urlPatterns = {"/ControllerServlet",
                                                       "/login",
                                                       "/createAccount"})
public class UserController extends HttpServlet {
  User user = new User();

    /*doGet listens for and get requests */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          HttpSession session = request.getSession();
          String urlPattern = request.getServletPath();
          
          //This section handles the login request.
          //If the user does not exist in the database
          //send them back to the current else
          //redirect them  to their control panel.
          if(urlPattern.equals("/login")) {
                String username, password;
		
                password = request.getParameter("password");
		username = request.getParameter("username");
		//Validate before setting the session
		//session.setAttribute("fname", fname);
		if(user.validate(username, password) == true) {
			session.setAttribute("username", username);
			response.sendRedirect("controlpanel.jsp");
		} else {
			response.sendRedirect("index.jsp");
		}
          }    
    }

    /* doPost listens for any posts requests */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            HttpSession session = request.getSession();
            
            String urlPattern = request.getServletPath();
            
            //This section handles creating an account
            if(urlPattern.equals("/createAccount")) {
                String un, pw, email;
                un = request.getParameter("username");
                pw = request.getParameter("pass");
                email = request.getParameter("email");
                
                user.createAccount(un, pw, email);
                response.sendRedirect(("controlpanel.jsp"));
            } 
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
