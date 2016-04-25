<form action="deleteMultipleUserComics" method="post">
<table id="userComicsSearchResult">
         <tr id="searchHeader">
            <th><input type="checkbox" id="selectall" /></th>
            <th>Title/Issue</th>
            <th>View</th>
            <th>Box</th>
            <th>Delete</th>
         </tr>
<%
    List<String> comicname = (List<String>)session.getAttribute("comicName");
    List<String> comicid = (List<String>) session.getAttribute("comicID");
    List<String> issuenumber = (List<String>) session.getAttribute("issueNumber");
    List<String> boxname = (List<String>) session.getAttribute("boxName");
    List<String> boxid = (List<String>) session.getAttribute("boxID");
    for(int i = 0; i < comicname.size(); i++) {
%>
<tr>
    <td id="comicCheckbox">
    <input type="checkbox" class="checkbox" name="usercomics" value="<%=comicid.get(i)%> " id="comics">
    </td>
    <td id="comicSearchTitle">
        <h3><%=comicname.get(i)%> #<%=issuenumber.get(i)%></h3>
    </td>
     <td width="180px" id="comicCellpic">
         <a href="comic.jsp?comicID=<%=comicid.get(i)%>">View</a>
     </td>
      <td width="180px" id="comicCellpic">
         <a href="box.jsp?boxID=<%=boxid.get(i)%>&boxname=<%=boxname.get(i)%> ">Box:<%=boxname.get(i)%></a>
     </td>
     <td width="180px" id="comicCellpic">
         <a href="delete.jsp?box_id=<%=box%>&comic_id=<%=comicid.get(i)%>">Delete</a>
     </td>
</tr>

<% } %>
</table>
</form>