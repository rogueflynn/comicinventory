 
<!-- Outer box -->
<div class="homecontainer"> 
 
<!-- About us container box-->
<div class="about_us_containerbox">
    <div id="aboutusBoxHeader"><h3>About us</h3></div>
    <br>
    <p>
        Comic Roundup is a Website that allows people to inventory their comic books,
        store them in an online database, search their inventory and much more.  
        We are currently working on entering comics in our database so any help
        is welcomed!
    </p>
</div>


<!-- News feed container box-->
<div class="news_feed_containerbox">
    <div id="newsfeedBoxHeader"><h3>News Feed</h3></div>
    <a class="twitter-timeline" data-height="300" data-theme="dark" data-link-color="#FAB81E" href="https://twitter.com/ComicBook">
        Tweets by ComicBook
    </a> 
    <script async src="//platform.twitter.com/widgets.js" charset="utf-8"></script>
</div>

<!-- Recently added container box-->
<div class="recentlyadded_containerbox">
    <div id="recentlyaddedBoxHeader"><h3>Recently Added Comics</h3></div>
    <%
        siteComics = comicInfo.getLastComicsEntered();
        for(String lastEntered : siteComics) {
    %>
    <p><%=lastEntered%></p><br>
    <%
        }
    %>
</div>
</div>
      