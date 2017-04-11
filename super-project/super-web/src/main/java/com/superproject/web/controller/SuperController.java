package com.superproject.web.controller;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.ModelAttribute;


@Scope("prototype")
@SuppressWarnings("all")
public class SuperController{
	
	protected Logger logger = Logger.getLogger(getClass());
	
	public HttpServletRequest request = null;
	public HttpServletResponse response = null;

	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	protected HttpSession getSession() {
		return this.request.getSession();
	}

	protected void redirect(String url) throws IOException {
		this.response.sendRedirect(url);
	}

	protected void forward(String url) throws ServletException, IOException {
		this.request.getRequestDispatcher(url).forward(this.request, this.response);
	}

	protected ServletOutputStream getServletOutputStream() throws IOException {
		return this.response.getOutputStream();
	}

	protected Writer getWriter() throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		return this.response.getWriter();
	}

	protected void getWriter(String str) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		this.response.getWriter().write(str);
	}

	public void forwardFromRequest() throws ServletException, IOException {
		String url = this.request.getParameter("url");
		this.request.getRequestDispatcher(url).forward(this.request, this.response);
	}

	protected void setAttribute(String key, Object value) {
		this.request.setAttribute(key, value);
	}

}
