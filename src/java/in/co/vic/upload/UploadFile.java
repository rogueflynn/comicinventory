/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.co.vic.upload;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
//import org.apache.tomcat.util.http.fileupload.FileItem;

/**
 *
 * @author Victor
 */
@WebServlet(name = "UploadFile", urlPatterns = {"/UploadFile"})
public class UploadFile extends HttpServlet {

   String saveFile = "C:/Users/Victor/Documents/comicinventory/web/comicImages";
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(); 
       
        
        response.setContentType("text/html;charset=UTF-8");
   
        String url = null;
            
        try (PrintWriter out = response.getWriter()) {
           boolean ismultipart = ServletFileUpload.isMultipartContent(request);
           if(!ismultipart) {
               
           } else {
               FileItemFactory factory = new DiskFileItemFactory();
               ServletFileUpload upload = new ServletFileUpload(factory);
               List items = null;
               
               try {
                   items = upload.parseRequest(request);
               } catch(Exception e) {
                   
               }
               Iterator itr = items.iterator();
               while(itr.hasNext()) {
                   FileItem item = (FileItem) itr.next();
                   
                   if(item.isFormField()) {
                   
                   } else {
                       String itemname = item.getName();
                       if((itemname==null) || itemname.equals("")) {
                           continue;
                       }
                       String filename = FilenameUtils.getName(itemname);
                       File f = checkExist(filename);
                       item.write(f);
                       url= filename;
                       session.setAttribute("url", url);
                       response.sendRedirect("addcomic.jsp");
                   }
               }
           }
        } catch(Exception e) {
            
        }
    }
 
    private File checkExist(String fileName) {
        File f = new File(saveFile+"/"+fileName);
        
        if(f.exists()) {
            StringBuffer sb = new StringBuffer(fileName);
            sb.insert(sb.lastIndexOf("."), "-"+new Date().getTime());
            f = new File(saveFile+"/"+sb.toString());
        }
        return f;
    }

}
