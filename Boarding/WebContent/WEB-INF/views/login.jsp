<!DOCTYPE html >
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%><%@taglib
	uri="http://www.springframework.org/tags/form" prefix="form"%><%@taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
 <title> Login</title>
 <link href="<spring:url value="css/jquery-ui.css"/>" rel="stylesheet">
<script src="<spring:url value="js/jquery-ui.min.js"/>"></script>
<script src="<spring:url value="js/jquery-ui.js"/>"></script>
<script src="<spring:url value="js/jquery-1.11.1.min.js"/>"></script>

 <meta name="description" content="" />

<link rel="stylesheet" href="css/jquery-ui.css" type="text/css" media="all" /> 
	<script src="js/jquery.validate.js"></script>
 <style type="text/css" media="screen">
 .error
 {
 color:red;
 }
 .container
{
	margin:0px auto;
	background-image:url("<spring:url value='/images/loginimg.jpg'/>");
	  background-repeat: no-repeat;
	text-align:center
}
 </style>
 <script type="text/javascript">

jQuery( document ).ready(function() {
		
		jQuery("#login").validate({	});
		});
		</script>
</head>
<body>
<div class="container" style="width:300px;height:250px;margin-top:15%;display: block;
    margin-left: auto;
    margin-right: auto"  >
    <br/>	
     <br/>
   	 <c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
	
   
	<form:form id="login" action="login" commandName="userForm">

        <div style="padding: 2px;">
            <table style="border-spacing:10px; padding: 5px;" >
            	 
               
                    <tr >
                        <label for="email" style="color:white;font-size: 15px;">CUID</label>
						<FONT color="red"></FONT>
						<br/>
                    </tr>
                    <tr  >
                        <form:input path="email" required="true" id="email"  cssStyle="width: 200px;" />
						<br/>
                    </tr>
                   
               
             
                    <tr >
                        <label for="password" style="color:white;font-size: 15px;">PASSWORD</label>
						
                        <FONT color="red"></FONT>
                        <br/>
                    </tr>
                    <tr  >
                    <form:password required="true" path="password" id="password" cssStyle="width: 200px;" />
					
					</tr>
                    
               

                <tr align="right">
                  
                        <input type="submit" value="Login" style="float: left;margin-left: 100px;margin-top:10px;color: #006600;width: 80px "
                           id="login-form-submit-button">
                      

                    </tr>
                </tr>
               
                
            </table>
        </div>
        
</form:form>


 
        
</body>
</html>
