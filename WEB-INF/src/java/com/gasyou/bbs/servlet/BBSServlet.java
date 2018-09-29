package com.gasyou.bbs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gasyou.bbs.db.DB;

public class BBSServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		DB db = DB.getInstance();

		String msg = req.getParameter("msg");

		if (msg != null && msg.trim().length() > 0) {
			db.addMessage(msg);
		}

		getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
	}
}