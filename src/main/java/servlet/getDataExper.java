package main;
/*
 * @author
 * Mehmet Öðütcan
 * 2013
 */
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class getDataExper
 */
@WebServlet("/getDataExper")
public class getDataExper extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getDataExper() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        
		String program, os, egitim, dergi, ilgi;

		program = request.getParameter("program");
		os = request.getParameter("os");
		egitim = request.getParameter("egitim");
		dergi = request.getParameter("dergi");
		ilgi = request.getParameter("ilgi");

		main.verikayit.kaydet_deneyim(program, os, egitim, dergi, ilgi);
	
		String adres = "personal.html";
		RequestDispatcher dispatcher = request.getRequestDispatcher(adres);
		dispatcher.forward(request, response);
	}

}
