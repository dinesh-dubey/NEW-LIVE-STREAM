package com.liveStream.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddContextVar1
 */

public class AddContextVar1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddContextVar1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	public static  void removecontextattr (ServletContext context){
        
	    context.removeAttribute("taggedVideo");
	    System.out.println("hi"+context.getAttribute("taggedVideo"));
	    context.removeAttribute("videoduration");
	    context.removeAttribute("pushtime");

	    
	    
	    }
}
