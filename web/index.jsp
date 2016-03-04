    <title>Comic Inventory</title>
    </head>
    <body>
        
        <% out.println("<h1>Home</h1>"); %>
       
        <% 
           con.openConnection();
           out.print(con.testConnection());
           rs = con.getUser(1);
           while(rs.next()) {
               out.println(rs.getString("userName"));
           }
        %>
       