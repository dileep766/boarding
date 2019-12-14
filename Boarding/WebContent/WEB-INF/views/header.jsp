	
<!DOCTYPE html>
<html lang="en">
<%@ page import="com.ctl.activiti.login.LoggedInUser" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%><%@taglib
	uri="http://www.springframework.org/tags/form" prefix="form"%><%@taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<head>
 
</head>
<style type="text/css">

#logo{
	max-width:100%;
	height:80px;
	margin-top:0px;
	width:auto\9;
<%if(request.getContextPath().toString().contains("Boarding"))
{
%>
background-image:url("<spring:url value='/images/sample.png'/>");
<%}

	else
		
{
%>

background-image:url("<spring:url value='/images/conference.png'/>");
<%}
%>
}
#menu ul
{
list-style-type:none;
margin:0;
padding:0;
overflow:hidden;
}
#menu ul li
{
float:left;

}
#menu ul li a
{
display:block;
width:40px;
background-color:#dddddd;
}
#menu ul
{
list-style-type:vertical;
margin:0;
padding:0;
}
#menu ul li a:link,#menu ul li a:visited
{
display:block;
font-weight:bold;
color:#FFFFFF;
background-color:#98bf21;
width:auto;
text-align:center;
padding:4px;
text-decoration:none;
text-transform:uppercase;

}
#menu ul li a:hover,#menu ul li a:active
{
background-color:#7A991A;
}
</style>
<%LoggedInUser user = (LoggedInUser) session.getAttribute("user");
String ldapuser=(String)session.getAttribute("ldapuser");
String username=user.getFirstName();%>
<script>
 $!=jQuery;
 </script>
<div id="logo"><p style="color:blue;margin-left:80%;padding-top:25px">Welcome  <%=username%><p></div>
<div id="menu">
	<center>		       
 <ul>          

<%if(ldapuser.equals("true"))
		{
	
	 %> 
	 
<%if(request.getContextPath().toString().contains("Boarding"))
{
%>
 <li style="width:20%"><a class="menu" href="${pageContext.request.contextPath}/procesList">NEW REQUEST</a></li>
	  <li style="width:20%"><a class="menu" href="${pageContext.request.contextPath}/procesStatusList">REQUEST STATUS</a></li>
   <li style="width:20%"><a class="menu" href="${pageContext.request.contextPath}/Report">REPORT</a></li> 
  <li style="width:10%"><a class="menu" href="${pageContext.request.contextPath}/taskList">TASK</a></li> 
<li style="width:19%"><a class="menu" href="${pageContext.request.contextPath}/createUser">Create Vendor</a></li> 
<li style="width:10%"><a class="menu" href="${pageContext.request.contextPath}/logout">Logout</a></li>
<%}

	else
		
{
%>
 <li style="width:20%"><a class="menu" href="${pageContext.request.contextPath}/procesList">NEW REQUEST</a></li>
	  <li style="width:20%"><a class="menu" href="${pageContext.request.contextPath}/procesStatusList">REQUEST STATUS</a></li>
  <li style="width:20%"><a class="menu" href="${pageContext.request.contextPath}/taskList">TASK</a></li> 
<li style="width:39%"><a class="menu" href="${pageContext.request.contextPath}/logout">Logout</a></li>
<%}
		}
else
{%>
 <li style="width:50%"><a class="menu" href="${pageContext.request.contextPath}/taskList">Tasks</a></li> 
<li style="width:49.7%"><a class="menu" href="${pageContext.request.contextPath}/logout">Logout</a></li>    
</ul>   	
<% }
%>
 

 
</center>
</div>
</html>