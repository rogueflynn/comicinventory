<%
    int comicid = Integer.parseInt(request.getParameter("comicID"));
%>

<img src="comicImages/<%=comicInfo.getComicPhoto(comicid) %>" id="comicimage">

<div id="comicinforight">
    <div id="comicinfo">Comic: <%=comicInfo.getComicTitle(comicid) %>  <br/></div>
    <div id="comicinfo">Issue #: <%=comicInfo.getComicIssue(comicid) %> <br/></div>
    <div id="comicinfo">Publisher: <%=comicInfo.getComicPublisher(comicid) %> <br/></div>
    <div id="comicinfo">Print #: <%=comicInfo.getComicPrint(comicid) %> <br/></div>
    <div id="comicinfo">Year Released: <%=comicInfo.getComicYear(comicid) %> <br/></div>
</div>