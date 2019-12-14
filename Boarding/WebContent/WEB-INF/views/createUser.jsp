
<!DOCTYPE html>
<html lang="en">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%><%@taglib
	uri="http://www.springframework.org/tags/form" prefix="form"%><%@taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>


<style type="text/css" media="screen">
.error {
	color: red;
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
 .scheduler-border
        {
        width:80%;
        }
		fieldset.scheduler-border {
    border: 1px groove #ddd !important;
    padding: 0 1.4em 1.4em 1.4em !important;
    margin: 0 0 1.5em 0 !important;
    -webkit-box-shadow:  0px 0px 0px 0px #000;
            box-shadow:  0px 0px 0px 0px #000;
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
	width:60%;
	}
	.col-sm-2
	{
	width:40%;
	}
	table {
    border-collapse: separate;
    border-spacing: 10px 50px;
}

</style>
<script type="text/javascript">
	jQuery(document).ready(function() {

		jQuery("#createuser").validate({});
	});
</script>
</head>

<body>

	<div class="container">
		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>

		<form:form id="createuser" action="createUser" commandName="user">
			<fieldset class="scheduler-border">
				<legend>
					<b style='color: green'>Create Vendor</b>
				</legend>
				<div style="padding: 2px;">
					<table  style="border-spacing: 10px; padding: 5px;" align="center">
						<tr align="center">
							<td><label for="vendorName">Vendor Name</label> <FONT
								color="red"></FONT></td>
							<td ><form:input required="true"
									path="vendorName" id="vendorName" class="form-control" cssStyle="width: 200px;" />
							</td>
							<td></td>
							<td></td>
						</tr>
						<tr align="center">
							<td><label for="firstName">First Name</label> <FONT
								color="red"></FONT></td>
							<td ><form:input required="true"
									path="firstName" id="firstName" class="form-control" cssStyle="width: 200px;" /></td>
							<td></td>
							<td></td>
						</tr>
						<tr align="center">
							<td><label for="lastName">Last Name</label> <FONT
								color="red"></FONT></td>
							<td ><form:input required="true"
									path="lastName" id="lastName" class="form-control" cssStyle="width: 200px;" /></td>
							<td></td>
							<td></td>
						</tr>
						<tr align="center">
							<td><label for="email">email</label> <FONT color="red"></FONT>
							</td>
							<td ><form:input type="text" required="true"
									path="email" id="email" class="form-control" cssStyle="width: 200px;" /></td>
							<td></td>
							<td></td>
						</tr>
						<tr align="center">
							<td><label for="password">Password</label> <FONT
								color="red"></FONT></td>
							<td ><form:password required="true"
									path="password" id="password" class="form-control" cssStyle="width: 200px;" /></td>
							<td></td>
							<td></td>
						</tr>

						<tr align="center">
							<td colspan="4" align="center"><input type="submit"
								value="CreateUser"
								style="float: left; margin-left: 100px; color: #006600; width: 80px"
								id="login-form-submit-button"> <br /> <br /></td>
						</tr>
						<tr align="center">
							<td colspan="4" style="color: #CA2514;"></td>
						</tr>

					</table>
				</div>
			</fieldset>
		</form:form>
		</div>
</body>
</html>
