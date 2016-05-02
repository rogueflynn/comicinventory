

<form action="addToRoundup" method="post" id="add_to_box_form">
 <div class="leftComicColumn">
     <table id="searchResult">
         <tr id="searchHeader">
            <th><input type="checkbox" id="selectall" /></th>
            <th>Title/Issue</th>
            <th>Image</th>
         </tr>
<%
    String name = (String) session.getAttribute("username");
    String search = request.getParameter("search");
    comicID = comicInfo.getComicsID(search);
    if(!(search.equals(""))) {
   for (int cID : comicID) {
%>
<tr>
    <td id="comicCheckbox">
    <input type="checkbox" class="checkbox" name="comics" value="<%= cID %>" id="comics">
    </td>
    <td id="comicSearchTitle">
        <a id="usercomiclink" href="comic.jsp?comicID=<%=cID%>"><p><%=comicInfo.getComicTitle(cID) %></p>
             <p>Issue #<%=comicInfo.getComicIssue(cID)%></p></a>
    </td>
     <td width="180px" id="comicCellpic">
         <img src="comicImages/<%=comicInfo.getComicPhoto(cID) %>" id="searchPic">
     </td>
</tr>

<%}%>
     </table>
</div>
 <%
   if(name != null) {
   
 %>
<div class="addToBox">
    <div id="selectBoxHeader"><h3>Add to box</h3></div>
<select id="putInBox" name="box">
<option selected="selected" id="boxSelect" value="default">Select a box</option>
<%
    //Grabs the user boxes
    
        hmap = user.getUserBoxes(name);
        set = hmap.entrySet();
        iterator = set.iterator();
        while(iterator.hasNext()) {
             Map.Entry mentry = (Map.Entry)iterator.next();
%> 

    <option value="<%=(int) mentry.getKey()%>">Box: <%=(String) mentry.getValue()%></option>
    
<% } %>
</select>
<input type="submit" value="Add" />
<%
   }
 }
%>
</div>
</form>
