package com.liveStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.kaltura.client.types.KalturaBaseEntry;
import com.kaltura.client.types.KalturaMediaEntry;

/**
 * Servlet implementation class Live
 */
public class Live extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger LOGGER = Logger.getLogger(Live.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Live() {
		super();
	}

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
		// String entryId = "1_6a0qjqkg";
		// String entryId="0_oosnpuph";
		KalturaSessionGen ksg = new KalturaSessionGen();
		LoadProperty property = new LoadProperty();
		try {
			KalturaUtil util = new KalturaUtil();
			ArrayList<KalturaBaseEntry> list = util.retrieveMediaMetaData();
			java.util.Iterator<KalturaBaseEntry> i = list.iterator();
			String media_entry_name = null;
			String media_entry_thumbnail = null;
			int media_entry_duration;
			String media_entry_URL = null;
			ArrayList<HashMap<String, String>> list_Vod = new ArrayList();
			HashMap hashMap_Vod = null;
			HashMap hashMap_live = null;
			while (i.hasNext()) {
				KalturaMediaEntry media_entry = (KalturaMediaEntry) i.next();
				hashMap_Vod = new HashMap();
				media_entry_name = media_entry.name;
				media_entry_thumbnail = media_entry.thumbnailUrl;
				media_entry_duration = media_entry.duration;
				media_entry_URL = media_entry.dataUrl;
				hashMap_Vod.put("media_entry_URL", media_entry_URL);
				hashMap_Vod.put("media_entry_name", media_entry_name);
				hashMap_Vod.put("media_entry_thumbnail", media_entry_thumbnail);
				hashMap_Vod.put("media_entry_duration", media_entry_duration);
				hashMap_Vod.put("media_entryId", media_entry.id);
				list_Vod.add(hashMap_Vod);
			}
			hashMap_live = new HashMap<>();
			hashMap_live = util.retrieveLiveStreamMetaData();
			request.setAttribute("hashMap_live", hashMap_live);
			String a = property.getProperty("SCRIPT_SOURCE");
			request.setAttribute("player_id", property.getProperty("PLAYER_ID"));
			request.setAttribute("partner_id",
					property.getProperty("PARTNER_ID"));
			request.setAttribute("script_source",
					property.getProperty("SCRIPT_SOURCE"));
			request.setAttribute("ui_conf_id",
					property.getProperty("UI_CONF_ID"));

			/* util.retrievePlaylistMetaData(); */

			// request.getRequestDispatcher("/pages/index.html?mediaUrl=media_entry_URL").forward(request,
			// response);
			if (request.getAttribute("list_vod") != null)
				list_Vod = (ArrayList<HashMap<String, String>>) request
						.getAttribute("list_vod");
			request.setAttribute("list_vod", list_Vod);
		} catch (Exception e) {
				LOGGER.error(e);
		}

	}

}
