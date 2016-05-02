/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import db.data.entity.Comic;
import db.data.entity.User;
import java.io.File;
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
@WebServlet(name = "ComicController", urlPatterns = {"/ComicController",
                                                      "/addToRoundup",
                                                      "/searchSite",
                                                      "/addComic",
                                                      "/deletePreviewImage"})
public class ComicController extends HttpServlet {
 
Comic comic = new Comic();
User user = new User();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                        HttpSession session = request.getSession();
            
                        String urlPattern = request.getServletPath();
                        if(urlPattern.equals("/searchSite")) {
                            try {
                             String filter = request.getParameter("filter");
                             String searchQuery = request.getParameter("search");
                             if(filter.equals("site")) {
                                response.sendRedirect("searchcomic.jsp?search=" + searchQuery);
                             } else if(filter.equals("userbox")) {
                                 String username = (String) session.getAttribute("username");
                                 int boxID = user.getUserBoxID(username, searchQuery);
                                 if(boxID != -1)
                                    response.sendRedirect("box.jsp?boxID=" + boxID + "&boxname=" + searchQuery);
                                 else 
                                     response.sendRedirect("index.jsp");
                             } else if(filter.equals("roundup")) {  
                                 String username = (String) session.getAttribute("username");
                                 List<String> comicName = user.getUserSearchedComicName(username, searchQuery);
                                 List<String> comicID = user.getUserSearchedComicID(username, searchQuery);
                                 List<String> issueNumber = user.getUserSearchedComicIssue(username, searchQuery);
                                 List<String> boxName = user.getUserSearchedBoxName(username, searchQuery);
                                 List<String> boxID = user.getUserSearchedBoxID(username, searchQuery);
                                 if(comicName != null && comicID != null && issueNumber != null && boxName != null && boxID != null) {
                                    session.setAttribute("comicName", comicName);
                                    session.setAttribute("comicID", comicID);
                                    session.setAttribute("issueNumber", issueNumber);
                                    session.setAttribute("boxName", boxName);
                                    session.setAttribute("boxID", boxID);
                                    response.sendRedirect("usercomic.jsp");
                                 } else {
                                     response.sendRedirect("index.jsp");
                                 }
                             }
                            } catch(NullPointerException ex) {
                                String searchQuery = request.getParameter("search");
                                response.sendRedirect("searchcomic.jsp?search=" + searchQuery);
                            }
                        }
     
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                        HttpSession session = request.getSession();
            
                        String urlPattern = request.getServletPath();
                        try {
                        if(urlPattern.equals("/addToRoundup")) {
                             String comics[] = request.getParameterValues("comics");  //Gets the comicID's as string
                             String boxID = request.getParameter("box");
                             for(String comicID : comics) {
                                 comic.addComictoInventory(Integer.parseInt(comicID), Integer.parseInt(boxID));
                             }  
                             response.sendRedirect("controlpanel.jsp");
                        }
                        } catch(NullPointerException ex) {
                            //String searchQuery = request.getParameter("search");
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
                session.setAttribute("comicsuccess", "true");
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
