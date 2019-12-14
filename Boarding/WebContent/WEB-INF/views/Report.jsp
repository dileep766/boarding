<!DOCTYPE html>
<html lang="en">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%><%@taglib
	uri="http://www.springframework.org/tags/form" prefix="form"%><%@taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java"
	import="org.activiti.engine.repository.ProcessDefinition,java.util.List,java.util.Map,java.util.HashMap,com.ctl.activiti.form.ProcessStatusForm"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<head>

<style type="text/css" media="screen">
.error {
	color: red;
}

<!--
DIV.container {
	margin: auto;
	width: 90%;
	margin-bottom: 10px;
}

h2 {
	margin: 0;
}

.drawers-wrapper {
	position: relative;
	width: 188px;
}

.drawer {
	background: transparent
		url(http://images.apple.com/downloads/images/sideboxlight_bg20070611.gif)
		repeat-y scroll 0pt;
	color: #76797C;
	font-size: 11px;
	line-height: 1.3em;
}

.boxcap {
	height: 5px;
	left: 0pt;
	position: absolute;
	width: 100%;
	z-index: 100;
	background: transparent
		url(http://images.apple.com/downloads/images/sidenav_capbottom.png)
		no-repeat scroll 0%;
	margin-top: -5px;
}

.captop {
	background-image:
		url(http://images.apple.com/downloads/images/box_188captop.png);
	bottom: auto;
	top: 0pt;
	margin-top: 0;
}

.drawers {
	margin-bottom: 15px;
	color: #76797C;
	font-size: 11px;
	line-height: 18px;
}

.drawers A {
	color: #666666;
	text-decoration: none;
	font-family: "Lucida Grande", Geneva, Arial, Verdana, sans-serif;
	font-size-adjust: none;
	font-style: normal;
	font-variant: normal;
	font-weight: normal;
}

.drawer li {
	border-bottom: 1px solid #E5E5E5;
	line-height: 16px;
	padding: 6px 0pt;
}

UL {
	list-style: none;
	padding: 0;
}

UL.drawers {
	margin: 0;
}

.drawer-handle {
	background: #A4CD39;
	color: white;
	cursor: default;
	font-size: 12px;
	font-weight: normal;
	height: 25px;
	line-height: 25px;
	margin-bottom: 0pt;
	text-indent: 15px;
	width: 100%;
}

.drawer-handle.open {
	background-color: #72839D;
	background-position: -188px 0pt;
	color: #FFFFFF;
}

.drawer UL {
	padding: 0 12px;
	padding-bottom: 0pt;
}

.drawer-content UL {
	padding-top: 7px;
}

.drawer-content LI A {
	display: block;
	overflow: hidden;
}

.alldownloads li {
	border: 0pt none;
	line-height: 18px;
	padding: 0pt;
}

-->
fieldset.scheduler-border {
	border: 1px groove #ddd !important;
	padding: 0 1.4em 1.4em 1.4em !important;
	margin: 0 0 1.5em 0 !important;
	-webkit-box-shadow: 0px 0px 0px 0px #000;
	box-shadow: 0px 0px 0px 0px #000;
}

legend.scheduler-border {
	font-size: 1.2em !important;
	font-weight: bold !important;
	text-align: left !important;
	width: auto;
	padding: 0 10px;
	border-bottom: none;
}

.container {
	margin: 0px auto;
	background-color: #eee;
	padding: 20px;
	border-radius: 5px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	-webkit-box-shadow: 0 1px 4px rgba(0, 0, 0, 0.3), 0 0 40px
		rgba(0, 0, 0, 0.1) inset;
	-moz-box-shadow: 0 1px 4px rgba(0, 0, 0, 0.3), 0 0 40px
		rgba(0, 0, 0, 0.1) inset;
	box-shadow: 0 1px 4px rgba(0, 0, 0, 0.3), 0 0 40px rgba(0, 0, 0, 0.1)
		inset;
}

.CSSTableGenerator {
	margin: 0px;
	padding: 0px;
	width: 100%;
	box-shadow: 10px 10px 5px #888888;
	border: 1px solid #3f7f00;
	-moz-border-radius-bottomleft: 0px;
	-webkit-border-bottom-left-radius: 0px;
	border-bottom-left-radius: 0px;
	-moz-border-radius-bottomright: 0px;
	-webkit-border-bottom-right-radius: 0px;
	border-bottom-right-radius: 0px;
	-moz-border-radius-topright: 0px;
	-webkit-border-top-right-radius: 0px;
	border-top-right-radius: 0px;
	-moz-border-radius-topleft: 0px;
	-webkit-border-top-left-radius: 0px;
	border-top-left-radius: 0px;
}

.CSSTableGenerator table {
	border-collapse: collapse;
	border-spacing: 0;
	width: 100%;
	margin: 0px;
	padding: 0px;
}

.CSSTableGenerator tr:last-child td:last-child {
	-moz-border-radius-bottomright: 0px;
	-webkit-border-bottom-right-radius: 0px;
	border-bottom-right-radius: 0px;
}

.CSSTableGenerator table tr:first-child td:first-child {
	-moz-border-radius-topleft: 0px;
	-webkit-border-top-left-radius: 0px;
	border-top-left-radius: 0px;
}

.CSSTableGenerator table tr:first-child td:last-child {
	-moz-border-radius-topright: 0px;
	-webkit-border-top-right-radius: 0px;
	border-top-right-radius: 0px;
}

.CSSTableGenerator tr:last-child td:first-child {
	-moz-border-radius-bottomleft: 0px;
	-webkit-border-bottom-left-radius: 0px;
	border-bottom-left-radius: 0px;
}

.CSSTableGenerator tr:hover td {
	
}

.CSSTableGenerator tr:nth-child(odd) {
	background-color: #d4ffaa;
}

.CSSTableGenerator tr:nth-child(even) {
	background-color: #ffffff;
}

.CSSTableGenerator td {
	vertical-align: middle;
	border: 1px solid #3f7f00;
	border-width: 0px 1px 1px 0px;
	text-align: left;
	padding: 7px;
	font-size: 10px;
	font-family: Arial;
	font-weight: normal;
	color: #000000;
}

.CSSTableGenerator tr:last-child td {
	border-width: 0px 1px 0px 0px;
}

.CSSTableGenerator tr td:last-child {
	border-width: 0px 0px 1px 0px;
}

.CSSTableGenerator tr:last-child td:last-child {
	border-width: 0px 0px 0px 0px;
}

.CSSTableGenerator tr:first-child td {
	background: -o-linear-gradient(bottom, #5fbf00 5%, #3f7f00 100%);
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #5fbf00
		), color-stop(1, #3f7f00));
	background: -moz-linear-gradient(center top, #5fbf00 5%, #3f7f00 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr="#5fbf00",
		endColorstr="#3f7f00");
	background: -o-linear-gradient(top, #5fbf00, 3f7f00);
	background-color: #5fbf00;
	border: 0px solid #3f7f00;
	text-align: center;
	border-width: 0px 0px 1px 1px;
	font-size: 14px;
	font-family: Arial;
	font-weight: bold;
	color: #ffffff;
}

.CSSTableGenerator tr:first-child:hover td {
	background: -o-linear-gradient(bottom, #5fbf00 5%, #3f7f00 100%);
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #5fbf00
		), color-stop(1, #3f7f00));
	background: -moz-linear-gradient(center top, #5fbf00 5%, #3f7f00 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr="#5fbf00",
		endColorstr="#3f7f00");
	background: -o-linear-gradient(top, #5fbf00, 3f7f00);
	background-color: #5fbf00;
}

.CSSTableGenerator tr:first-child td:first-child {
	border-width: 0px 0px 1px 0px;
}

.CSSTableGenerator tr:first-child td:last-child {
	border-width: 0px 0px 1px 1px;
}

.CSSTableGenerator tr {
	height: 30px;
}
</style>
<script>
$!=jQuery;
</script>
<title>Welcome</title>
</head>
<body>
	<div style="width: 100%">

		<div style="margin-left: 20px">
		<br/><br/>
			<center>To download report please <a href="${pageContext.request.contextPath}/finalReport">click</a> here.</center>
			<br/>
			<br/>
			<div class="CSSTableGenerator" id="tablediv" style="width: 90%">

			</div>

		</div>
	</div>
	<script type="text/javascript" class="demo" id="demo-1">

 var allVendors=[];
 
jQuery( document ).ready(function() {
	
			getReportData();
			
	
		});


function getReportData(id)
{
	jQuery("#reportgenearation").val(id);	
var gridData=[];
jQuery.ajax({
    url: '${pageContext.request.contextPath}/getTotalReport',
    dataType: 'json',
	
    success: function(response) {
    	//alert(JSON.stringify(response))
    	//jQuery("#tablediv").html('')
				var tbodydata=[];
		
				
		tbodydata.push({"type":"tr","html":[{"type":"td","html":"PROCESS ID"},{"type":"td","html":"STATUS"},{"type":"td","html":"CTLI MANAGER"},{"type":"td","html":"CTLI DIVISION"},{"type":"td","html":"CTLI DIRECTOR"},{"type":"td","html":"VENDOR"},{"type":"td","html":"VENDOR MAIL"},{"type":"td","html":"DATE OF JOINING"},{"type":"td","html":"CONTRACT ENDDATE"},{"type":"td","html":"NAME"},{"type":"td","html":"FIRST NAME"},{"type":"td","html":"LAST NAME"},{"type":"td","html":"MIDDLE NAME"},{"type":"td","html":"DATE OF BIRTH"},{"type":"td","html":"Gender"},{"type":"td","html":"Mobile Number"},{"type":"td","html":"Previous Organization"},{"type":"td","html":"Total Years Of Experience"},{"type":"td","html":"Degree"},{"type":"td","html":"Specializtion"},{"type":"td","html":"Institute"},{"type":"td","html":"Year Of Passing"},{"type":"td","html":"SKILLS"},{"type":"td","html":"Designation"},{"type":"td","html":"COMPANY"}]});
	
		jQuery.each(response, function(key) {
		
			tbodydata.push({"type":"tr","html":[{"type":"td","html":this.processid},{"type":"td","html":this.statusofprocess},{"type":"td","html":this.ctlimanager},{"type":"td","html":this.ctlidivision },{"type":"td","html":this.ctlidirector },{"type":"td","html":this.vendor },{"type":"td","html":this.vendormail },{"type":"td","html":this.startDate},{"type":"td","html":this.enddate},{"type":"td","html":this.resourceName},{"type":"td","html":this.firstname },{"type":"td","html":this.lastname},{"type":"td","html":this.middlename},{"type":"td","html":this.dateofbirth},{"type":"td","html":this.gender},{"type":"td","html":this.mobilenumber},{"type":"td","html":this.previousorganization},{"type":"td","html":this.totalyearsofexperience},{"type":"td","html":this.degree},{"type":"td","html":this.specialization},{"type":"td","html":this.institute},{"type":"td","html":this.yearofpassing},{"type":"td","html":this.skills},{"type":"td","html":this.designation},{"type":"td","html":this.company}]})
		})
	
		
		jQuery('#tablediv').dform({
		   
		    "method":"post",
		    "html":[
		        {
		            "type":"table",
					
		            "html":tbodydata
					}]
					});
    }
});
}
 
</script>
</body>
</html>