package main;

/**
 * @author
 * Mehmet Öðütcan
 * 2013
 */

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.*;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;


@WebServlet("/pdfolustur")
public class pdfolustur extends HttpServlet
{
	public static String filename = null;
	public static String path = null;
	public static String pdfPath = null;
	public static Document doc;
	public static String url = null;
	
	public pdfolustur()
	{
		super();
	}

	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws javax.servlet.ServletException, java.io.IOException
	{
		
		process(req, resp);	
		save_database(); 
	}
	
	
	protected void save_database(){
		
			try {
				main.db.database_open();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			main.db.insert(); 
		
	}
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws IOException{
			
		req.setCharacterEncoding("UTF-8");
	    resp.setContentType("application/pdf; charset=utf-8");
	    DocumentException ex = null;
	    ByteArrayOutputStream baosPDF = null;
			        		
	        		
	    try
			    
	    {
			        			
	    	baosPDF = generatePDFDocumentBytes(req, this.getServletContext());
			        	
	    	StringBuffer sbFilename = new StringBuffer();
			 
	    	sbFilename.append("ozgecmis_");
			        			
	    	sbFilename.append(main.verikayit.ad + main.verikayit.soyad+"_");
			
			
	    	sbFilename.append(System.currentTimeMillis());
			
	    	sbFilename.append(".pdf");
			        			

	    	resp.setHeader("Cache-Control", "max-age=30");
			
	    	
			
	    	resp.setContentType("application/pdf");
			
	    	StringBuffer sbContentDispValue = new StringBuffer();
			
	    	sbContentDispValue.append("inline");
			
	    	sbContentDispValue.append("; filename=");
			
	    	sbContentDispValue.append(sbFilename);
			
	    	
			
	    	resp.setHeader("Content-disposition",sbContentDispValue.toString());

			        			
	    	resp.setContentLength(baosPDF.size());

			
	    	ServletOutputStream sos;

			        			
			
	    	sos = resp.getOutputStream();
			        			
			
	    	baosPDF.writeTo(sos);
			        			
			
	    	sos.flush();
			
	    }
		
	    catch (DocumentException dex)
		
	    {
			        			
	    	resp.setContentType("text/html");
			        			
	    	PrintWriter writer = resp.getWriter();
			        			
	    	writer.println(
			        					
	    			this.getClass().getName() 
			        					
	    			+ " caught an exception: " 
			        					
	    			+ dex.getClass().getName()
			        					
	    			+ "<br>");
			        			
	    	writer.println("<pre>");
			        			
	    	dex.printStackTrace(writer);
			        			
	    	writer.println("</pre>");
			        		
	    }
			        		
	    finally
			        		
	    {
			        			
	    	if (baosPDF != null)
			        			
	    	{
			        				
	    		baosPDF.reset();
			        			
	    	}
			        		
	    }	        		
	
	}     
	/**
	 *  
	 * @param req must be non-null
	 * 
	 * @return a non-null output stream. The output stream contains
	 *         the bytes for the PDF document
	 * 
	 * @throws DocumentException
	 * @throws IOException 
	 * 
	 */
	protected ByteArrayOutputStream generatePDFDocumentBytes(HttpServletRequest req, ServletContext ctx)
					throws DocumentException, IOException
		{   
		 
		doc = new Document();
		path = "pdf/";
		pdfPath = path + "ozgecmis_" + main.verikayit.ad +main.verikayit.soyad + "_" + System.currentTimeMillis() +".pdf";
	   
		try {
	    	PdfWriter.getInstance(doc, new FileOutputStream(pdfPath));	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		ByteArrayOutputStream baosPDF = new ByteArrayOutputStream();
		PdfWriter docWriter = null;

		try
		{
			docWriter = PdfWriter.getInstance(doc, baosPDF);
			
			doc.addAuthor(this.getClass().getName());
			doc.addCreationDate();
			doc.addProducer();
			doc.addCreator(this.getClass().getName());
			doc.addTitle("This is a title.");
			doc.addKeywords("pdf, itext, Java, open source, http");		
			doc.setPageSize(PageSize.LETTER);
			
			doc.open();   
	        BaseFont _bFont;   
	        _bFont = BaseFont.createFont("arial.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED); 
	        Font _headerFont = new Font(_bFont, 10);

	        String picture = req.getParameter("gravatar");
			
			main.Gravatar gravatar = new main.Gravatar();
			gravatar.setSize(125);
			gravatar.setRating(main.GravatarRating.GENERAL_AUDIENCES);
			gravatar.setDefaultImage(main.GravatarDefaultImage.IDENTICON);	
			url = gravatar.getUrl(picture);
			
	        Image img = Image.getInstance(url);
			img.setAlignment(Image.RIGHT);
			img.scaleAbsolute(125, 125);
			doc.add(img);
	
			doc.add(new Paragraph("Kiþisel Bilgiler", _headerFont));
			doc.add(new Paragraph(" "));
								  					
			doc.add(new Paragraph(
					main.verikayit.ad +"  "+ main.verikayit.soyad, _headerFont 
					));
			doc.add(new Paragraph(
					main.verikayit.email, _headerFont
					));
			doc.add(new Paragraph(
					main.verikayit.tel, _headerFont
					));
			doc.add(new Paragraph(
					main.verikayit.gsm, _headerFont
					));
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph("Doðum tarihi ve yeri:"
					+main.verikayit.dogtar+"  -  "+main.verikayit.dogumyeri, _headerFont
					));
			
			if( main.verikayit.cinsiyet.equals("Kadin") ){				
			} 
			else{
					   
				doc.add(new Paragraph("Askerlik:"
						+main.verikayit.askerlik, _headerFont			
						));
			}
			doc.add(new Paragraph("Cinsiyet:"
								+main.verikayit.cinsiyet, _headerFont
								));
						
			doc.add(new Paragraph("Ehliyet sÄ±nÄ±fÄ±:"
								+main.verikayit.ehliyet, _headerFont
								));
			 
			if( !main.verikayit.medenihal.equals("") ){
						 
				doc.add(new Paragraph("Medeni Hal:"
						+main.verikayit.medenihal, _headerFont					
						));
						
			} 
						
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph("Eðitim Bilgileri", _headerFont));
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph("Lisansüstü:" 
					+main.verikayit.lisansustu, _headerFont
					));
			doc.add(new Paragraph("Lisans:"
					+main.verikayit.lisans, _headerFont
					));
			doc.add(new Paragraph("Önlisans:"
					+main.verikayit.onlisans, _headerFont
					));
			doc.add(new Paragraph("Yabancý Dil:"
					+main.verikayit.yabancidil, _headerFont
					));
			doc.add(new Paragraph("Beceriler:"
					+main.verikayit.beceri, _headerFont
					));
			doc.add(new Paragraph("Alýnmýþ Ödüller:"
					+main.verikayit.odul, _headerFont
					));
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph("Deneyimler", _headerFont));
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph("Bilgisayar programlarý:"
					+main.verikayit.program, _headerFont
					));
			doc.add(new Paragraph("Ýþletim Sistemleri:"
					+main.verikayit.os, _headerFont
					));
			doc.add(new Paragraph("Eðitimler:"
					+main.verikayit.egitim, _headerFont
					));
			
