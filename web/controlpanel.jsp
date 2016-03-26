    <title>Control Panel</title>
    </head>
    <body>
        
      <h1>Control Panel</h1>
      <p><a href="#" id="comiclist">Test</a></p>
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
           out.println("User Boxes:<br>");
           while(rs.next()) {
            
          %>
          <%
               out.println("Box: " + rs.getString("boxTracker") + "<br>");
               
               int boxID = Integer.parseInt(rs.getString("boxID"));
               comic = con2.getUserComicsinBox(boxID);
               
               out.println("<ul class=\"toggle\">");
               while(comic.next()) {
                   out.println("<li>");
                   out.println(comic.getString("comicName") + " #" + comic.getString("issueNumber"));
                   out.println("</li>");
               }
               out.println("</ul>");
           }
           
           /*
           rs = con.getUserComics(username);
           out.println("User comics:<br>");
           while(rs.next()) {
               out.println("Comic: " + rs.getString("comicName") + "<br>");
               out.println("Issue # " + rs.getString("issueNumber") + "<br>");
               out.println("Publisher: " + rs.getString("publisher") + "<br>");
               out.println("Print: " + rs.getString("printing") + "<br>");
               out.println("Photo: " + rs.getString("photo") + "<br>");
               out.println("Release Date: " + rs.getString("yearReleased") + "<br>");
               out.println("<br>");
           }
           */
           con.closeConnection();
           con2.closeConnection();
        %>
       
