

<form action="addToRoundup" method="post">
    <div class="leftComicColumn">
<%
    con.openConnection();
    rs = con.getAllComics();
    while(rs.next()) {
%>

<input type="checkbox"  name="comics" value="<%=rs.getString("comicID") %>" id="comics"><%=rs.getString("comicName") %> 
Issue #<%=rs.getString("issueNumber")%><br>
<%}%>
</div>
<div class="rightColumn">
<select name="box">
<option selected="selected">Select a box</option>
<%
    rs = con.getUserBoxes((String) session.getAttribute("username"));
    while(rs.next()) {
%> 

    <option value="<%=rs.getString("boxID")%>">Box: <%=rs.getString("boxTracker")%></option>
    
<% } 
con.closeConnection();
%>
</select>
<input type="submit" value="Add" />
</div>
</form>
