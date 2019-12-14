<!DOCTYPE html>
<html >
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%><%@taglib
	uri="http://www.springframework.org/tags/form" prefix="form"%><%@taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<head>
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<meta http-equiv="x-ua-compatible" content="IE=9" />

 <link rel="stylesheet" href="css/jquery-ui.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/jquery-ui-timepicker-addon.css" type="text/css" media="all" />
 <link href="css/uploadfile.css" rel="stylesheet">
<script type='text/javascript' src='js/jquery.min.js'></script>
<script src="js/jquery.uploadfile.min.js"></script>
<script src="js/jquery-ui.min.js" type="text/javascript"></script> 
<script src="js/jquery.form.js" type="text/javascript"></script> 
<script type="text/javascript" src="js/jquery.dform-1.1.0.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.validate.js"></script>
<script src="js/jquery-ui-timepicker-addon.js"></script>
<script src="js/jquery.accordion.js" type="text/javascript"></script>
</head>
<body style="margin:0px">


 
        <div  ><tiles:insertAttribute name="header" /></div>
   
       
        <div valign="top" style="margin-top:50px" align="center" >

         <tiles:insertAttribute name="body" />

        </div>
   
  
        <div height="30" >

         <tiles:insertAttribute name="footer" />

        </div>
   
</body>
</html>