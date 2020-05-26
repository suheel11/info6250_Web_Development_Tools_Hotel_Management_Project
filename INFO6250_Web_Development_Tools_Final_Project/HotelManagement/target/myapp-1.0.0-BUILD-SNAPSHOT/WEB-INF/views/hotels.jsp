<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.mycom.pojo.Hotel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Hotel Manage</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous"/>
<link rel="stylesheet" href="<c:url value="/resources/css/background.css"/>">
</head>

<body>
	<nav class="navbar navbar-default sb">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="hotels">Hotels</a>
				<c:set var="user" scope="session" value="${sessionScope.curUser}" />
				<c:if test="${user == null}">
					<a class="navbar-brand" href="register">Register</a>
					<a class="navbar-brand" href="login">Login</a>
				</c:if>
				<c:if test="${user != null}">
					<a class="navbar-brand"> login in as <c:out
							value="${user.getUserEmail()}" /></a>
					<a class="navbar-brand" href="viewbooking">View Booking</a>
					<c:if test="${user.getType() == 'Admin'}">
						<a class="navbar-brand" href="managebooking">Manage Booking</a>
					</c:if>
					<a class="navbar-brand" href="logout">Logout</a>
				</c:if>
			</div>
		</div>
	</nav>
	<header class="jumbotron sb">
		<div class="container">
			<h1>View Our Hotel Selection</h1>
			<p>Search For Hotel</p>
			<div class="row sb">
				<div class="col-lg-12 sb">
					<div class="row sb">
						<form action="/myapp/hotelsearch" method="POST" id="search-form">
							<div class="col-lg-3 col-md-3 col-sm-12 p-0">
								<input type="text" class="form-control search-slt term" placeholder="Search By Name" name="searchName" />
							</div>
							<button class="btn btn-danger wrn-btn">Submit</button>
							<c:if test="${user.getType() == 'Admin'}">
								<a class="btn btn-primary btn-large" href="/myapp/hoteladd">
									Add New Hotel</a>
							</c:if>
						</form>
					</div>
				</div>
			</div>
		</div>
	</header>
	<div class="row sb">
		<div class="col-lg-12">
			<h3>View Our Hotel Selection</h3>
		</div>
	</div>

	<div class="row text-center" style="display: flex; flex-wrap: wrap;">
		<c:forEach var="hotel" items="${requestScope.hotels}">
			<div class="col-md-3 col-sm-6 sb">
				<div class="thumbnail">
					<img src="${hotel.getImage()}">
					<div class="caption">
						<h4>
							<c:out value="${hotel.getHotelName()}" />
						</h4>
					</div>
					<p>
						<a class="btn btn-primary"
							href="/myapp/hoteldetail/?id='${hotel.getId()}'">More Info</a>
					</p>
				</div>
			</div>
		</c:forEach>
	</div>
	</div>
	</div>
</body>