/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comic.io.controller;

import com.connect.mysql.DBConnect;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ComicController", urlPatterns = {"/ComicController",
                                                      "/addToRoundup",
                                                      "/searchSite"})
public class ComicController extends HttpServlet {
DBConnect connection = new DBConnect(); 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                        HttpSession session = request.getSession();
            
                        String urlPattern = request.getServletPath();
                        if(urlPattern.equals("/searchSite")) {
                             String searchQuery = request.getParameter("search");
                             response.sendRedirect("searchcomic.jsp?search=" + searchQuery);
                        }
     
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                        HttpSession session = request.getSession();
            
                        String urlPattern = request.getServletPath();
                        if(urlPattern.equals("/addToRoundup")) {
                             String comics[] = request.getParameterValues("comics");  //Gets the comicID's as string
                             String boxID = request.getParameter("box");
                             connection.openConnection();
                             for(String comicID : comics) {
                                 connection.addComictoInventory(Integer.parseInt(comicID), Integer.parseInt(boxID));
                             }  
                             connection.closeConnection();
                             response.sendRedirect("controlpanel.jsp");
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
