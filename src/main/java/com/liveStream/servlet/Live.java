package com.liveStream.servlet;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.liveStream.LoadProperty;

/**
 * Servlet implementation class Live */

public class Live extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOGGER = Logger.getLogger(Live.class.getName());
	public static String ks = "";
	LoadProperty property = new LoadProperty();

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
		LOGGER.info("REquest Referal::"+request.getRequestURI());
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
			String requestReferal =  request.getRequestURI();
			getVodData(request, response, ks, requestReferal, loadProperty);
			if(requestReferal.equalsIgnoreCase("/livestream/")){
				getLiveData(request, response, ks, loadProperty);
			}

		} else {
			LOGGER.info("POST request not worked");
		}
	}

	public void getVodData(HttpServletRequest request,
			HttpServletResponse response, String ks, String referal, LoadProperty loadProperty) {
		try {
			URL obj = new URL(
					loadProperty.getProperty("SERVICE_URL")+"api_v3/?service=media&action=list&ks="
							+ ks + "&filter:tagsLike=");
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // success
				BufferedReader in = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				String inputLine;
				StringBuffer sb = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					sb.append(inputLine);
				}
				in.close();
				ArrayList<HashMap<String, String>> list_Vod = new ArrayList();
				HashMap<String, String> hashMap_Vod = null;
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
				JSONArray vodJsonArray= new JSONArray();
				JSONObject vodJsonData= new JSONObject();
				for (int temp = 0; temp < nList.getLength(); temp++) {
					hashMap_Vod = new HashMap();
					Node nNode = nList.item(temp);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;
						if (eElement
								.getElementsByTagName("tags")
								.item(0)
								.getTextContent()
								.equalsIgnoreCase(
										property.getProperty("TAG_NAME_FOR_ADS")))
							continue;
						if(null!=referal && referal.equalsIgnoreCase("/livestream/Live")){
							JSONObject vodObj = new JSONObject();
							vodObj.put("media_entry_URL", eElement
									.getElementsByTagName("dataUrl").item(0)
									.getTextContent());
							
							vodObj.put("media_entry_name", eElement
									.getElementsByTagName("name").item(0)
									.getTextContent());
							vodObj.put("media_entry_thumbnail", eElement
									.getElementsByTagName("thumbnailUrl").item(0)
									.getTextContent());
							vodObj.put("media_entry_duration", eElement
									.getElementsByTagName("duration").item(0)
									.getTextContent());
							vodObj.put("media_entryId", eElement
									.getElementsByTagName("id").item(0)
									.getTextContent());
							vodJsonArray.put(vodObj);
						}
						if(null!=referal && referal.equalsIgnoreCase("/livestream/")){
							hashMap_Vod.put("media_entry_URL", eElement
									.getElementsByTagName("dataUrl").item(0)
									.getTextContent());
							
							hashMap_Vod.put("media_entry_name", eElement
									.getElementsByTagName("name").item(0)
									.getTextContent());
							hashMap_Vod.put("media_entry_thumbnail", eElement
									.getElementsByTagName("thumbnailUrl").item(0)
									.getTextContent());
							hashMap_Vod.put("media_entry_duration", eElement
									.getElementsByTagName("duration").item(0)
									.getTextContent());
							hashMap_Vod.put("media_entryId", eElement
									.getElementsByTagName("id").item(0)
									.getTextContent());
							list_Vod.add(hashMap_Vod);
						}
					}
				}
				if(null!=referal && referal.equalsIgnoreCase("/livestream/Live")){
				vodJsonData.put("vodJson",vodJsonArray);
				response.getWriter().write(vodJsonData.toString());
				}
				if(null!=referal && referal.equalsIgnoreCase("/livestream/")){
				request.setAttribute("list_vod", list_Vod);
				request.setAttribute("player_id",
						property.getProperty("PLAYER_ID"));
				request.setAttribute("partner_id",
						property.getProperty("PARTNER_ID"));
				request.setAttribute("script_source",
						property.getProperty("SCRIPT_SOURCE"));
				request.setAttribute("ui_conf_id",
						property.getProperty("UI_CONF_ID"));
				request.setAttribute("live_entryId", property.getProperty("LIVE_ENTRY_ID"));
				}
				LOGGER.info("VOD COUNT: "+ list_Vod.size());
			}
		} catch (Exception e) {
			LOGGER.error("Ohh Something went wrong while getting VOD:"+e);
		}
	}

	public void getLiveData(HttpServletRequest request,
			HttpServletResponse response, String ks, LoadProperty loadProperty) {
		try {
			HashMap<String, String> live_data = new HashMap<String, String>();
			String entryId = property.getProperty("LIVE_ENTRY_ID");
			URL obj = new URL(
					loadProperty.getProperty("SERVICE_URL")+"api_v3/?service=livestream&action=get&ks="
							+ ks + "&entryId=" + entryId);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // success
				BufferedReader in = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				String inputLine;
				StringBuffer sb = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					sb.append(inputLine);
				}
				in.close();
				LOGGER.info("--LIVE data--: "+sb.toString());
				DocumentBuilderFactory factory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				StringBuilder xmlStringBuilder = new StringBuilder(
						sb.toString());
				ByteArrayInputStream input = new ByteArrayInputStream(
						xmlStringBuilder.toString().getBytes("UTF-8"));
				Document doc = builder.parse(input);
				Element root = doc.getDocumentElement();
				NodeList nList = doc.getElementsByTagName("result");
				Node nNode = nList.item(0);
				if (nNode.getNodeType() == Node.ELEMENT_NODE  && !sb.toString().contains("ENTRY_ID_NOT_FOUND")) {
					Element eElement = (Element) nNode;
					live_data.put("liveStreamName", eElement
							.getElementsByTagName("name").item(0)
							.getTextContent());
					live_data.put("live_entryId", eElement
							.getElementsByTagName("id").item(0)
							.getTextContent());
					live_data.put("live_data_url", eElement
							.getElementsByTagName("dataUrl").item(0)
							.getTextContent());
					}
				request.setAttribute("hashMap_live", live_data);
			}
		} catch (Exception e) {
			LOGGER.error("ohh Something went wrong while getting Live Video: "+e);
		}
	}

}
