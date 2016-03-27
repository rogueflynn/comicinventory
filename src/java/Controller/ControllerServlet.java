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
                                                       "/createAccount",
                                                       "/createBox",
                                                       "/deleteBox"}
           )
public class ControllerServlet extends HttpServlet {
  DBConnect connection = new DBConnect();

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

    /* doPost listens for any posts requests */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            HttpSession session = request.getSession();
            
            String urlPattern = request.getServletPath();
            
            //This section handles creating an account
            if(urlPattern.equals("/createAccount")) {
                String un, pw, email;
                connection.openConnection();
                un = request.getParameter("username");
                pw = request.getParameter("pass");
                email = request.getParameter("email");
                
                connection.createAccount(un, pw, email);
                connection.closeConnection();
                response.sendRedirect(("controlpanel.jsp"));
            } 
            
            //This section handles creating a box
            if(urlPattern.equals("/createBox")) {
                String boxName, username;
                boxName = request.getParameter("boxName");
                username = (String) session.getAttribute("username");
                connection.openConnection();
                connection.createBox(username, boxName);
                connection.closeConnection();
                response.sendRedirect(("controlpanel.jsp"));
            }  
            
                //This section handles creating a box
            if(urlPattern.equals("/deleteBox")) {
                connection.openConnection();
                connection.deleteBox(20);
                connection.closeConnection();
                response.sendRedirect(("controlpanel.jsp"));
            }  
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
