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
                                                   "/deleteBoxes",
                                                   "/createBox",
                                                   "/updateBoxName"})
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
                if(!(boxName.equals(""))) {
                    box.createBox(username, boxName);
                    response.sendRedirect(("controlpanel.jsp"));
                } else {
                    response.sendRedirect(("controlpanel.jsp"));
                }
            }  
            
                //This section handles creating a box
            if(urlPattern.equals("/deleteBoxes")) {
                //Gets the boxIDs as string
                try {
                    String userboxes[] = request.getParameterValues("userboxes");
                    for(String boxID : userboxes) 
                        box.deleteBox(Integer.parseInt(boxID));
            
                    response.sendRedirect(("controlpanel.jsp"));
                } catch(java.lang.NullPointerException ex) {
                    response.sendRedirect(("controlpanel.jsp"));
                }
            }
            if(urlPattern.equals("/updateBoxName")) {
                try{
                    String boxName = (String) request.getParameter("boxName");
                    int boxId = Integer.parseInt(request.getParameter("boxID"));
                    box.updateBoxName(boxId, boxName);
                    session.setAttribute("boxName", boxName);
                    session.setAttribute("boxID", boxId);
                    response.sendRedirect(("box.jsp?boxID=" + boxId + "&boxname=" + boxName));
               
                } catch(java.lang.NullPointerException ex) {
                    String boxName = (String) session.getAttribute("boxName");
                    String boxID = (String) session.getAttribute("boxID");
                    response.sendRedirect(("box.jsp?boxID=" + boxID + "&boxname=" + boxName));
                }
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
