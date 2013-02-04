package main;
/**
 * @author
 * Mehmet Öðütcan
 * 2013
 */
public class verikayit {
	
	
	public static String ad, soyad, email, tel, gsm;
	public static String lisansustu, lisans, onlisans, yabancidil, beceri, odul;
	public static String program, os, egitim, dergi, ilgi;
	public static String dogtar, dogumyeri, kulup, cinsiyet, medenihal, askerlik, ehliyet;
	public static String ref_ad, ref_soyad, ref_kurum, ref_unvan, ref_tel;
	public static String picture_path, search_path;
	
	
	public static void kaydet_kisisel(String a, String b, String c, String d, String e) {
		ad =a;
		soyad=b;
		email =c;
		gsm = d;
		tel = e;
	}
	
	public static void kaydet_egitim(String a, String b, String c, String d, String e, String f){

		lisansustu = a;
		lisans = b;
		onlisans =c;
		yabancidil =d;
		beceri = e;
		odul = f;
		
	}
	
	
	public static void kaydet_deneyim(String a, String b, String c, String d, String e){
		
		program = a;
		os = b;
		egitim = c;
		dergi = d;
		ilgi = e;
	}
	
	public static void kaydet_kisisel_devam(String a, String b, String c, String d, String e, String f, String g){

		dogtar = a;
		dogumyeri = b;
		kulup = c;
		cinsiyet = d;
		medenihal = e;
		askerlik = f;
		ehliyet = g;
	}
	
	public static void kaydet_referans(String a, String b, String c, String d, String e) {
		
		ref_ad = a;
		ref_soyad =b;
		ref_kurum =c;
		ref_unvan =d;
		ref_tel=e;
		
	}
	
	public static void kaydet_path(String path, String sPath){
	
		picture_path = path;
		search_path = sPath;
		
	}
}
