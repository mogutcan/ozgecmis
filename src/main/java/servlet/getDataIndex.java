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
 * Servlet implementation class getDataIndex
 */
@WebServlet("/getDataIndex")
public class getDataIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getDataIndex() {
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
        
		String adres = "education.html";
		String ad, soyad, email, gsm, tel;

		ad = 	request.getParameter("uname");
		soyad = request.getParameter("sname");
		email = request.getParameter("email");
		gsm = 	request.getParameter("gsm");
		tel = 	request.getParameter("tel");
			
		main.verikayit.kaydet_kisisel(ad, soyad, email, gsm, tel);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(adres);
		dispatcher.forward(request, response);
	
	}

}
