<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Booking</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous"/>
	<link rel="stylesheet" href="<c:url value="/resources/css/background.css"/>">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
.form-signin {
	max-width: 330px;
	margin: auto;
}
</style>
</head>
<body>
	<nav class="navbar navbar-default sb">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand">Hotels</a>
			</div>
		</div>
	</nav>
	<c:if test="${requestScope.getAlert == 'yes'}">
		<div class="alert alert-warning alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong>Registration Failed!</strong>
		</div>
	</c:if>
	<div align="text-center">
        <form:form class="form-signin" action="register" method="post" commandName="user">
            <table border="0">
                <tr>
                    <td colspan="2" align="center"><h1 id="sign-in-title" class="h3 mb-3 font-weight-normal">Please Sign Up</h1></td>
                </tr>
                <tr>
                    <td>Username:</td>
                    <td><form:input class="form-control term" type="email" path="userEmail" placeholder="Email" required="required"/>
                    <form:errors path="userEmail" cssClass="text-danger"/></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><form:input class="form-control term" type="password" path="userPassword" placeholder="Password" required="required"/>
                    <form:errors path="userPassword" cssClass="text-danger"/></td>
                </tr>
                <tr>
                    <td>Firstname:</td>
                    <td><form:input class="form-control term" path="firstname" placeholder="Firstname" required="required" />
                    <form:errors path="firstname" cssClass="text-danger"/></td>
                </tr>
                <tr>
                    <td>Lastname</td>
                    <td><form:input class="form-control term" path="lastname" placeholder="Lastname" required="required"/>
                    <form:errors path="lastname" cssClass="text-danger"/></td>
                </tr>
                <tr>
                    <td>Role:</td>
                    <td><select class="form-control term" name="type" id="type">
					<option>User</option>
					<option>Admin</option>
				</select></td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><input class="btn btn-primary" type="submit" value="Sign Up" /></td>
                </tr>
            </table>
        </form:form>
    </div>
</body>
</html>
