
var entryId;
var data_url_live;
function changeMainUrl( xyz, current_play_type)
{
	
	
	
	
	 entryId=xyz;
	 data_url_live="http://kalturalivestream/index.php/kwidget/cache_st/1477654856/wid/_100/uiconf_id/6709457/entry_id/"+entryId;
	 
    

	 jQuery("#vod_close_button").show();
	 /*$("#player").html(kWidget.embed({
			"targetId" : "kaltura_player_1477484934",
			"wid" : "_2199811",
			"uiconf_id" : 36776161,
			"flashvars" : {
				"streamerType" : "auto",
					"autoPlay": true
								},
			"cache_st" : 1477484934,
			"entry_id" : entryId
		}));*/
	 
	 $('#live').attr('src',data_url_live); 
	 $("#player").attr('data-current_play_type',current_play_type);
	 
	 
	
 if(current_play_type=='close' || current_play_type=='live')
	 jQuery("#vod_close_button").hide();
	
 
 
 
 

	  console.log(' current_play_type'+current_play_type)	;
	 
	 
}


function playAdd( xyz, current_play_type, time_start)
{
	
	
	
	
	 entryId=xyz;
	 
	 
	
	var current= $("#player").attr("data-current_play_type");

	 
	 console.log("current_play_type"+current);
	 
	 
		 jQuery("#vod_close_button").hide();
		 /*$("#player").html(kWidget.embed({
				"targetId" : "kaltura_player_1477643189",
				"wid" : "_100",
				"uiconf_id" : 6709423,
				"flashvars" : {
					'streamerType' : 'auto',
						'autoPlay': true,
						'mediaProxy.mediaPlayFrom' : time_start,
						
						'externalInterfaceDisabled' : false 
				},
				"cache_st" : 1477643189,
				"entry_id" : entryId
			}));	*/
	 $('#live').attr('src',"http://kalturalivestream/index.php/kwidget/cache_st/1477654856/wid/_100/uiconf_id/6709457/entry_id/"+entryId); 
	 $("#player").attr('data-current_play_type',current_play_type);
	 
}


