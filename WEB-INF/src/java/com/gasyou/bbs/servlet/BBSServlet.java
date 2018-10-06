package com.gasyou.bbs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gasyou.bbs.db.DB;
import com.gasyou.bbs.util.PostToken;
import com.gasyou.bbs.util.PropertyMap;
import com.gasyou.bbs.util.StringUtils;

public class BBSServlet extends HttpServlet {

	public static final String PATH_AJAX = "/ajax.servlet";

	public static final String PATH_SUBMIT = "/submit.servlet";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String path = req.getServletPath();

		if (path.equals(PATH_AJAX)) {
			String token = PostToken.getToken(req, this.getClass().getName(), true);
			responseJson(req, resp, "{\"token\": " + token + "}");
		} else {
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String path = req.getServletPath();

		// Check token
		String requestToken = req.getParameter("_ct");
		String checkToken = PostToken.getToken(req, this.getClass().getName(), false);

		if (StringUtils.isEmpty(requestToken) || !StringUtils.equals(requestToken, checkToken)) {
			throw new ServletException("Illegal Access.");
		} else {
			PostToken.deleteToken(req, this.getClass().getName());
		}

		DB db = DB.getInstance();

		String msg = req.getParameter("msg");

		if (msg != null && msg.trim().length() > 0) {
			db.addMessage(msg);
		}

		if (PATH_AJAX.equals(path)) {
			responseJson(req, resp, "{}");
		} else {
			getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
		}
	}

	private void responseJson(HttpServletRequest req, HttpServletResponse resp, String body) throws IOException {
		resp.setStatus(HttpServletResponse.SC_OK);
		resp.setContentType("text/javascript");
		resp.getWriter().print(body);

		// Access Control
		String origin = req.getHeader("Origin");
		String[] allowOrigins = PropertyMap.getInstance().getProperty("allow.origins", new String[0]);
		for (String allowOrigin : allowOrigins) {
			if (StringUtils.equals(origin, allowOrigin)) {
				resp.setHeader("Access-Control-Allow-Origin", origin);
				resp.setHeader("Access-Control-Allow-Credentials", String.valueOf(true));
				break;
			}
		}
	}
}