package com.liveStream.servlet;

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
import com.liveStream.KalturaUtil;

/**
 * Servlet implementation class getVodList
 */

public class getVodList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOGGER = Logger.getLogger(getVodList.class
			.getName()); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getVodList() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	
	}

	/**
	 * @return 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList vodlist=new ArrayList();
		ArrayList vodlist1=new ArrayList();
		KalturaUtil ku=new KalturaUtil();
		vodlist = ku.retrieveMediaMetaData();
		LOGGER.info("VOD list size:  "+vodlist.size());
		HashMap vodMap = null;
		String media_entry_name ;
		String media_entry_thumbnail;
		int media_entry_duration;
		String media_entry_URL ;
		HashMap hashMap_live = null;
		java.util.Iterator<KalturaBaseEntry> vodIterator = vodlist.iterator();
		while (vodIterator.hasNext()) {
			KalturaMediaEntry media_entry = (KalturaMediaEntry) vodIterator.next();
			vodMap = new HashMap();
			media_entry_name = media_entry.name;
			media_entry_thumbnail = media_entry.thumbnailUrl;
			media_entry_duration = media_entry.duration;
			media_entry_URL = media_entry.dataUrl;
			vodMap.put("media_entry_URL", media_entry_URL);
			vodMap.put("media_entry_name", media_entry_name);
			vodMap.put("media_entry_thumbnail", media_entry_thumbnail);
			vodMap.put("media_entry_duration", media_entry_duration);
			vodMap.put("media_entryId", media_entry.id);
			vodlist1.add(vodMap);
		}
		//TODO  why below code line is required
		vodlist.add(vodlist.get(0));
		request.setAttribute("list_vod", vodlist1);
	
	
	}

}
