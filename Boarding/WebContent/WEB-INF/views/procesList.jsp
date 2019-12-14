	
<!DOCTYPE html>
<html lang="en">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%><%@taglib
	uri="http://www.springframework.org/tags/form" prefix="form"%><%@taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" import="org.activiti.engine.repository.ProcessDefinition,java.util.List" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<head>
  <style type="text/css">
.form-style-2{
background-image:url("<spring:url value='/images/paper.gif'/>");
	border-radius:10px;
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
 <style type="text/css" media="screen">
 .error
 {
 color:red;
 }
  select {
      width: 200px;
    }
        <!--
    
        DIV.container {
background-image:url("<spring:url value='/images/paper.gif'/>");
	border-radius:10px;
 margin: auto; width: 90%; margin-bottom: 10px;}
       
        h2 {
            margin: 0;
        }

        .drawers-wrapper {
            position: relative;
            width: 188px;
            
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
	.container
{
	
	
	
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
<div style="float:left;width:200px">
	 <div class="drawers-wrapper">
	<div class="boxcap captop"></div>
    <ul class="drawers">
        <li class="drawer">
		 <h2 class="drawer-handle">PROCESS</h2>
    <ul class="alldownloads">
	<c:forEach items="${procesList}" var="item">
  <li style="cursor:pointer" id="${item.getId()}"><a  onclick="getTaskData('${item.getId()}','${item.getName()}')" />${item.getName()}</a></li>
   
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
<div style="margin-left: 200px;"  >

<div id="status"></div>
<div class="container">

<form id="processform" class="form-style-2" role="form" style="min-height:300px;" ></form>

</div>
</div>

</div>
<script type="text/javascript" class="demo"  id="demo-1">

var allVendors=[];


jQuery( document ).ready(function() {

	
	
 <c:if test="${not empty procesList}">

		getTaskData('${procesList.get(0).getId()}','${procesList.get(0).getKey()}');
		</c:if>
	getAllvendors()
	
	  jQuery.ui.autocomplete.filter = function (array, term) {
                var matcher = new RegExp("^" + jQuery.ui.autocomplete.escapeRegex(term), "i");
                return jQuery.grep(array, function (value) {
                        return matcher.test(value.label || value.value || value);
                });
				}
	

jQuery("#processform").validate({
    
  });


	
		});


function getAllvendors()
{
	
jQuery.ajax({
    url: '${pageContext.request.contextPath}/allVendors',
    dataType: 'json',
	
    success: function(response) {
    	//alert(JSON.stringify(response));
		jQuery.each(response, function(key) {
		allVendors.push(this.ID_)
		
		})
		//alert(allVendors)
    }
});
}
    
	function getTaskData(id,name)
	{
		 jQuery("#processform").css("background", "url('<spring:url value='/images/ajax-loader .gif'/>')   no-repeat center center");
	jQuery("#processform").empty()
taskid=id;
jQuery.ajax({
        url: '${pageContext.request.contextPath}/processFormData/'+id,
        dataType: 'json',
		
        success: function(response) {
		//alert(JSON.stringify(response))
		jQuery("#processform").css("background", "none");
		var data=[];
		
		data.push({"type" : "legend","html" : "<b style='color:green'>New Request</b>"})
		
		
		 
		
		 jQuery.each(response.properties, function(key) {
		//alert(JSON.stringify(this.property))
		 if(this.property.type.name=="string")
		 {
		 if(this.property.id=="additionalDetails")
		 {
		
		 data.push({"type": "label","html":[{"type":"span","html":this.property.name+":"},{"name":this.property.id,"class":"textarea-field","id":this.property.id,"type":"TextArea","rows":"10","required":this.property.required}]});
		 }
		else if(this.property.id=="vendor")
		 {
		  data.push({"type": "label","html":[{"type":"span","html":this.property.name+":"},{"name":this.property.id,"class":"input-field","id":this.property.id,"type":"text","rows":"10","required":this.property.required}]});
		 }
		 else
		 {
		  data.push({"type": "label","html":[{"type":"span","html":this.property.name+":"},{"name":this.property.id,"class":"input-field","id":this.property.id,"type":"text","rows":"10","required":this.property.required}]});
		 }
		 }
		  else if(this.property.type.name=="date")
		 {
		 data.push({"type": "label","html":[{"type":"span","html":this.property.name+":"},{"name":this.property.id,"class":"input-field","id":this.property.id,"type":"text","rows":"10","required":this.property.required}]});
		 }
		  else if(this.property.type.name=="long")
		 {
		 data.push({"type": "label","html":[{"type":"span","html":this.property.name+":"},{"name":this.property.id,"id":this.property.id,"type":"number","required":this.property.required,"class":"input-field"}]});
		 }
		else if( this.property.type.name=="enum")
		{
		 data.push({"type": "label","html":[{"type":"span","html":this.property.name+":"},{"name":this.property.id,"class":"select-field","id":this.property.id,"type":"select", "options":this.propertyvalues,"required":this.property.required}]});
		}
		
		 });
		 
		 data.push({"name":"id","id":"id","type":"hidden","value":name})
data.push({"type": "label","html":[{"type":"span","html":"&nbsp;"},{"type":"submit","value":"Start Process"}]})
/*	data.push({"type" : "div","id":"uploaddiv","name":"uploaddiv","html" : {"type" : "div", "id":"mulitplefileuploader", "name":"mulitplefileuploader","html" :"Upload"}});		
*/
//alert(JSON.stringify(data))
jQuery('.form-style-2').dform({
    "action":"${pageContext.request.contextPath}/processIntiation",
    "method":"post",
    "html":[
        {
            "type":"fieldset",
			"class":"scheduler-border",
            "html":data
			}]
			});
			

			
			 jQuery.each(response.properties, function(key) {
		
		 if(this.property.type.name=="string")
		 {
		  if(this.property.id=="vendor")
		  {
		  jQuery( "#"+this.property.id ).autocomplete({
      source: allVendors
    });
		  }
		 }
		  else if(this.property.type.name=="date")
		 {
			  if(name=="HelpDesk")
				 {
				  
				  jQuery( "#"+this.property.id ).datetimepicker({
					  minDate: 0,
					  dateFormat: 'dd/mm/yy',
					  timeFormat: "hh:mm tt"

					  
				  });
				  
				 }
			  else
				  {
		if(this.property.id =="startDate")
		{
		
		jQuery("#startDate").datepicker({
        minDate: 0,
       
       numberOfMonths: 2,
	   dateFormat: 'dd/mm/yy',
        onSelect: function(selected) {
          $("#endDate").datepicker("option","minDate", selected)
        }
    });
    
		}
		else
		{
		
		jQuery("#endDate").datepicker({ 
        minDate: 0,
    
       numberOfMonths: 2,
	   dateFormat: 'dd/mm/yy',
        onSelect: function(selected) {
           $("#startDate").datepicker("option","maxDate", selected)
        }
    }); 
		}
		
				  }
		 }
  else if(this.property.type.name=="long")
		 {
		 //jQuery( "#"+this.property.id ).spinner({min:0});
		 
		 }
		else if( this.property.type.name=="enum")
		{
			//$( "#"+this.property.id ).selectmenu();
		}
		 if(name=="HelpDesk")
		 {
		 			 	jQuery("#employeeid").val(response.propertyvalues.employeeid);
		 	jQuery("#employeename").val(response.propertyvalues.employeename);
		 	jQuery("#phonenumber").val(response.propertyvalues.phonenumber);
		 	
		 }
		 });
			
   
   
	jQuery("#mulitplefileuploader").uploadFile({
	url: "${pageContext.request.contextPath}/upload",
	method: "POST",
	allowedTypes:"jpg,png,gif,doc,docx,xml,pdf,zip",
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
});		

	}

	  jQuery.fn.serializeObject = function()
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
		  
	});

</script>
</body>
</html>