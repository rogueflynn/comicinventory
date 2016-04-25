       <h1>Control Panel</h1>
       <!--Delete boxes-->
       <form action="deleteBoxes" method="post">
       <input type="checkbox" id="selectall" />Select All
        <% 
	    //This piece of code will test whether there is an active session in the browser.
            //If there no active session, the page will redirect back to the index page
	   if(username == null)
	    	response.sendRedirect("createAccount.jsp"); 
	%>
       
        <% 
           
            //If the session is active, get the username
           //and display their comics and boxes
             hmap = user.getUserBoxes(username);
             set = hmap.entrySet();
             iterator = set.iterator();
             while(iterator.hasNext()) {
                Map.Entry mentry = (Map.Entry)iterator.next();
                /*Box title*/
               out.println("<p>  <input type=\"checkbox\" class=\"checkbox\" name=\"userboxes\""
                       + " value=\"" +(int) mentry.getKey() + "\" id=\"comics\"><a href=\"#\" class=\"toggleLink\" id=\"comiclist\">");
               out.println("<span>+</span>");
               out.println("<span style=\"display:none\">-</span>");
               out.println("</a>");
               out.println("<a href=\"box.jsp?boxID=" +(int) mentry.getKey() + "&boxname=" + mentry.getValue()+ "\" id=\"comiclist\">");
               out.println("Box: " + mentry.getValue() + "</a> <a href=\"delete.jsp?boxID=" +(int) mentry.getKey() + "\"> Delete</a></p>");
               usercomics = user.getUserComicsinBox((int) mentry.getKey());
                
               /*Comic list*/
               out.println("<ul class=\"toggle\">");
                for(String c : usercomics) {
                   out.println("<li>");
                   out.println(c);
                   out.println("</li>");
                }
                out.println("</ul>");
            }
   
        %>
        <input type="submit" value="Delete Boxes" />
       </form>
        
       <!--create box-->
        <form action="createBox" id="create_box_form" method="post">
            <input type="text" name="boxName" id="username" />
            <input type="submit" value="Create Box" />
        </form>
        <!--Delete box code
        <form action="deleteBox" method="post">
            <input type="submit" value="Delete Box" />
        </form> -->
