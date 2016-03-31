/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import db.data.entity.Comic;
import java.io.File;
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
@WebServlet(name = "ComicController", urlPatterns = {"/ComicController",
                                                      "/addToRoundup",
                                                      "/searchSite",
                                                      "/addComic",
                                                      "/deletePreviewImage"})
public class ComicController extends HttpServlet {
 
Comic comic = new Comic();

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
                             for(String comicID : comics) {
                                 comic.addComictoInventory(Integer.parseInt(comicID), Integer.parseInt(boxID));
                             }  
                             response.sendRedirect("controlpanel.jsp");
                        }
                        //This section handles adding a comic
            if(urlPattern.equals("/addComic")) {
                String comicName, issueNumber, publisher, url, year, printing;
                comicName = request.getParameter("comicname");
                issueNumber = request.getParameter("issuenumber");
                publisher = request.getParameter("publisher");
                year = request.getParameter("year");
                url = (String) session.getAttribute("url");  //Saves the image name in a variable.
                printing = request.getParameter("printing");
                int print = Integer.parseInt(printing);
                comic.addComic(comicName, issueNumber, publisher, url, year, print);
                session.removeAttribute("url");
                response.sendRedirect(("addcomic.jsp"));
            } 
            
            //Delete preview image
            if(urlPattern.equals("/deletePreviewImage")) {
                String url = (String) session.getAttribute("url");
                String saveFile = "C:\\Users\\Victor\\Documents\\comicinventory\\web\\comicImages\\" + url;
                
                File file = new File(saveFile);
                
                //Deletes the preview image from the directory 
                //it resides in.
                //If te deletion is successful, remove the 
                //image session.
                if(file.delete()) {
                    //Remove the session
                    session.removeAttribute("url");
                    response.sendRedirect(("addcomic.jsp"));
                } else {
                    System.out.println("Error");
                    response.sendRedirect(("addcomic.jsp"));
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
