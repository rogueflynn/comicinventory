

<%
  String name = (String) session.getAttribute("username");
  String boxName = (String) request.getParameter("boxname");
  int thisbox = Integer.parseInt(request.getParameter("boxID"));
  session.setAttribute("boxName", boxName);
  session.setAttribute("boxID", thisbox);
%>

<p>Box: <%=boxName%> <a href="#" class="toggleLink" id="comiclist"><span>Edit</span>
        <span style="display:none;">Close</span></a></p>
<ul class="toggle">
    <li>
        <form action="updateBoxName" method="post">
            <input type="hidden" name="boxID" value="<%=thisbox%>" />
            <input type="text" name="boxName" value="<%=boxName%>" /> <input type="submit" value="update" />
        </form>
    </li>
</ul>
<form action="deleteMultipleUserComics" method="post">
<table id="searchResult">
         <tr id="searchHeader">
            <th><input type="checkbox" id="selectall" /></th>
            <th>Title/Issue</th>
            <th>View</th>
            <th>Delete</th>
         </tr>
<%
    usercomics = user.getUserComicsinBox(thisbox);
    userComicID = user.getUserComicID(thisbox);
    int comic_id;
   for (int i = 0; i < usercomics.size(); i++){
       comic_id = (Integer) userComicID.get(i);
%>
<tr>
    <td id="comicCheckbox">
    <input type="checkbox" class="checkbox" name="usercomics" value="<%= comic_id %>" id="comics">
    </td>
    <td id="comicSearchTitle">
        <h3><%=usercomics.get(i) %></h3>
    </td>
     <td width="180px" id="comicCellpic">
         <a href="comic.jsp?comicID=<%=userComicID.get(i)%>" >View</a>
     </td>
     <td width="180px" id="comicCellpic">
     
                   <a href="delete.jsp?box_id=<%=box%>&comic_id=<%=userComicID.get(i)%>"  >Delete</a>
     </td>
</tr>

<%}%>
</table>

    <input type="submit" value="Delete" />
</form>
