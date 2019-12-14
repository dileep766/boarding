
<!DOCTYPE html>
<html lang="en">
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


</style>
<script>
 $!=jQuery;
 </script>
<div id="logo"></div>

</html>