			doc.add(new Paragraph("Takip edilen yayýnlar:"
					+main.verikayit.dergi, _headerFont
					));
			
			doc.add(new Paragraph("ilgi Alanlarý:"
					+main.verikayit.ilgi, _headerFont
					));
			
			doc.add(new Paragraph("Üye kulupler:"
					+main.verikayit.kulup, _headerFont
					));
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph("Referans Bilgileri", _headerFont));
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph("Ýsim:				"
					+main.verikayit.ref_ad +"  "+ main.verikayit.ref_soyad, _headerFont
					));
			doc.add(new Paragraph("Kurum:"
					+main.verikayit.ref_kurum, _headerFont
					));
			doc.add(new Paragraph("Ünvan:"
					+main.verikayit.ref_unvan, _headerFont
					));
			doc.add(new Paragraph("Ýletiþim:"
					+main.verikayit.ref_tel, _headerFont
					));
		}
		catch (DocumentException dex)
		{
			baosPDF.reset();
			throw dex; 
		}
		finally
		{
			if (doc != null)
			{
				doc.close();
			}
			if (docWriter != null)
			{
				docWriter.close();
			}
		}

		if (baosPDF.size() < 1)
		{
			throw new DocumentException(
				"document has "
				+ baosPDF.size()
				+ " bytes");		
		}
		return baosPDF;
	}
}
