
var entryId;

$(document).ready(function() {
	$(".thumbnail").click(function () {
	    $(".thumbnail").removeClass("active");
	    $(this).addClass("active");        
	});
	});
function changeMainUrl( xyz, current_play_type)
{
	
	
	
	
	 entryId=xyz;
	 data_url_live="http://kalturalivestream/index.php/kwidget/cache_st/1477654856/wid/_100/uiconf_id/6709457/entry_id/"+entryId;
	 
    

	 jQuery("#vod_close_button").show();
	 
	 
	 
	 $('#kaltura_player').attr('data',data_url_live); 
	 $("#kaltura_player param[name=movie]").attr('value', data_url_live);
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
	// $('#live').attr('src',"http://kalturalivestream/index.php/kwidget/cache_st/1477654856/wid/_100/uiconf_id/6709457/entry_id/"+entryId); 
		 $('#kaltura_player').attr('data',"http://kalturalivestream/index.php/kwidget/cache_st/1477654856/wid/_100/uiconf_id/6709457/entry_id/"+entryId); 
		 $("#kaltura_player param[name=movie]").attr('value', "http://kalturalivestream/index.php/kwidget/cache_st/1477654856/wid/_100/uiconf_id/6709457/entry_id/"+entryId);
		 $("#player").attr('data-current_play_type',current_play_type);
	 
}


