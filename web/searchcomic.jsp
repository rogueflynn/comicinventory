

<form action="addToRoundup" method="post">
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
   for (int cID : comicID) {
%>
<tr>
    <td>
    <input type="checkbox" class="checkbox" name="comics" value="<%= cID %>" id="comics">
    </td>
    <td id="comicSearchTitle">
        <h3><%=comicInfo.getComicTitle(cID) %></h3>
        <p>Issue #<%=comicInfo.getComicIssue(cID)%></p>
    </td>
     <td width="180px">
         <img src="comicImages/<%=comicInfo.getComicPhoto(cID) %>" id="searchPic">
     </td>
</tr>

<%}%>
     </table>
</div>
<div class="addToBox">
    <div id="selectBoxHeader"><h3>Add to roundup</h3></div>
<select name="box">
<option selected="selected" id="boxSelect">Select a box</option>
<%
    //Grabs the user boxes
    if(name != null) {
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
<%}%>
</div>
</form>
