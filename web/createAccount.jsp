<%
    String accountCreated = (String) session.getAttribute("success");
    if(accountCreated != null) {
%>
<script>alert("Account created succesfully. Please login to continue");</script>
<% 
     accountCreated = null;  
     session.setAttribute("success", null);
    }
%>

<% if(username == null) { //check to see if the user is logged on%> 
<div id="createForm">
    <div id="loginHeader">Sign up!</div>
    <form action="createAccount" id="create_form" method="post" >
        <fieldset>
            <div id="inputMargin">   
                <p>
                    <label for="username"><div id="rLabel">Username:</div></label> 
                    <input type="text" name="username" id="username" />
                </p>
               
            </div>
            <div id="inputMargin"> 
            <p>
                <label for="pass"><div id="rLabel">Password:</div></label>   
                <input type="password" name="pass" id="pass" /> 
            </p>
            </div>
             <div id="inputMargin"> 
            <p>
                <label for="confirm_pass"><div id="rLabel">Confirm Password:</div></label> 
                <input type="password" name="confirm_pass" id="confirm_pass" />
            </p>
             </div>
            <div id="inputMargin"> 
            <p>
                <label for="email"><div id="rLabel">Email:</div> </label> 
                <input type="email" name="email" id="email" />
            </p>
             </div>
            <p><input type="submit" value="Sign-Up" id="rSubmit"/></p>
        </fieldset>
    </form>

</div>

<div id="loginForm">
    <div id="loginHeader">Login</div>
    
       <form action="login" method="get" id="login_form">
           <fieldset>
                
          <div id="inputMargin"> 
         
           <p>
               <label for="username"><div id="rLabel">Username:</div></label>
               <input type="text" name="username" /><br/>
               <span id="invalid-username"></span>
           </p>
            
           </div>
           <div id="inputMargin">
           <p>
               <label for="pass"><div id="rLabel">Password:</div></label>
               <input type="password" name="password" />
           </p>
           </div>
           <input type="submit" value="Login" />
           </fieldset>
	</form>
    <div id="hRule"></div>
</div>      
        <!-- REDIRECT -->
        <% } else {
             response.sendRedirect("controlpanel.jsp");
           }
        %>