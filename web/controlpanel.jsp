    <title>Control Panel</title>
    </head>
    <body>
        
      <h1>Control Panel</h1>
        <% 
	    //This piece of code will test whether there is an active session in the browser.
            //If there no active session, the page will redirect back to the index page
	   if(username == null)
	    	response.sendRedirect("index.jsp"); 
	%>
       
        <% 
           con.openConnection();
           con2.openConnection();
           rs = con.getUserBoxes(username);
           out.println("Round Up:<br>");
           while(rs.next()) {
               /*Box title*/
               out.println("<p><a href=\"#\" class=\"toggleLink\" id=\"comiclist\">");
               out.println("<span>+</span>");
               out.println("<span style=\"display:none\">-</span>");
               out.println("</a>");
               out.println("<a href=\"#\" id=\"comiclist\">");
               out.println("Box: " + rs.getString("boxTracker") + "</a></p>");
               int boxID = Integer.parseInt(rs.getString("boxID"));
               comic = con2.getUserComicsinBox(boxID);
               
               /*Comic list*/
               out.println("<ul class=\"toggle\">");
               while(comic.next()) {
                   out.println("<li>");
                   out.println(comic.getString("comicName") + " #" + comic.getString("issueNumber"));
                   out.println("</li>");
               }
               out.println("</ul>");
           }
     
           con.closeConnection();
           con2.closeConnection();
        %>
        <form action="createBox" method="post">
            <input type="text" name="boxName" id="username" />
            <input type="submit" value="Create Box" />
        </form>
        <!--Delete box code
        <form action="deleteBox" method="post">
            <input type="submit" value="Delete Box" />
        </form> -->
