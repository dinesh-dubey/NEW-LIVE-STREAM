package com.liveStream;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.kaltura.client.KalturaApiException;
import com.kaltura.client.types.KalturaBaseEntry;
import com.kaltura.client.types.KalturaLiveStreamEntry;
import com.kaltura.client.types.KalturaMediaEntry;
import com.kaltura.client.types.KalturaMediaListResponse;
import com.kaltura.client.types.KalturaOperationAttributes;
import com.kaltura.client.types.KalturaPlaylist;

public class KalturaUtil {

	private final static Logger LOGGER = Logger.getLogger(KalturaUtil.class.getName());
	public static LoadProperty propertyLoad = null;
	public KalturaUtil()
	{
		if (propertyLoad == null)
			propertyLoad = new LoadProperty();
	}

	public KalturaMediaListResponse retrieveMediaList()
			throws KalturaApiException {
		KalturaMediaListResponse list = null;
		try {
			new KalturaSessionGen();
			list = KalturaSessionGen.getClient().getMediaService().list();
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return list;
	}

	public ArrayList retrieveTaggedMediaMetaData() {

		ArrayList<KalturaBaseEntry> metaData_media = new ArrayList();
		try {
			KalturaMediaListResponse list = retrieveMediaList();
			if (list.totalCount > 0) {
				LOGGER.info("The account contains " + list.totalCount
						+ " entries.");
				for (KalturaMediaEntry entry : list.objects) {
					if (null == entry.tags)
						metaData_media.add((KalturaBaseEntry) entry);
				}
			} else {
				LOGGER.info("This account doesn't have any entries in it.");
			}
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return metaData_media;

	}

	public ArrayList retrieveMediaMetaData() {
		ArrayList<KalturaBaseEntry> metaData_media = new ArrayList();
		try {
			KalturaMediaListResponse list = retrieveMediaList();

			if (list.totalCount > 0) {
				LOGGER.info("The account contains " + list.totalCount
						+ " entries.");
				for (KalturaMediaEntry entry : list.objects) {
					metaData_media.add((KalturaBaseEntry) entry);
				}
			} else {
				LOGGER.info("This account doesn't have any entries in it.");
			}
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return metaData_media;
	}

	public ArrayList retrievePlaylistMetaData() {

		ArrayList metaData_media = new ArrayList();
		try {
			KalturaPlaylist list;
			String entryId = propertyLoad.getProperty("PLAYLIST_ENTRY_ID");
			list = KalturaSessionGen.getClient().getPlaylistService()
					.get(entryId);
			if (list != null) {
				LOGGER.info("\t \"" + list.executeUrl + "\"");
			} else {
				LOGGER.info("This account doesn't have any entries in it.");
			}
		} catch (Exception e) {
			LOGGER.error(e);

		}
		return metaData_media;
	}

	public HashMap retrieveLiveStreamMetaData() {
		HashMap live_data = new HashMap();
		try {
			String entryId = propertyLoad.getProperty("LIVE_ENTRY_ID");
			KalturaLiveStreamEntry liveEntry = KalturaSessionGen.getClient()
					.getLiveStreamService().get(entryId);
			String dataUrl = liveEntry.streamUrl;
			ArrayList a = liveEntry.operationAttributes;
			System.out.println("Stream URL::" + liveEntry.streamUrl);
			// ArrayList
			// urlObj=KalturaSessionGen.getClient().getLiveStreamService().get(entryId).liveStreamConfigurations;
			ArrayList urlObj = liveEntry.liveStreamConfigurations;
			LOGGER.info("Stream url:" + liveEntry.toParams());
			KalturaOperationAttributes obj[] = new KalturaOperationAttributes[urlObj.size()];
			for (int i = 0; i < a.size(); i++) {
				obj[i] = (KalturaOperationAttributes)a.get(i);
			}
			String partner_id = "2199811";
			String URL = "https://www.kaltura.com/index.php/extwidget/preview/partner_id/"
					+ partner_id
					+ "/uiconf_id/36961672/entry_id/"
					+ entryId
					+ "/embed/dynamic?&flashvars[streamerType]=auto";

			live_data.put("liveStreamName", liveEntry.name);
			live_data.put("liveStreamURL", URL);
			live_data.put("live_entryId", entryId);

		} catch (Exception e) {
			LOGGER.error(e);
		}
		return live_data;
	}

}
