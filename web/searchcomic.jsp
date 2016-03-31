

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
    con.openConnection();
    rs = con.getComicsByTitle(search);
    while(rs.next()) {
%>
<tr>
    <td>
    <input type="checkbox" class="checkbox" name="comics" value="<%=rs.getString("comicID") %>" id="comics">
    </td>
    <td id="comicSearchTitle">
        <h3><%=rs.getString("comicName") %></h3>
        Issue #<%=rs.getString("issueNumber")%>
    </td>
     <td width="180px"><img src="comicImages/<%=rs.getString("photo") %>" id="searchPic"></td>
</tr>

<%}%>
     </table>
</div>
<div class="addToBox">
    <div id="selectBoxHeader"><h3>Add to roundup</h3></div>
<select name="box">
<option selected="selected" id="boxSelect">Select a box</option>
<%
    if(name != null) {
    rs = con.getUserBoxes(name);
    while(rs.next()) {
%> 

    <option value="<%=rs.getString("boxID")%>">Box: <%=rs.getString("boxTracker")%></option>
    
<% } 
con.closeConnection();
%>
</select>
<input type="submit" value="Add" />
<%}%>
</div>
</form>
