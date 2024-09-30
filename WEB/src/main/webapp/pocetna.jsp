<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pocetna</title>
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
    width: 90%;    
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
		<c:if test="${empty prijavljen}">
			<a href="/Projekat/prijava.jsp" id="logout-button">Prijavi se</a>
		</c:if>	
		<c:if test="${!empty prijavljen}">
			<a href="/Projekat/projekat/kolekcije">Kolekcije</a>
			<a href="/Projekat/projekat/logout" id="logout-button">Odjavi se</a>
		</c:if>
		<a href="/Projekat/projekat/izvestaji">Izvestaji</a>
	</div>
	
	<br><br>
	<c:if test="${!empty prijavljen}">
		<h1 style="text-align: center"> Prijavljen si kao: ${prijavljen.imePrezime }</h1>	
	</c:if>
	<div class="objava">
		<form method="get" action="/Projekat/projekat/pretraga">
			<h3>Pretrazi igru</h3>
			Ime: <input type="text" name="imeIgre"> <br>
			Zanr: <select name="zanrIgre">
			<option value="-1">Odaberite zanr</option>
			<c:forEach items="${zanrovi}" var="z">
				<option value="${z.id }">${z.zanr }</option>
			</c:forEach>
			</select>
			<input type="submit" value="Pretrazi" name="">
		</form>
	</div>
	<h1 style="text-align: center">${pretraga }</h1>
	<c:if test="${!empty content}">
		<c:forEach items="${content}" var="c">
			<div class="objava">
				<div class="naslov">
					<br>
					IME: ${c.ime } <br>
				</div>
				<div class="sadrzaj">
					
					OPIS: ${c.opis } <br>
					ZANR: ${c.zanrigre.zanr } <br><br>
					Specifikacije: 
					<table border="1" style=" margin-left: auto; margin-right: auto;">
						<tr>
							<th> CPU  </th>
							<th> GPU </th>
							<th> RAM </th>
							<th> STORAGE </th>
						</tr>
						<tr>
							<td> ${c.cpu }  </td>
							<td> ${c.gpu } </td>
							<td> ${c.ram } </td>
							<td> ${c.storage } </td>
						</tr>
					</table>			
				</div>
				<br><div class="linija"></div>
			
				<c:if test="${!empty komentari}">
					<c:forEach items="${komentari}" var="k">
						<c:if test="${k.igra.id == c.id}">
							<br>
							${k.korisnik.imePrezime} <br><br>
							${k.sadrzaj }<br>
							<br><div class="linija"></div>
						</c:if>
						
						
						
					</c:forEach>
				</c:if>
				<c:if test="${!empty prijavljen}">
					<div>
						<br>

						
						<h3>Komentarisi:</h3>
						<form action="/Projekat/projekat/komentarisi" method="post">
							<textarea class="komentar" name="komentar" rows="4" cols="120" style="resize: vertical;"></textarea>
							<input type="hidden" id="komentatorID" name="komentatorID" value="${prijavljen.id }">
							<input type="hidden" id="postID" name="postID" value="${c.id }">
							<input type="submit" value="Komentarisi" name="postaviKomentar">
						</form>
					</div>
				</c:if>
			</div>
		</c:forEach>				
	</c:if>
			
</body>
</html>