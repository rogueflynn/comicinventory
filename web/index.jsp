
 

<% out.println("<h1>Home</h1>"); %>
       
        <% 
        
           if(username == null) { //check to see if the user is logged on
        %>
       <form action="login" method="get">
		<p>Username:      <input type="text" name="username" /></p>
		<p>Password:   <input type="password" name="password" /> </p>
		<input type="submit" value="Login" />
	</form>
       
        <% } else {
              out.println("<br>" + username + " is logged in."); //Print user name is logged in
           }
        %>
        <%
           /*
          
             comicID = comicInfo.getComicsID("ca");
             for (int cID : comicID) {
                 out.println("Comic Name: " + comicInfo.getComicTitle(cID) + "<br>");
                 out.println("Issue #" + comicInfo.getComicIssue(cID) + "<br>");
                 out.println("Photo: " + comicInfo.getComicPhoto(cID) + "<br>");
            } 
                   */
          // out.println(comicInfo.getComicPhoto(1) + "<br>");
        
        %>