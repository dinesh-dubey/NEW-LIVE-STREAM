package com.liveStream.servlet;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.liveStream.LoadProperty;

/**
 * Servlet implementation class FetchTagVideos1
 */

public class FetchTagVideos1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LoadProperty property= new LoadProperty();  
	public static String ks = "";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchTagVideos1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    URL obj = new URL("http://kalturalivestream/api_v3/?service=session&action=start&secret=e9d684a068e7ac7d99432f0a6583246e&userId=fc1e90f90de623f429208f7761c0f786&type=2&partnerId=100&expiry=345345345");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer sb = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                sb.append(inputLine);
            }
            in.close();
            String abc = sb.toString();
            ks=abc.substring(abc.indexOf("<result>"),abc.indexOf("</result>")).replace("<result>", "");
            System.out.println(ks);
            getAdds(request,response,ks);
                
        } else {
            System.out.println("POST request not worked");
        }
	}

	public void getAdds(HttpServletRequest request, HttpServletResponse response,String ks)
	{
try{
            Map<String,String> metaData_media=new HashMap<String,String>();
            URL obj = new URL("http://kalturalivestream/api_v3/?service=media&action=list&ks="+ks+"&filter:tagsLike=tavant");
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

            int responseCode = con.getResponseCode();
            System.out.println("POST Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { //success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer sb = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    sb.append(inputLine);
                }
                in.close();
                System.out.println(sb.toString());
                DocumentBuilderFactory factory =
                        DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        StringBuilder xmlStringBuilder = new StringBuilder(sb.toString());
                        ByteArrayInputStream input =  new ByteArrayInputStream(
                           xmlStringBuilder.toString().getBytes("UTF-8"));
                        Document doc = builder.parse(input);   
                        Element root = doc.getDocumentElement();
                        System.out.println("Root element :" 
                                + doc.getDocumentElement().getNodeName());
                             NodeList nList = doc.getElementsByTagName("item");
                             System.out.println("----------------------------");
                             for (int temp = 0; temp < nList.getLength(); temp++) {
                                 Node nNode = nList.item(temp);
                                 System.out.println("\nCurrent Element :" 
                                    + nNode.getNodeName());
                                 if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element eElement = (Element) nNode;
                                    
                                    System.out.println("First Name : " 
                                       + eElement
                                       .getElementsByTagName("name")
                                       .item(0)
                                       .getTextContent());
                                    System.out.println("Last Name : " 
                                    + eElement
                                       .getElementsByTagName("thumbnailUrl")
                                       .item(0)
                                       .getTextContent());
                                    System.out.println("Nick Name : " 
                                    + eElement
                                       .getElementsByTagName("duration")
                                       .item(0)
                                       .getTextContent());
                                    System.out.println("Marks : " 
                                    + eElement
                                       .getElementsByTagName("dataUrl")
                                       .item(0)
                                       .getTextContent());
                                    
                                    
                                    metaData_media.put(eElement
                                       .getElementsByTagName("name")
                                       .item(0)
                                       .getTextContent(), eElement
                                       .getElementsByTagName("id")
                                       .item(0)
                                       .getTextContent()+","+eElement
                                       .getElementsByTagName("duration")
                                       .item(0)
                                       .getTextContent());
                                 }
                                 
                                 request.setAttribute("metaData_media", metaData_media);
                                 request.getRequestDispatcher("/views/tavantads.jsp").forward(request, response);
        }
            }
            }catch(Exception e)
            {
                e.printStackTrace();
            }
	}
}
