/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import db.data.entity.Box;
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
@WebServlet(name = "BoxController", urlPatterns = {"/BoxController",
                                                   "/deleteBox",
                                                   "/createBox"})
public class BoxController extends HttpServlet {

    Box box = new Box();

     /*doGet listens for and get requests */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }


    /* doPost listens for any posts requests */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          HttpSession session = request.getSession();
          String urlPattern = request.getServletPath();
       //This section handles creating a box
            if(urlPattern.equals("/createBox")) {
                String boxName, username;
                boxName = request.getParameter("boxName");
                username = (String) session.getAttribute("username");
                box.createBox(username, boxName);
                response.sendRedirect(("controlpanel.jsp"));
            }  
            
                //This section handles creating a box
            if(urlPattern.equals("/deleteBox")) {
                box.deleteBox(20);
                response.sendRedirect(("controlpanel.jsp"));
            }  
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
