package com.liveStream.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class AddContextVar1
 */

public class AddContextVar1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOGGER = Logger.getLogger(AddContextVar1.class
			.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddContextVar1() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String data = request.getParameter("taggedVideo");
	    String[] attributes=data.split(",");
	    ServletContext context=getServletContext(); 
	    long pushtime  =System.currentTimeMillis();   
	    context.setAttribute("taggedVideo", attributes[0]);
	    context.setAttribute("videoduration", attributes[1]);
	    context.setAttribute("pushtime", pushtime);
	    try {
            Thread.sleep((Integer.valueOf(attributes[1]))*1000);
            removecontextattr(context);
        } catch (Exception e) {
            LOGGER.error(e);
        }    
	}

	public static  void removecontextattr(ServletContext context){
	    context.removeAttribute("taggedVideo");
	    context.removeAttribute("videoduration");
	    context.removeAttribute("pushtime");
	    }
}
