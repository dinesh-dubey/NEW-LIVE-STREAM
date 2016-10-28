package com.liveStream.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.kaltura.client.types.KalturaBaseEntry;
import com.kaltura.client.types.KalturaMediaEntry;
import com.kaltura.client.types.KalturaMediaListResponse;
import com.liveStream.KalturaUtil;

/**
 * Servlet implementation class getVodList
 */

public class getVodList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getVodList() {
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
	 * @return 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList vodlist=new ArrayList();
		ArrayList vodlist1=new ArrayList();

		
		KalturaUtil ku=new KalturaUtil();
		
		vodlist = ku.retrieveMediaMetaData();
		System.out.println(vodlist.size());
		HashMap hashMap_Vod = null;
		String media_entry_name ;
		String media_entry_thumbnail;
		int media_entry_duration;
		String media_entry_URL ;
		HashMap hashMap_live = null;
		
		
		
		java.util.Iterator<KalturaBaseEntry> i = vodlist.iterator();
		
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

			vodlist1.add(hashMap_Vod);
			vodlist1.add(hashMap_Vod);
			vodlist1.add(hashMap_Vod);
		}
		
		
		
		vodlist.add(vodlist.get(0));
		System.out.println("vodsize"+vodlist1.size());
		
		
		request.setAttribute("list_vod", vodlist1);
	
	
	}

}
