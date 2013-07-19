<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 	<link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/bootstrap-responsive.css" rel="stylesheet">
    <link href="css/footer.css" rel="stylesheet">
    
	<script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/bootstrap-transition.js"></script>
    <script type="text/javascript" src="js/bootstrap-alert.js"></script>
    <script type="text/javascript" src="js/bootstrap-modal.js"></script>
    <script type="text/javascript" src="js/bootstrap-dropdown.js"></script>
    <script type="text/javascript" src="js/bootstrap-scrollspy.js"></script>
    <script type="text/javascript" src="js/bootstrap-tab.js"></script>
    <script type="text/javascript" src="js/bootstrap-tooltip.js"></script>
    <script type="text/javascript" src="js/bootstrap-popover.js"></script>
<title>Sonuç sayfası</title>
</head>
<body background="bg.png">
<div id="wrap">
<div class="navbar">
  <div class="navbar-inner">
    <a class="brand" href="feed.html">Özgeçmiş[beta]</a>
    <ul class="nav">
    <li><a href="index.html">Kişisel:Başlangıç</a></li>
    <li><a href="education.html">Eğitim </a></li>
    <li><a href="experience.html">Deneyimler</a></li>
    <li><a href="personal.html">Kişisel</a></li>
    <li><a href="credential.html">Referanslar</a></li>
    <li><a href="picture.html">Profil Resmi</a></li>
    <li><a href="#"></a></li>
    <li><a href="#"></a></li>
    <li><a href="#"></a></li>
    <li><a href="#"></a></li>
    <li><a href="#"></a></li>
    <li><a href="#"></a></li>
    <li><a href="#"></a></li>
    <li><a href="#"></a></li>
    <li><a href="#"></a></li>
   
    <form class="navbar-search pull-left" action="search.jsp" method="post">
  		<input type="text" class="search-query" name="search" id="search" placeholder="insanları ve özgeçmişlerini ara">
	</form>
    </ul>
  </div>
</div>
<%
main.db.database_open();
%>
<%
main.db.liste.clear();
String similarQuery = request.getParameter("search");
main.db.select(similarQuery);
%>

<% if( main.db.liste.size() == 0 ){ %>
	<center><h3>Aradığınız kriterlere uygun sonuç bulunmamaktadır.</h3></center>
<%} %>

<%
for (int i = 0 ; i< main.db.liste.size(); i++){
%>
<% if ( main.db.liste != null & main.db.liste.get(i).contains("gravatar") ) { %>
	
		<ul class="thumbnails">
  			<li class="span2"> 
    		<a href="#" class="thumbnail">
      		<img data-src="holder.js/160x120" src=<%= main.db.liste.get(i) %> >      
    		</a>	
<%} %>


<% if ( main.db.liste != null & ! main.db.liste.get(i).contains(".") ) {  %>   
	
		<b><e><%= main.db.liste.get(i) %> </e> </b>
	
	
<% } %>

<% if ( main.db.liste != null & main.db.liste.get(i).contains(".pdf") ){ %>

	<a href = <%= main.db.liste.get(i) %> ><br><b>CV Dökümanı </b></a> <br> <hr>
	</li>
	</ul>
<%} %>	
<%	
}
%>
<%
for ( int j=0 ; j < main.db.liste.size(); j++ ){	
	main.db.liste.remove(j);
}
%>
</div>
<div id="footer">
    <div class="container" align="center">
        <p class="muted credit">&copy; 2013 <a href="http://mogutcan.herokuapp.com" target="_blank">Mehmet Öğütcan</a>
        Fikirleriniz önemli. Görüşlerinizi bildirerek <a href="feed.html"> geri dönüş </a> yapın.
        </p>
    </div>  
</div>
</body>
</html>
