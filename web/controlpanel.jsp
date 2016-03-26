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
           rs = con.getUserComics(username);
           while(rs.next()) {
               out.println("Comic: " + rs.getString("comicName") + "<br>");
               out.println("Issue # " + rs.getString("issueNumber") + "<br>");
               out.println("Publisher: " + rs.getString("publisher") + "<br>");
               out.println("Print: " + rs.getString("printing") + "<br>");
               out.println("Photo: " + rs.getString("photo") + "<br>");
               out.println("Release Date: " + rs.getString("yearReleased") + "<br>");
               out.println("<br>");
           }
           con.closeConnection();
        %>
       
