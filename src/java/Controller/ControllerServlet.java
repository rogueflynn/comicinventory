/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.connect.mysql.DBConnect;

/**
 *
 * @author Victor
 */
@WebServlet(name = "ControllerServlet", 
                                        urlPatterns = {"/ControllerServlet",
                                                       "/login",
                                                       "/createAccount"}
           )
public class ControllerServlet extends HttpServlet {
  DBConnect connection = new DBConnect();


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
		connection.openConnection();
		//Validate before setting the session
		//session.setAttribute("fname", fname);
		if(connection.validate(username, password) == true) {
                        connection.closeConnection();
			session.setAttribute("username", username);
			response.sendRedirect("controlpanel.jsp");
		} else {
                        connection.closeConnection();
			response.sendRedirect("index.jsp");
		}
          }    
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            HttpSession session = request.getSession();
            String un, pw, email;
            String urlPattern = request.getServletPath();
            
            if(urlPattern.equals("/createAccount")) {
                connection.openConnection();
                un = request.getParameter("username");
                pw = request.getParameter("pass");
                email = request.getParameter("email");
                
                connection.createAccount(un, pw, email);
                connection.closeConnection();
                response.sendRedirect(("controlpanel.jsp"));
            } 
                   
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
