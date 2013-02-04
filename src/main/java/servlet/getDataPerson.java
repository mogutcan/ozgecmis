package main;
/**
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
 * Servlet implementation class getDataPerson
 */
@WebServlet("/getDataPerson")
public class getDataPerson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getDataPerson() {
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
        
		String dogtar, dogumyeri, kulup, cinsiyet, medenihal, askerlik, ehliyet;

		dogtar = request.getParameter("dogumtar");
		dogumyeri = request.getParameter("dogumyeri");
		kulup = request.getParameter("kulup");
		cinsiyet = request.getParameter("secim01");
		medenihal = request.getParameter("secim02");
		askerlik = request.getParameter("secim03");
		ehliyet = request.getParameter("secim04");
			
		main.verikayit.kaydet_kisisel_devam(dogtar, dogumyeri, kulup, cinsiyet, medenihal,askerlik, ehliyet);
		String adres = "credential.html";
		RequestDispatcher dispatcher = request.getRequestDispatcher(adres);
		dispatcher.forward(request, response);
		
		
	
	}

}
