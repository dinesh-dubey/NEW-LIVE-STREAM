var entryId;
var uiconf_id; 
var partnerid; 
$(document).ready(function() {
	$(".thumbnail").click(function () {
	    $(".thumbnail").removeClass("active");
	    $(this).addClass("active");  
	  
	});
	uiconf_id = document.getElementById("uiconf_id").value;
	partnerid = document.getElementById("partnerid").value;
});

function changeMainUrl(videoId, current_play_type) {
	if('close' == current_play_type){
	$('#liveLogo').show();
	}	
	if('vod' == current_play_type){
	$('#liveLogo').hide();
	}	
	if('live' == current_play_type){
	$('#liveLogo').show();
	}	
	var size = document.getElementById("xID").value;
	
	entryId = videoId;

	if (size > 0) {
		jQuery("#vod_close_button").show();
	}
	
	jQuery("#vod_close_button").show();
	 $("#player").html(kWidget.embed({
			"targetId" : "kaltura_player_1477484309",
			"wid" : "_"+partnerid,
			"uiconf_id" : uiconf_id,
			"flashvars" : {
				"streamerType" : "auto",
				"autoplay":true/*,
				"titleLabel": {
					'plugin' : true,
					'align' : 'left',
					'text' : '{mediaProxy.entry.name}'
				}					*/
			},
			"cache_st" : 1477484309,
			"entry_id" : entryId
		}));

	$("#player").attr('data-current_play_type', current_play_type);
	if (current_play_type == 'close' || current_play_type == 'live')
		jQuery("#vod_close_button").hide();
}

function playAdd(videoId, current_play_type, time_start) {
if('ad' == current_play_type){
	$('#liveLogo').hide();
	}
	entryId = videoId;
	var current = $("#player").attr("data-current_play_type");
	jQuery("#vod_close_button").hide();

	$("#player").html(kWidget.embed({
				"targetId" : "kaltura_player_1477484309",
				"wid" : "_"+partnerid,
				"uiconf_id" : uiconf_id,
				"flashvars" : {
					'streamerType' : 'auto',
						'autoPlay': true,
						'mediaProxy.mediaPlayFrom' : time_start/*,
						'controlBarContainer.plugin': false*/
						
				},
				"cache_st" : 1477484309,
				"entry_id" : entryId
			}));		
	$("#player").attr('data-current_play_type', current_play_type);
}
