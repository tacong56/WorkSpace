<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Trang tìm kiếm sách</title>
	<style>
		div{
			height: 50px;
		}
	</style>
</head>
<body>
	<div>
		<form action = "BookSearchServlet" method = "post">
			<input type="search" name="search" id="search">
			<button>Search</button>
		</form>
	</div>
	
	<div>
		<table border = 1 cellpadding = 5>
			<caption><h2>Kết quả tìm kiếm</h2></caption>
			<tr>
				<th>Title</th>
				<th>Author</th>
			</tr>
			
			<c:forEach var="book" items="${listBook}">
				<tr>
					<td><c:out value="${book.title}"/></td>
					<td><c:out value="${book.author}"/></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>