<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">

<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <meta http-equiv="X-UA-Compatible" content="ie=edge">
   <title>Booking</title>
   <!-- Latest compiled and minified CSS -->
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
       integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
   <link rel="stylesheet" href="<c:url value="/resources/css/background.css"/>">
</head>
<body>
    <nav class="navbar navbar-default sb">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand">Hotels</a>
            </div>
        </div>
    </nav>

<div class="container">
  <div class="row">
        <form:form class="form-signin" action="/myapp/hoteladd.htm" method="post" commandName="hotel">
            <table border="0">
                <tr>
                    <td colspan="2" align="center"><h1 style="text-align:center">Add Your Hotel</h1></td>
                </tr>
                <tr>
                    <td>Hotel-Name:</td>
                    <td>
                    <form:input class="form-control term" path="hotelName" placeholder="Hotel name" required="required"/>
                    <form:errors path="hotelName" cssClass="text-danger"/>
					</td>
                </tr>
                <tr>
                    <td>Location:</td>
                    <td>
                    <form:input class="form-control term" path="location" placeholder="Location" required="required"/>
                    <form:errors path="location" cssClass="text-danger"/>
					</td>
                </tr>
                <tr>
                    <td>ImagePath:</td>
                    <td>
                    <form:input class="form-control term" path="image" placeholder="Image" required="required"/>
                    <form:errors path="image" cssClass="text-danger"/>
					</td>
                </tr>
                <tr>
                    <td>Price</td>
                    <td><form:input class="form-control term" path="price" placeholder="Price" required="required"/>
                    <form:errors path="price" cssClass="text-danger"/>
					</td>
                </tr>
                <tr>
                    <td>Description:</td>
                    <td>
                    <form:input class="form-control term" path="description" placeholder="Description" required="required"/>
                    <form:errors path="description" cssClass="text-danger"/>
					</td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><input class="btn btn-primary" type="submit" value="Add Hotel" /></td>
                </tr>
            </table>
        </form:form>
  </div>
</div>
</body>
</html>
