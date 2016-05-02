<%
    List<String> comicname = (List<String>)session.getAttribute("comicName");
    List<String> comicid = (List<String>) session.getAttribute("comicID");
    List<String> issuenumber = (List<String>) session.getAttribute("issueNumber");
    List<String> boxname = (List<String>) session.getAttribute("boxName");
    List<String> boxid = (List<String>) session.getAttribute("boxID");
   
    int index = Integer.parseInt((String) request.getParameter("index"));
    int box_id = Integer.parseInt((String) request.getParameter("box_id"));
    int comic_id = Integer.parseInt((String) request.getParameter("comic_id"));
    
    boxid.remove(index);
    boxname.remove(index);
    issuenumber.remove(index);
    comicid.remove(index);
    comicname.remove(index);
    user.deleteComicInBox(box_id, comic_id);
    response.sendRedirect("usercomic.jsp");


%>
