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
 * Servlet implementation class getDataCredential
 */
@WebServlet("/getDataCredential")
public class getDataCredential extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getDataCredential() {
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
		String ref_ad, ref_soyad, ref_kurum, ref_unvan, ref_tel;
		ref_ad = request.getParameter("ref_ad");
		ref_soyad = request.getParameter("ref_soyad");
		ref_kurum = request.getParameter("ref_kurum");
		ref_unvan = request.getParameter("ref_unvan");
		ref_tel = request.getParameter("ref_tel");
		main.verikayit.kaydet_referans(ref_ad, ref_soyad, ref_kurum, ref_unvan, ref_tel);
		
		String adres = "picture.html";
		RequestDispatcher dispatcher = request.getRequestDispatcher(adres);
		dispatcher.forward(request, response);
	}
}