/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import db.data.entity.User;
import java.io.IOException;
import java.util.List;
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
                                                       "/createAccount",
                                                       "/deleteUserComic",
                                                       "/deleteMultipleUserComics",
                                                       "/deleteMultipleUserComicsFromSearch"})
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
                session.setAttribute("success", "true");
                response.sendRedirect(("controlpanel.jsp"));
            } 
            if(urlPattern.equals("/deleteUserComic")) {
             
              response.sendRedirect("delete.jsp");
            }
            if(urlPattern.equals("/deleteMultipleUserComics")) {
                        try {
                             int boxID = (Integer) session.getAttribute("boxID");
                             String boxName = (String) session.getAttribute("boxName");
                             //Gets the comicID's as string
                             String usercomics[] = request.getParameterValues("usercomics");
                             for(String comicID : usercomics) {
                              user.deleteComicInBox(boxID, Integer.parseInt(comicID));
                             }  
                            response.sendRedirect("box.jsp?boxID=" + boxID + "&boxname=" + boxName);
                        } catch(java.lang.NullPointerException ex) {
                            int boxID = (Integer) session.getAttribute("boxID");
                            String boxName = (String) session.getAttribute("boxName");
                            response.sendRedirect("box.jsp?boxID=" + boxID + "&boxname=" + boxName);
                        }
            }
            if(urlPattern.equals("/deleteMultipleUserComicsFromSearch")) {
                        try {
                             List<String> comicname = (List<String>)session.getAttribute("comicName");
                             List<String> comicid = (List<String>) session.getAttribute("comicID");
                             List<String> issuenumber = (List<String>) session.getAttribute("issueNumber");
                             List<String> boxname = (List<String>) session.getAttribute("boxName");
                             List<String> boxid = (List<String>) session.getAttribute("boxID");
                         
                             //Gets the comicID's as string
                             String usercomics[] = request.getParameterValues("usercomics");
                             for(String index : usercomics) {
                                    user.deleteComicInBox(
                                                    Integer.parseInt(boxid.get(Integer.parseInt(index))),
                                                    Integer.parseInt(comicid.get(Integer.parseInt(index)))
                                                   );
                                    int i = Integer.parseInt(index);
                                    comicname.remove(i);
                                    comicid.remove(i);
                                    issuenumber.remove(i);
                                    boxname.remove(i);
                                    boxid.remove(i);
                             }  
                             session.setAttribute("comicName", comicname);
                             session.setAttribute("comicID", comicid);
                             session.setAttribute("issueNumber", issuenumber);
                             session.setAttribute("boxName", boxname);
                             session.setAttribute("boxID", boxid);
                            response.sendRedirect("usercomic.jsp");
                        } catch(java.lang.NullPointerException ex) {
                              response.sendRedirect("usercomic.jsp");
                        }
            }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
