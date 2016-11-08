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

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.liveStream.LoadProperty;

/**
 * Servlet implementation class FetchTagVideos1
 */

public class FetchTagVideos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOGGER = Logger.getLogger(FetchTagVideos.class
			.getName());
	LoadProperty property = new LoadProperty();
	public static String ks = "";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException { 
		LoadProperty loadProperty = new LoadProperty();
		StringBuilder url= new StringBuilder();
		url.append(loadProperty.getProperty("SERVICE_URL")).append("api_v3/?service=session&action=start&secret=").append(loadProperty.getProperty("ADMIN_SECRET")).append("&userId=").append(loadProperty.getProperty("USER_SECRET")).
		append("&type=").append(loadProperty.getProperty("TYPE")).append("&partnerId=").append(loadProperty.getProperty("PARTNER_ID")).append("&expiry=").append(loadProperty.getProperty("EXPIRY_TIME"));
		URL obj = new URL(url.toString());
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		int responseCode = con.getResponseCode();
		LOGGER.info("POST Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer sb = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				sb.append(inputLine);
			}
			in.close();
			String abc = sb.toString();
			ks = abc.substring(abc.indexOf("<result>"),
					abc.indexOf("</result>")).replace("<result>", "");
			getAdds(request, response, ks);
		} else {
			LOGGER.info("POST request not worked");
		}
	}

	public void getAdds(HttpServletRequest request,
			HttpServletResponse response, String ks) {
		try {
			Map<String, String> metaData_media = new HashMap<String, String>();
			URL obj = new URL(
					"http://kalturalivestream/api_v3/?service=media&action=list&ks="
							+ ks + "&filter:tagsLike=tavant");
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");

			int responseCode = con.getResponseCode();
			LOGGER.info("POST Response Code :: " + responseCode);

			if (responseCode == HttpURLConnection.HTTP_OK) { // success
				BufferedReader in = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				String inputLine;
				StringBuffer sb = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					sb.append(inputLine);
				}
				in.close();
				DocumentBuilderFactory factory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				StringBuilder xmlStringBuilder = new StringBuilder(
						sb.toString());
				ByteArrayInputStream input = new ByteArrayInputStream(
						xmlStringBuilder.toString().getBytes("UTF-8"));
				Document doc = builder.parse(input);
				Element root = doc.getDocumentElement();
				NodeList nList = doc.getElementsByTagName("item");
				for (int temp = 0; temp < nList.getLength(); temp++) {
					Node nNode = nList.item(temp);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;
						metaData_media.put(
								eElement.getElementsByTagName("id").item(0)
										.getTextContent()
										+ ","
										+ eElement.getElementsByTagName("duration").item(0)
										.getTextContent(), eElement
										.getElementsByTagName("name").item(0)
										.getTextContent());
					}
				}
				request.setAttribute("metaData_media", metaData_media);
				request.getRequestDispatcher("/views/tavantads.jsp").forward(
						request, response);
			}
		} catch (Exception e) {
			LOGGER.error("Ohh Something went wrong while getiing ad videos: "+e);
		}
	}
}
