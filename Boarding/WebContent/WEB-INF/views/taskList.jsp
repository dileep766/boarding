<!DOCTYPE html>
<html lang="en">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%><%@taglib
	uri="http://www.springframework.org/tags/form" prefix="form"%><%@taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" import="org.activiti.engine.task.Task,java.util.List" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<head>
  <style type="text/css">
.form-style-2{
background-image:url("<spring:url value='/images/paper.gif'/>");	border-radius:10px;
    max-width: 700px;
    padding: 20px 12px 10px 20px;
    font: 13px Arial, Helvetica, sans-serif;
}
.form-style-2-heading{
    font-weight: bold;
    font-style: italic;
    border-bottom: 2px solid #ddd;
    margin-bottom: 20px;
    font-size: 15px;
    padding-bottom: 3px;
}
.form-style-2 label{
    display: block;
    margin: 0px 0px 15px 0px;
}
.form-style-2 label > span{
    width: 200px;
    font-weight: bold;
    float: left;
    padding-top: 8px;
    padding-right: 5px;
    text-align:right;
}
.form-style-2 span.required{
    color:red;
}
.form-style-2 .tel-number-field{
    width: 40px;
    text-align: center;
}
.form-style-2 input.input-field{
    width: 48%;
    
}

.form-style-2 input.input-field, 
.form-style-2 .tel-number-field, 
.form-style-2 .textarea-field, 
 .form-style-2 .select-field{
    box-sizing: border-box;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    border: 1px solid #C2C2C2;
    box-shadow: 1px 1px 4px #EBEBEB;
    -moz-box-shadow: 1px 1px 4px #EBEBEB;
    -webkit-box-shadow: 1px 1px 4px #EBEBEB;
    border-radius: 3px;
    -webkit-border-radius: 3px;
    -moz-border-radius: 3px;
    padding: 7px;
    outline: none;
}
.form-style-2 .input-field:focus, 
.form-style-2 .tel-number-field:focus, 
.form-style-2 .textarea-field:focus,  
.form-style-2 .select-field:focus{
    border: 1px solid #0C0;
}
.form-style-2 .textarea-field{
    height:100px;
    width: 55%;
}
.form-style-2 input[type=submit],
.form-style-2 input[type=button]{
    border: none;
    padding: 8px 15px 8px 15px;
    background: #FF8500;
    color: #fff;
    box-shadow: 1px 1px 4px #DADADA;
    -moz-box-shadow: 1px 1px 4px #DADADA;
    -webkit-box-shadow: 1px 1px 4px #DADADA;
    border-radius: 3px;
    -webkit-border-radius: 3px;
    -moz-border-radius: 3px;
}
.form-style-2 input[type=submit]:hover,
.form-style-2 input[type=button]:hover{
    background: #EA7B00;
    color: #fff;
}
</style>
<style>
 .error
 {
 color:red;
 }
