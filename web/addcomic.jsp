<%-- 
    Document   : addcomic
    Created on : Mar 27, 2016, 12:22:02 PM
    Author     : Victor
--%>
   <fieldset>
      <!--PHOTO UPLOAD FORM STARTS HERE -->
      <div class="imageContainer">
        <% 
          String url = (String) session.getAttribute("url"); 

	    //This piece of code will test whether there is an active session in the browser.
	   if(url==null) {
	%>
        <form action ="UploadFile" method="post" enctype="multipart/form-data">
            <p><b>Upload Photo:</b></p> 
            <input type="submit" value="Upload" />
            <input type="file" name="filetoupload" />
            
        </form>
        <% 
           } else if(url != null) {
                Thread.sleep(2000); //sleep 2 seconds so the picture loads
        %>
       
         <img src="comicImages/<%=url%>" id="uploadImg">
         <form action ="deletePreviewImage" method="post" enctype="multipart/form-data" id="deletePreview">
            <input type="submit" value="Delete" />
        </form>
        <%
           }
        %>
      </div>
     <!--PHOTO UPLOAD FORM ENDS HERE -->
<div class="addComicForm">
    <form action="addComic" id="createForm" method="post">
            <p><label for="comicname"><div id="rLabel">Comic Name:</div></label> <input type="text" name="comicname" id="comicname"  /></p>
            <p><label for="issuenumber"><div id="rLabel">Issue #:</div></label>   <input type="text" name="issuenumber" id="pass" /> </p>
            <p><label for="publisher"><div id="rLabel">Publisher:</div></label>   <input type="text" name="publisher" id="publisher" /> </p>
            <p><label for="printing"><div id="rLabel">Print:</div></label>   <input type="text" name="printing" id="printing" /> </p>
            <p><label for="year"><div id="rLabel">Year Released:</div> </label> <input type="text" name="year" id="year" /></p><br/><br/>
            
        <p><input type="submit" value="Submit" id="rSubmit"/> <input type="submit" value="Clear" id="rSubmit"/></p>
       
    </form>
</div>
 </fieldset>