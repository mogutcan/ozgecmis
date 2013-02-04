package main;
/*
 * @author
 * Mehmet Öðütcan
 * 2013
 */
import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.management.Query;


public class db {
	
	public static String ad, soyad, resimLink, pdfLink;
	public static ArrayList<String> liste = new ArrayList<String>();
	public static String username, message;
	public static int destroy;
	public static Connection conn = null;
	public static Statement st = null;
	public static ResultSet rs = null;
	public static PreparedStatement pst = null;
	public static String query_ = null;
	
	 
	public static void database_open() throws ClassNotFoundException, SQLException{
		
		String url = "*********";
		String user = "********";
		String password = "****"
        Class.forName("org.postgresql.Driver");
		conn = DriverManager.getConnection(url, user, password);
	}
	
	public static void select(String similar) throws SQLException{
		
		String searchQuery = "SELECT resimlink, ad, soyad, pdflink from tKullanici where ad like '"+similar+"%';";
		PreparedStatement ifade = conn.prepareCall(searchQuery);
		ResultSet sonuc = ifade.executeQuery();
		
		while( sonuc.next() ){
			resimLink = sonuc.getString("resimLink");
			ad = sonuc.getString("ad");
			soyad = sonuc.getString("soyad");
			pdfLink = sonuc.getString("pdfLink");
			
			liste.add(resimLink);
			liste.add(ad);
			liste.add(soyad);
			liste.add(pdfLink);
		}
		
		
     }
	
	// Admin sorgulamasi yapalim
	
	public static void adminControl(String admin, String pass) throws SQLException{
		
		
		String searchQuery = "SELECT  * from SignIn where username= '"+admin+"';";
		PreparedStatement ifade = conn.prepareCall(searchQuery);
		ResultSet sonuc = ifade.executeQuery();
		
		if( sonuc.next() ){

			username = sonuc.getString("username");
			if (sonuc.getString("passwd").equals(pass)){
				message = "Hosgeldiniz " + username;		
			}
			else{
				message = "Parolanizi yanlis girdiniz";
			}
					
		}
		else{
			message = "Giris yetkiniz yok.";
		}
	}
	
	public static void getAllUser() throws SQLException{
		
		String alluser = "Select * from tKullanici;";
		PreparedStatement ifade = conn.prepareCall(alluser);
		ResultSet sonuc = ifade.executeQuery();
		
		while( sonuc.next() ){

			resimLink = sonuc.getString("resimLink");
			ad = sonuc.getString("ad");
			soyad = sonuc.getString("soyad");
			pdfLink = sonuc.getString("pdfLink");

			liste.add(resimLink);
			liste.add(ad);
			liste.add(soyad);
			liste.add(pdfLink);
		}
		
		
	}
	// Admin tum kullanicilari siler:
	//FIXME: destroy icin pdfadi yerine benzersiz kullanici id'si kullanilsin. 
	public static void destroy(String user){
		String destroy = "delete from tKullanici where pdfLink='"+user+"' ";
		
		try {
			 
			pst = conn.prepareStatement(destroy);
			pst.executeUpdate();	
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void insert(){
		
		 query_ = "INSERT INTO tKullanici (ad, soyad, resimLink, pdfLink) VALUES('"					
			+main.verikayit.ad+ "', '"+main.verikayit.soyad+"', '"+main.pdfolustur.url+"', '"+main.pdfolustur.pdfPath+"');";
		try {
			pst = conn.prepareStatement(query_);
			pst.executeUpdate();
			                                                                 
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}