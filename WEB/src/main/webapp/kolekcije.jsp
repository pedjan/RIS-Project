<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Kolekcije</title>
</head>
<style>

body {
	background-color: blue;
	background-repeat: no-repeat;
 	background-size: cover;
	font-family: "Open Sans", sans-serif;
	font-size: 15px;
	line-height: 1.5;
}

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


.objava { 
	background-color: #f5f7f8;
	border: 1px solid;
	margin: auto;
	margin-top: 50px;
	width: 45%;
	padding-left: 15px;
	padding-right: 15px;
	padding-bottom: 15px;
        box-shadow: 0 2px 4px 0 rgba(0,0,0,0.16),0 2px 10px 0 rgba(0,0,0,0.12);
        border-radius: 14px;
    }

    .sadrzaj {
        padding: 0px 10px;
    }

    .komentari {
        display: flex;
        padding: 0px 10px;
    }

    .like {
        flex: 1;
        text-align: center;
        background-color: green; 
    }  
    .dislike {
        flex: 1;
        text-align: center;
        background-color: red; 
    }  
    .naslov {
        display: flex;
        padding: 0px 10px;
    }
    .linija {
        padding: 1px;
        background-color: #704c4c;
    }
    .linijaa {
        margin-left: 50px;
        margin-right: 50px;
        padding: 1px;
        background-color: #704c4c;
    }
    textarea {
        width: 100%;
    }
    a{
        text-decoration:none;
        color: black;
    }
    .link-button, button:hover{
        background: none;
        border: none;
        color: black;
        text-decoration: none;
    }
    img {
        display: block;
        margin: auto;
        max-width: 100%;
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



button:hover, input[type=submit]:hover {
    background-color: #008000;
}

.objava { 
	background-color: #f5f7f8;
	border: 1px solid;
	margin: auto;
	margin-top: 50px;
	width: 45%;
	padding-left: 15px;
	padding-right: 15px;
	padding-bottom: 15px;
        box-shadow: 0 2px 4px 0 rgba(0,0,0,0.16),0 2px 10px 0 rgba(0,0,0,0.12);
        border-radius: 14px;
    }
    .linija {
        padding: 1px;
        background-color: #704c4c;
    }
</style>
<body>
	<div class="navigacija tamno">
        <a href="/Projekat/projekat/pocetna">Pocetna</a>
		<c:if test="${empty prijavljen}">
			<a href="/Projekat/prijava.jsp" id="logout-button">Prijavi se</a>
		</c:if>	
		<c:if test="${!empty prijavljen}">
			<a href="/Projekat/projekat/kolekcije">Kolekcije</a>
			<a href="/Projekat/projekat/logout" id="logout-button">Odjavi se</a>
		</c:if>
		<a href="/Projekat/projekat/izvestaji">Izvestaji</a>
	</div>
	<br>
	<br>
	<br>
	<div class="objava">
		<h1 style="text-align: center">Kolekcija omiljenih igara korisnika ${prijavljen.imePrezime }</h1> <br>
		<c:if test="${!empty kolekcije}">
			<h3>Prikaz kolekcije</h3>
			<form method="get" action="/Projekat/projekat/prikazKolekcije">
				Izaberi kolekciju: 
				<select name="kolekcijaID">
					<option value="">Odaberi kolekciju</option>
					<c:forEach items="${kolekcije}" var="k">
						<option value="${k.id}">${k.ime }</option>
					</c:forEach>
				</select>
				<input type="submit" value="Prikazi">
			</form>			
		</c:if>
		<c:if test="${empty kolekcije}">
			<h2>Ne postoji ni jedna kolekcija!</h2><br>
		</c:if>
		
		<form method="post" action="/Projekat/projekat/napraviKolekciju">
			<h3>Napravi novu kolekciju: </h3>
			Ime kolekcije: <input type="text" name="imeKolekcije">
			<input type="hidden" id="kID" name="kID" value="${prijavljen.id }">
			<input type="submit" value="Napravi">
		</form>
		<br><div class="linija"></div><br>
		
		<c:if test="${!empty kolekcija}">
			<h3>Prikaz igara za kolekciju: ${kolekcija.ime}</h3>
			<c:if test="${empty kolekcija.igraukolekcijis}">
				Ova kolekcija ja prazna
			</c:if>
			<h3>Dodaj igre u kolekciju</h3>
			<form method="post" action="/Projekat/projekat/igreUkolekciju">
				Izaberi igru: 
				<select name="igraID">
					<option value="">Odaberi igru</option>
					<c:forEach items="${igre}" var="i">
						<option value="${i.id}">${i.ime }</option>
					</c:forEach>
				</select>
				<input type="hidden" id="kID" name="kID" value="${kolekcija.id }">
				<input type="submit" value="Dodaj">
			</form>	
			<c:forEach items="${kolekcija.igraukolekcijis }" var="c">
				<div class="naslov">
					<br>
					IME: ${c.igra.ime } <br>
				</div>
				<div class="sadrzaj">
					OPIS: ${c.igra.opis } <br>
					ZANR: ${c.igra.zanrigre.zanr } <br><br>
					Specifikacije: 
					<table border="1" style=" margin-left: auto; margin-right: auto;">
						<tr>
							<th> CPU  </th>
							<th> GPU </th>
							<th> RAM </th>
							<th> STORAGE </th>
						</tr>
						<tr>
							<td> ${c.igra.cpu }  </td>
							<td> ${c.igra.gpu } </td>
							<td> ${c.igra.ram } </td>
							<td> ${c.igra.storage } </td>
						</tr>
					</table>			
				</div>		
			</c:forEach>
		</c:if>
	</div>
</body>
</html>