<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Prijava</title>
</head>
<style>
.navigacija {	
	position: fixed;
	top: 0;
	width: 100%;
	z-index: 1;
    margin-left: -8px;
    box-shadow: 0 2px 4px 0 rgba(0,0,0,0.16),0 2px 10px 0 rgba(0,0,0,0.12);
}

.navigacija a {
	display: inline-block;
	text-decoration: none;
	padding: 10px 15px;
}

.navigacija a:hover {
	background-color: rgb(159,179,189);
}

.navigacija a, .navigacija a:visited {
	color: white;
}

#logout-button {
	float: right;
	width: 70px;
}

.tamno {
	color: white;
	background-color: #704c4c;
}
body {
	background-color: blue;
	background-repeat: no-repeat;
 	background-size: cover;
	font-family: "Open Sans", sans-serif;
	font-size: 15px;
	line-height: 1.5;
}

.kontejner {
	vertical-align: top;
	text-align: center;
	width: 40%;
	max-width: 400px;
	min-width: 200px;

	box-shadow: 0 2px 4px 0 rgba(0,0,0,0.16),0 2px 10px 0 rgba(0,0,0,0.12);
	border-radius: 4px;
	padding: 15px 16px;
	margin: auto;
	margin-top: 100px;
	background-color: #f5f7f8;
}

input {
	padding: 12px 20px;
    margin: 8px 0;
}

input[type=text], input[type=password], input[type=date] {
    width: 100%;    
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}

input[type=checkbox], input[type=radio] {
    margin: 15px 0;
}

button, input[type=submit] {
    background-color: #355E3B;
    color: white;
    margin: 8px 0;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

button {
	padding: 8px 13px;
}

input[type=submit] {
	width: 100%;
	padding: 14px 20px;
}

button:hover, input[type=submit]:hover {
    background-color: #008000;
}

</style>

<body>
	<div class="navigacija tamno">
        <a href="/Projekat/projekat/pocetna">Pocetna</a>
		<c:if test="${!empty prijavljen}">
			<a href="/Projekat/projekat/logout" id="logout-button">Odjavi se</a>
		</c:if>
	</div>
	<c:if test="${empty prijavljen}">
	    <div class="kontejner">
			<h2>Uloguj se</h2>
			<form method="post" action="/Projekat/projekat/login">
				<label for="korIme">Korisnicko ime:</label> 
				<input type="text" name="korIme" value=""><br>
	
				<label for="sifra">Sifra:</label>
				<input	type="password" name="sifra"><br>
	
				<input type="submit" name="loginButton" value="Uloguj se">
			</form>
	        Nemate nalog? <a href="/Projekat/registracija.jsp">Registruj se</a>
		</div>
	</c:if>
	<c:if test="${!empty prijavljen}">
		<br>
		<h1>Vec ste prijavljeni</h1>
	</c:if>
</body>
</html>