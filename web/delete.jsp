<%
try {
    String boxName = (String) session.getAttribute("boxName");
    int boxID = (Integer) session.getAttribute("boxID");

    int box_id = Integer.parseInt((String) request.getParameter("box_id"));
    int comic_id = Integer.parseInt((String) request.getParameter("comic_id"));
    //out.print("Comic ID=" + comic_id + " Box Id = " + box_id + " " + boxName + " "  + boxID);
    user.deleteComicInBox(box_id, comic_id);
    response.sendRedirect("box.jsp?boxID=" + boxID + "&boxname=" + boxName);
} catch(Throwable t) {}

try {
    int box_ID = Integer.parseInt((String) request.getParameter("boxID"));
    out.print(box_ID);
    box.deleteBox(box_ID);
    response.sendRedirect("controlpanel.jsp");
} catch(Throwable t) {}
%>
