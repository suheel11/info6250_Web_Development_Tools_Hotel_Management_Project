<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <h1 style="text-align:center">Update Hotel ${requestScope.hotel.getHotelName()}</h1>
        <div style="width:30%; margin:30px auto;">
            <form action="/myapp/hotelupdate/?id='${requestScope.hotel.getId()}'" method="post">
                <div class="form-group">
                    <input class="form-control term" name="hotelName" type="text" placeholder="${requestScope.hotel.getHotelName()}" required>
                </div>
                <div class="form-group">
                    <input class="form-control term" name="location" type="text" placeholder="${requestScope.hotel.getLocation()}" required>
                </div>
                <div class="form-group">
                  <input class="form-control term" name="image" type="text" placeholder="${requestScope.hotel.getImage()}" required>
                </div>
                <div class="form-group">
                        <input class="form-control term" name="price" type="text" pattern="[0-9]*" placeholder="${requestScope.hotel.getPrice()}" required>
                </div>
                <div class="form-group">
                        <input class="form-control term" name="description" type="text" placeholder="${requestScope.hotel.getDescription()}" required>
                </div>
                <div class="form-group">
                    <button class="btn btn-lg btn-primary btn-block">Update Hotel</button>
                </div>
            </form>
        </div>
  </div>
</div>
</body>
</html>
