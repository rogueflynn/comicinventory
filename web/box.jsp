

<%
  String name = (String) session.getAttribute("username");
  String boxName = (String) request.getParameter("boxname");
  int thisbox = Integer.parseInt(request.getParameter("boxID"));
  session.setAttribute("boxName", boxName);
  session.setAttribute("boxID", thisbox);
%>

<div class="userboxheader">Box: <%=boxName%><a href="#" class="toggleLink" id="comiclist"><span>Edit</span>
            <span style="display:none;">Close</span></a></div>
<ul class="toggle">
    <li>
        <form action="updateBoxName" id="update_box_form_one" method="post">
            <input type="hidden" name="boxID" value="<%=thisbox%>" />
             <input type="text" name="boxName" value="<%=boxName%>" /> <input type="submit" value="Update" />
        </form>
    </li>
</ul>
<form action="deleteMultipleUserComics" method="post">
<table id="userBox">
         <tr id="searchHeader">
            <th><input type="checkbox" id="selectall" /></th>
            <th>Title/Issue</th>
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
        <a id="usercomiclink" href="comic.jsp?comicID=<%=userComicID.get(i)%>" ><%=usercomics.get(i) %></a>
    </td>
     <td width="180px" id="comicCellpic">
         <a href="delete.jsp?box_id=<%=thisbox%>&comic_id=<%=userComicID.get(i)%>"  ><img id="trashcan" src="assets/trashcan.png"></a>
     </td>
</tr>

<%}%>
</table>

    <input type="submit" value="Delete" />
</form>
