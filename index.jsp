<%@ page import="com.gasyou.bbs.db.DB"%>
<%@ page import="com.gasyou.bbs.util.PostToken"%>
<%@ page import="com.gasyou.bbs.util.PropertyMap"%>
<%@ page import="com.gasyou.bbs.util.StringUtils"%>
<%@ page import="com.gasyou.bbs.servlet.BBSServlet"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page contentType="text/html; charset=UTF-8"%>

<c:set var="msgs" value="<%=DB.getInstance().getMessages()%>" />

<%
	// Access Control
	String origin = request.getHeader("Origin");
	String[] allowOrigins = PropertyMap.getInstance().getProperty("allow.origins", new String[0]);
	for (String allowOrigin : allowOrigins) {
		if (StringUtils.equals(origin, allowOrigin)) {
			response.setHeader("Access-Control-Allow-Origin", origin);
			response.setHeader("Access-Control-Allow-Credentials", String.valueOf(true));
			break;
		}
	}

	// Context Path
	String contextPath = PropertyMap.getInstance().getProperty("context.path", "/bbs");
%>
<c:set var="contextPath" value="<%=contextPath%>" />

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
			<form action="${contextPath}<%= BBSServlet.PATH_SUBMIT %>" method="post">
				<input type="hidden" name="_ct" value="<%= PostToken.getToken(request, BBSServlet.class.getName(), true) %>">
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