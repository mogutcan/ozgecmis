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
 * Servlet implementation class getDataEdu
 */
@WebServlet("/getDataEdu")
public class getDataEdu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getDataEdu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
        	response.setContentType("text/html; charset=utf-8");
        
		String lisansustu, lisans, onlisans, yabancidil, beceri, oduller;

		lisansustu = request.getParameter("lisansustu");
		lisans = request.getParameter("lisans");
		onlisans = request.getParameter("onlisans");
		yabancidil = request.getParameter("yabancidil");
		beceri = request.getParameter("beceri");
		oduller = request.getParameter("oduller");

		main.verikayit.kaydet_egitim(lisansustu, lisans, onlisans, yabancidil, beceri, oduller);	
	
		String adres = "experience.html";
		RequestDispatcher dispatcher = request.getRequestDispatcher(adres);
		dispatcher.forward(request, response);
	
	}

}
