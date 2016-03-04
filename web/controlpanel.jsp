    <title>Control Panel</title>
    </head>
    <body>
        
        <% out.println("<h1>Control Panel</h1>"); %>
       
        <% 
           con.openConnection();
           out.print(con.testConnection());
           rs = con.getUser(3);
           while(rs.next()) {
               out.println(rs.getString("userName"));
           }
        %>
       
