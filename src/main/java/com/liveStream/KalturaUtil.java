package com.liveStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.kaltura.client.KalturaApiException;
import com.kaltura.client.types.KalturaBaseEntry;
import com.kaltura.client.types.KalturaLiveStatsEvent;
import com.kaltura.client.types.KalturaLiveStreamConfiguration;
import com.kaltura.client.types.KalturaLiveStreamEntry;
import com.kaltura.client.types.KalturaMediaEntry;
import com.kaltura.client.types.KalturaMediaListResponse;
import com.kaltura.client.types.KalturaOperationAttributes;
import com.kaltura.client.types.KalturaPlaylist;

public class KalturaUtil {

	static Logger log = Logger.getLogger(KalturaUtil.class.getName());

	public static LoadProperty propertyLoad=null;
	
	public KalturaUtil()
	
	{
		if(propertyLoad==null)
		 propertyLoad=new LoadProperty();
		
		
	}
	/*
	public static void main(String... s)
	{
		//new KalturaUtil().getProperty();
		
		try
		{
			
			new KalturaUtil();
		System.out.print(propertyLoad.getProperty("LIVE_ENTRY_ID"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		
		}
	*/
	
	public  KalturaMediaListResponse retrieveMediaList() throws KalturaApiException {
		KalturaMediaListResponse list =null;
		try
		{
			 
		 new KalturaSessionGen();
		list = KalturaSessionGen.getClient().getMediaService().list();
		}
		catch(Exception e)
		{
			log.error(e.getMessage());
			
		}
		
	return list;
	
	}
		
	

	public  ArrayList retrieveTaggedMediaMetaData()  {

		ArrayList<KalturaBaseEntry> metaData_media=new ArrayList();
		try
		{
		KalturaMediaListResponse list =  retrieveMediaList();
	
		
		if (list.totalCount > 0) {
			System.out.println("The account contains " + list.totalCount + " entries.");
			for (KalturaMediaEntry entry : list.objects) {
				if(entry.tags.equalsIgnoreCase(propertyLoad.getProperty("TAG_NAME_FOR_VOD")))
				metaData_media.add((KalturaBaseEntry)entry);
			
			
			
			}
		} else {
			System.out.println("This account doesn't have any entries in it.");
		}
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}
	return metaData_media;
	
	}
	
	
	
	
	
	
	
	
	public  ArrayList retrieveMediaMetaData()  {

		ArrayList<KalturaBaseEntry> metaData_media=new ArrayList();
		try
		{
		KalturaMediaListResponse list =  retrieveMediaList();
	
		
		if (list.totalCount > 0) {
			System.out.println("The account contains " + list.totalCount + " entries.");
			for (KalturaMediaEntry entry : list.objects) {
			//	if(entry.tags.equalsIgnoreCase(propertyLoad.getProperty("TAG_NAME")))
				metaData_media.add((KalturaBaseEntry)entry);
			
			
			
			}
		} else {
			System.out.println("This account doesn't have any entries in it.");
		}
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}
	return metaData_media;
	
	}
	
	
	
	
	
	
	
	public  ArrayList retrievePlaylistMetaData()  {

		ArrayList metaData_media=new ArrayList();
		try
		{
		KalturaPlaylist list ;
		String entryId=propertyLoad.getProperty("PLAYLIST_ENTRY_ID");
		 list =  KalturaSessionGen.getClient().getPlaylistService().get(entryId);
		if (list !=null) {
			
				System.out.println("\t \"" + list.executeUrl + "\"");
			
		} else {
			System.out.println("This account doesn't have any entries in it.");
		}
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}
	return metaData_media;
	
	}
	
	public   HashMap retrieveLiveStreamMetaData()  {

		HashMap live_data=new HashMap();
		try
		{

			String entryId=propertyLoad.getProperty("LIVE_ENTRY_ID");
			KalturaLiveStreamEntry liveEntry= KalturaSessionGen.getClient().getLiveStreamService().get(entryId);
			String dataUrl=liveEntry.streamUrl;
			ArrayList a=liveEntry.operationAttributes;
			System.out.println("Stream URL::"	+liveEntry.streamUrl);
		//	ArrayList urlObj=KalturaSessionGen.getClient().getLiveStreamService().get(entryId).liveStreamConfigurations;
			ArrayList urlObj=liveEntry.liveStreamConfigurations;
			
			
			
			
			System.out.println("Stream url:"+liveEntry.toParams());
			
			KalturaOperationAttributes obj[]=new KalturaOperationAttributes[urlObj.size()];
			
			System.out.println("==> For Loop Example.");
			for (int i = 0; i < a.size(); i++) {
				 obj[i]=(KalturaOperationAttributes) a.get(i);
			
					System.out.println(	obj[i].toParams());
					
					
			}
			String partner_id="2199811";
		String URL="https://www.kaltura.com/index.php/extwidget/preview/partner_id/"+partner_id+"/uiconf_id/36961672/entry_id/"+entryId+"/embed/dynamic?&flashvars[streamerType]=auto";
			
			live_data.put("liveStreamName", liveEntry.name);
			live_data.put("liveStreamURL", URL);
			
			live_data.put("live_entryId", entryId);
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}
	return live_data;
	
	}
	
}
