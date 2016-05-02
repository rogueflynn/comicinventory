<div class="usercomicsheader">My Comics</div>
<form action="deleteMultipleUserComicsFromSearch" method="post">
<table id="userComicsSearchResult">
         <tr id="searchHeader">
            <th><input type="checkbox" id="selectall" /></th>
            <th>Title/Issue</th>
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
    <input type="checkbox" class="checkbox" name="usercomics" value="<%=i%>" id="comics">
    </td>
    <td id="comicSearchTitle">
        <a id="usercomiclink" href="comic.jsp?comicID=<%=comicid.get(i)%>"><%=comicname.get(i)%> #<%=issuenumber.get(i)%></a>
    </td>
      <td width="180px" id="comicCellpic">
         <a id="usercomiclink" href="box.jsp?boxID=<%=boxid.get(i)%>&boxname=<%=boxname.get(i)%> ">Box:<%=boxname.get(i)%></a>
     </td>
     
     <td width="180px" id="comicCellpic">
        <a href="deleteusercomic.jsp?box_id=<%=boxid.get(i)%>&comic_id=<%=comicid.get(i)%>&index=<%=i%>"><img id="trashcan" src="assets/trashcan.png"></a>
     </td>
</tr>

<% } %>
</table>
<input type="submit" value="Delete" />
</form>