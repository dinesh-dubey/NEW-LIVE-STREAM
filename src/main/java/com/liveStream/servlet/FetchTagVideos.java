package com.liveStream.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaltura.client.KalturaApiException;
import com.kaltura.client.types.KalturaBaseEntry;
import com.kaltura.client.types.KalturaMediaInfo;
import com.kaltura.client.types.KalturaMediaListResponse;
import com.kaltura.client.types.KalturaMetadata;
import com.kaltura.client.types.KalturaMetadataListResponse;
import com.liveStream.KalturaSessionGen;
import com.liveStream.KalturaUtil;

/**
 * Servlet implementation class FetchTagVideos
 */

public class FetchTagVideos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchTagVideos() {
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
	
	
		KalturaSessionGen ksg=new KalturaSessionGen();

		Map metaData_media=new HashMap<>();
		KalturaMediaListResponse list;
		KalturaUtil ku=new KalturaUtil();
		
		 
		
		try {
			
			
			list = KalturaSessionGen.getClient().getMediaService().list();
			if (list.totalCount > 0) {
				System.out.println("The account contains " + list.totalCount + " entries.");
				for (KalturaBaseEntry entry : list.objects) {
					if(null != entry.tags &&entry.tags.equalsIgnoreCase(ku.propertyLoad
							.getProperty("TAG_NAME_FOR_ADS"))){
					metaData_media.put(entry.name,entry.id);
					System.out.println("\t \"" + entry.name + "\""+" "+entry.tags + "\""+" "+entry.id);
				
				}}
			} else {
				System.out.println("This account doesn't have any entries in it.");
			
			}
			System.out.println("The Tagged videos Count " + metaData_media.size() + " entries");
			request.setAttribute("metaData_media", metaData_media);
			request.getRequestDispatcher("/views/tavantads.jsp").forward(request, response);
		} 
		
		catch (KalturaApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