.container
{
	
	
	
	
}
</style>
 <style type="text/css" media="screen">
 
        <!--
    
        DIV.container { margin: auto; width: 90%; margin-bottom: 10px;}
       
        h2 {
            margin: 0;
        }

        .drawers-wrapper {
            position: relative;
            width: 288px;
            
        }

        .drawer {
            background:transparent url(http://images.apple.com/downloads/images/sideboxlight_bg20070611.gif) repeat-y scroll 0pt;
            color:#76797C;
            font-size:11px;
            line-height:1.3em;
        }

        .boxcap {
            height:5px;
            left:0pt;
            position:absolute;
            width:100%;
            z-index:100;
            background:transparent url(http://images.apple.com/downloads/images/sidenav_capbottom.png) no-repeat scroll 0%;
            margin-top:-5px;
        }

        .captop {
            background-image:url(http://images.apple.com/downloads/images/box_188captop.png);
            bottom:auto;
            top:0pt;
            margin-top:0;
        }

        .drawers {
            margin-bottom:15px;
            color:#76797C;
            font-size:11px;
            line-height: 18px;
        }

        .drawers A {
            color:#666666;
            text-decoration:none;
            font-family:"Lucida Grande",Geneva,Arial,Verdana,sans-serif;
            font-size-adjust:none;
            font-style:normal;
            font-variant:normal;
            font-weight:normal;
        }

        .drawer li {
            border-bottom:1px solid #E5E5E5;
            line-height:16px;
            padding:6px 0pt;
        }

        UL {
            list-style: none;
            padding: 0;
        }

        UL.drawers {
            margin: 0;
        }

         .drawer-handle {
            background:#A4CD39;
            color:white;
            cursor:default;
            font-size:12px;
            font-weight:normal;
            height:25px;
            line-height:25px;
            margin-bottom:0pt;
            text-indent:15px;
            width:100%;
        }

        .drawer-handle.open {
            background-color:#72839D;
            background-position:-188px 0pt;
            color:#FFFFFF;
        }

        .drawer UL {
            padding: 0 12px;
            padding-bottom:0pt;
        }

        .drawer-content UL {
            padding-top: 7px;
        }

        .drawer-content LI A {
            display:block;
            overflow:hidden;
        }

        .alldownloads li {
            border:0pt none;
            line-height:18px;
            padding:0pt;
        }
        -->
     /*    .scheduler-border
        {
        width:80%;
        } */
		fieldset.scheduler-border {
	  
    
    padding: 0 1.4em 1.4em 1.4em !important;
    margin: 0 0 1.5em 0 !important;
    -webkit-box-shadow:  0px 0px 0px 0px #000;
            box-shadow:  0px 0px 0px 0px #000;
}
fieldset
{
border:2px solid black;
border-radius:10px;
}

    legend.scheduler-border {
	
        font-size: 1.2em !important;
        font-weight: bold !important;
        text-align: left !important;
        width:auto;
        padding:0 10px;
        border-bottom:none;
    }
	.col-sm-10
	{
	width:65%;
	}
	.col-sm-2
	{
	width:35%;
	}
        </style>
	<script>
$!=jQuery;
</script>
<title>Welcome</title>
</head>
<body>
<div style="width:100%">
<div style="float:left;width:300px">
	 <div class="drawers-wrapper">
	<div class="boxcap captop"></div>
    <ul class="drawers">
        <li class="drawer">
		 <h2 class="drawer-handle">INBOX(${tasklist.size()})</h2>
    <ul class="alldownloads">
	<c:forEach items="${tasklist}" var="item">
  <li style="cursor:pointer" id="${item.getId()}"><a  onclick="getTaskData('${item.getId()}','${item.getName()}')" />${item.getName()}(CRR ${item.getProcessInstanceId()})</a></li>
   
</c:forEach>
        </ul>
        </li>
        <li class="drawer">
            <h2 class="drawer-handle"></h2>
            <ul> 
</ul>
      </li>
</ul>	
</div>	
</div>
<div style="margin-left:310px">
<div  >

<div  class="container">

<form id="filleddata" class="form-style-2"  ></form>
</div>
<div id="uploaddata" >

</div>

<div id="formdata" class="container" >

</div>
</div>
</div>
</div>
<script type="text/javascript" class="demo"  id="demo-1">
$.noConflict();

jQuery( document ).ready(function() {
	
	 jQuery("#filleddata").click(function()
  {
    jQuery("#filleddata div").slideToggle(500);
  });
 <c:if test="${not empty tasklist}">
 jQuery("#formdata").html('<form id="processform" class="form-style-2" role="form" ></form>')
		getTaskData(${tasklist.get(0).getId()},'${tasklist.get(0).getName()}')
		</c:if>
		<c:if test="${empty tasklist}">
		
		jQuery("#formdata").html("<center><b>No task found in your INBOX</b></center>")
		</c:if>
		jQuery("#processform").validate({
    
	});
		});
		
		



	function getTaskData(id,name)
	{
	jQuery("#processform").empty()
	jQuery("#filleddata").empty()
	jQuery('#uploaddata').empty();
taskid=id;
jQuery.ajax({
        url: '${pageContext.request.contextPath}/taskFormData/'+id,
        dataType: 'json',
		
        success: function(response) {
	//	alert(JSON.stringify(response.properties))
	//alert(response.description)
	
		var descriptiondata=eval("("+response.description+")")
	//alert(JSON.stringify(descriptiondata))
var beforetaskid=descriptiondata.id;
	var attachmentids=[];
		if(name=="Resource Information to PMO" && descriptiondata.FormData.Attachmentids!="" )
		{
			//attachmentids=descriptiondata.Attachment ids;
attachmentids=descriptiondata.FormData.Attachmentids.split('##');
			
		}
		var filleddata=[];
		filleddata.push({"type" : "legend","html" :"<b style='color:green'>Info for "+name+"</b>"})
		jQuery.each(descriptiondata.FormData, function(key, value) {
        	
		if(key!="id" && key!="Attachmentids")
		{
		 if(key=="Additional Details" || key=="Feed back from Vendor for Closing Contract" || key=="Previous PMO comments")
		 {
		
		 filleddata.push({"type": "label","html":[{"type":"span","html":key+":"},{"name":key,"class":"textarea-field","id":key,"value":value,"type":"TextArea","rows":"10","readonly":"readonly"}]});
		 }
		 else
		 {
		 filleddata.push({"type": "label","html":[{"type":"span","html":key+":"},{"name":key,"class":"input-field","id":key,"type":"text","value":value,"readonly":"readonly"}]});
		 
		 }
		  }
		
            
        });
		
		
		var data=[];
		var uploaddata=[];
	//	jQuery("#Description").html('<fieldset class="scheduler-border"><legend><b>Info for '+name+'</legend>'+response.description+'</b></fieldset><br/><br/>');
		data.push({"type" : "legend","html" :"<b style='color:green'>"+name+"</b>"})
		uploaddata.push({"type" : "legend","html" : "<b style='color:green'>Upload Files</b>"})
		// data.push({"type" : "div","style":"width:100%","html" : response.description+"<br/>"})
//	alert(JSON.stringify(response.attachments))
		jQuery.each(response.attachments, function(key) {
		var flag=false;
		
		for(var i=0;i<attachmentids.length;i++)
		{
		if(this.id==attachmentids[i])
	{
		flag=true;
		break;
	}
		
		 }
		if(!flag)
		 filleddata.push({"type": "label","html":[{"type":"span","html":"Attachment:"},{"type" : "a","href":"${pageContext.request.contextPath}/attachmentData/"+this.id,"html" :this.name}]}); 
		 else
		  filleddata.push({"type": "label","html":[{"type":"span","html":"Attachment:","css":{"color":"red"}},{"type" : "a","href":"${pageContext.request.contextPath}/attachmentData/"+this.id,"html" :this.name,"css":{"color":"red"}}]}); 
		 
		 
		 });

		 if(response.attachments.length>0)
			 {
			 
			 filleddata.push({"type": "label","html":[{"type":"span","html":"Attachments as ZIP:"},{"type" : "a","href":"${pageContext.request.contextPath}/dataByTaskId/"+response.attachments[0].id,"html" :"Click Here"}]}); 
			 }
			if(name=="Resource Information to PMO" || name=="Resource Information" || name=="Resource Information On Hold" )
				{
			 filleddata.push({"type": "label","html":[{"type":"span","html":"Contractor Joining Info Document:"},{"type" : "a","href":"${pageContext.request.contextPath}/reportsDownload/"+taskid,"html" :"Click Here"}]}); 	
				}
			if(name=="Resource Status Approved" || name=="Refilling Req From PMO" )
			{
			filleddata.push({"type": "label","html":[{"type":"span","html":"On Boarding Documents:"},{ "type" : "a","href":"${pageContext.request.contextPath}/onBoardingAttachments","html" :"Click Here"}]});
						}
		 jQuery.each(response.properties, function(key) {
		 if(this.property.type.name=="string")
		 {
		if(this.property.id!="id")
		{
		 if(this.property.id=="additionalDetails")
		 {
		data.push({"type": "label","html":[{"type":"span","html":this.property.name+":"},{"name":this.property.id,"class":"textarea-field","id":this.property.id,"type":"TextArea","rows":"10","required":this.property.required}]});
		
		 }
		 else
		 {
		  data.push({"type": "label","html":[{"type":"span","html":this.property.name+":"},{"name":this.property.id,"class":"input-field","value":this.property.value,"id":this.property.id,"type":"text","rows":"10","required":this.property.required}]});
		 }
		 }
		 }
		  else if(this.property.type.name=="date")
		 {
		// alert("i am in date")
		 data.push({"type": "label","html":[{"type":"span","html":this.property.name+":"},{"name":this.property.id,"class":"input-field","id":this.property.id,"value":this.property.value,"type":"text","rows":"10","required":this.property.required}]});
		 }
		  else if(this.property.type.name=="long")
		 {
		  data.push({"type": "label","html":[{"type":"span","html":this.property.name+":"},{"name":this.property.id,"id":this.property.id,"value":this.property.value,"type":"number","required":this.property.required,"class":"input-field"}]});
		 }
		else if( this.property.type.name=="enum")
		{
		 data.push({"type": "label","html":[{"type":"span","html":this.property.name+":"},{"name":this.property.id,"class":"select-field","id":this.property.id,"type":"select","options":this.propertyvalues,"required":this.property.required}]});
		}
		
		 });
		if(name=="Resource Information to PMO" || name=="Resource Information" ||  name=="Status Change" || name=="Resource Information On Hold")
			{
			
			data.push({"name":"currentid","id":"currentid","type":"hidden","value":taskid})
			 data.push({"name":"id","id":"id","type":"hidden","value":beforetaskid})
			}
		else
			{
			 data.push({"name":"currentid","id":"currentid","type":"hidden","value":taskid})
			 data.push({"name":"id","id":"id","type":"hidden","value":taskid})
			}
	if(name!="Contract Closing Notification" && name!="Contract Extending Information" && name!="Contract Closing Information" && name!="Resource Information to HR Operations" && name!="Conformation About DOJ" && name!="Resource Information to PMO" && name!="Filled Exit Clearence Form" && name!="LWD for HR Ops Team"  && name!="Resource Status Rejected" && name!="Resource Information" && name!="Manager Approval" && name!="Manager Approved" && name!="Manager Rejected" && name!="Director Approval" && name!="Director Approved" && name!="Director Rejected")
{
data.push({"type":"submit","value":"Submit"});
uploaddata.push({"type" : "div", "id":"mulitplefileuploader", "name":"mulitplefileuploader","html" :"Upload"});
uploaddata.push({"type" : "div", "id":"status", "name":"status"});
jQuery('#uploaddata').html("");

jQuery('#uploaddata').dform({
    "action":"${pageContext.request.contextPath}/finishTask",
    "method":"post",
    "html":[
        {
            "type":"div",
			
            "html":uploaddata
			}]
			});
jQuery("#mulitplefileuploader").uploadFile({
	url: "${pageContext.request.contextPath}/upload",
	
	allowedTypes:"jpg,png,gif,doc,docx,xml,pdf,zip,xls",
	fileName: "file",
	multiple: true,
	formData:{"id":taskid},
	onSuccess:function(files,data,xhr)
	{
		jQuery("#status").html("<font color='green'>Upload is success</font>");
		
	},
    afterUploadAll:function()
    {
       // alert("all files uploaded!!");
    },
	onError: function(files,status,errMsg)
	{		
		jQuery("#status").html("<font color='red'>Upload is Failed</font>");
	}
});

}
else if(name=="Contract Extending Information" || name=="Contract Closing Information" || name=="Conformation About DOJ" || name=="Filled Exit Clearence Form" || name=="LWD for HR Ops Team"  || name=="Resource Status Rejected"  ||  name=="Resource Status Rejected" || name=="Manager Approved" || name=="Manager Rejected"  || name=="Director Approved" || name=="Director Rejected" || name=="Resource Status Rejected")
{
data.push( {"type":"submit","value":"Close Message"})

}
else if(name=="Resource Information to HR Operations")
{

}		
else
{
data.push( {"type":"submit","value":"Submit"})

}
jQuery('#processform').dform({
    "action":"${pageContext.request.contextPath}/finishTask",
    "method":"post",
    "html":[
        {
            "type":"fieldset",
			"class":"scheduler-border",
            "html":data
			}]
			});
			jQuery('#filleddata').dform({
    "action":"${pageContext.request.contextPath}/finishTask",
    "method":"POST",
    "html":[
        {
            "type":"fieldset",
			"class":"scheduler-border",
            "html":filleddata
			}]
			});
			
			 jQuery.each(response.properties, function(key) {
		
		 if(this.property.type.name=="string")
		 {
		 
		 }
		  else if(this.property.type.name=="date")
		 {
		// alert("i am in date 2")
		if(this.property.id=="dateofbirth")
		 jQuery( "#"+this.property.id ).datepicker({changeYear: true,yearRange: '1910:2015',dateFormat: 'dd/mm/yy'});
			else
			 jQuery( "#"+this.property.id ).datepicker({ minDate: 0,dateFormat: 'dd/mm/yy'});
		 }
		  else if(this.property.type.name=="long")
		 {
		 jQuery( "#"+this.property.id ).spinner();
		 
		 }
		else if( this.property.type.name=="enum")
		{
		
		}
		
		 });
	
        
        }
});		

	}

/*	  jQuery.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    jQuery.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};


jQuery('#finishTask').submit(function(e) {
	  e.preventDefault();
	  var data=$('#finishTask').serializeObject();
	 data['id']=taskid;
	// alert("id="+taskid+"&"+jQuery('#finishTask').serialize())
	      jQuery.ajax({
	        url: '${pageContext.request.contextPath}/finishTask',
	      async:false,
			data:"id="+taskid+"&"+jQuery('#finishTask').serialize(),
			type:"POST",
	        success: function(response) {
			
		
			 location.reload();
	        }
	      
	    });
		  
	});*/

</script>
</body>
</html>