<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>Tavant Engage</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<head>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/ug-theme-default.css"> 
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/unite-gallery.css"> 
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/style.css">
    <link href="http://fonts.googleapis.com/css?family=Raleway" rel="stylesheet" type="text/css">
    <script src="<%=request.getContextPath()%>/resources/Libraries/jquery.min.v3.1.1.js"></script>
    <script src="<%=request.getContextPath()%>/resources/Js/custom.js"></script>
    
 </head>

<body class="body">
<header class="header overhid">
    <span class="fl pad10"><img src="<%=request.getContextPath()%>/resources/images/tavant_logo.png" height="30px"/></span>
    <span class="fr pad10"><img src="<%=request.getContextPath()%>/resources/images/tech_connect_logo.png" height="35px" /></span>
</header>
<div class="tealive"><div class="fr">
<img class="pad10 fl" src="<%=request.getContextPath()%>/resources/images/engage_logo.png"/>
 <span class="pad10 fl livetext"><span class="redDot"></span>Live</span></div></div>    
<div >
 Please Select advertisment		
			<select id="tagvideo" name="tagvideo">
    <c:forEach var="line" items="${metaData_media}">
        <option value="${line.value}"><c:out value="${line.key}"/></option>
    </c:forEach>
 </select>
</div>
<div>
 <button  name="submit" id="submit" type="button">Submit</button>	
 
 </div>
 
 
 
 		<script src="<%=request.getContextPath()%>/resources/Js/tavantadd.js"></script>
</body>
</html>
