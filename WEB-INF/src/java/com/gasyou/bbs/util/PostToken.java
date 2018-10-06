package com.gasyou.bbs.util;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class PostToken {


	public static String getToken(HttpServletRequest req, String tokenKey, boolean create) {
		HttpSession session = req.getSession(create);

		if (session != null) {
			String token = (String) session.getAttribute(tokenKey);
			if (StringUtils.isNotEmpty(token)) {
				return token;
			} else if (StringUtils.isEmpty(token) && create) {
				token = String.valueOf(new Random().nextInt());
				session.setAttribute(tokenKey, token);
				return token;
			}
		}

		return null;
	}

	public static void deleteToken(HttpServletRequest req, String tokenKey) {
		HttpSession session = req.getSession();
		session.removeAttribute(tokenKey);
	}
}
