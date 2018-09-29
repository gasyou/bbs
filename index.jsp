<%@ page import="com.gasyou.bbs.db.DB"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page contentType="text/html; charset=UTF-8"%>

<c:set var="msgs" value="<%=DB.getInstance().getMessages()%>" />

<%
	response.addHeader("Access-Control-Allow-Origin", 		"http://localhost");
	response.addHeader("Access-Control-Allow-Credentials",	"true");
%>

<!doctype html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<div>
			<form action="/bbs/submit.servlet" method="post">
				<input type="hidden" name="_charset_" value="UTF-8">
				<div class="form-group">
					<label for="message">Message</label>
					<input type="text" name="msg" class="form-control" id="message" placeholder="Enter message" autocomplete="off">
				</div>
				<button type="submit" class="btn btn-primary">Submit</button>
			</form>
		</div>
		<div class="table-responsive">
			<table id="message-table" class="table  table-dark">
				<thead>
					<th>投稿内容</th>
				</thead>
				<c:forEach var="msg" items="${msgs}" varStatus="status">
					<tr>
						<td><c:out value="${msg}" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>