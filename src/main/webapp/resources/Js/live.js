var entryId;

$(document).ready(function() {
	$(".thumbnail").click(function () {
	    $(".thumbnail").removeClass("active");
	    $(this).addClass("active");        
	});
	});

function changeMainUrl(videoId, current_play_type) {
	var size = document.getElementById("xID").value;
	entryId = videoId;
	data_url_live = "http://kalturalivestream/index.php/kwidget/cache_st/1477654856/wid/_100/uiconf_id/6709457/entry_id/"
		+ entryId;
	if (size > 0) {
		jQuery("#vod_close_button").show();
	}
	$('#kaltura_player').attr('data', data_url_live);
	$("#kaltura_player param[name=movie]").attr('value', data_url_live);
	$("#player").attr('data-current_play_type', current_play_type);
	if (current_play_type == 'close' || current_play_type == 'live')
		jQuery("#vod_close_button").hide();
}

function playAdd(videoId, current_play_type, time_start) {
	entryId = videoId;
	var current = $("#player").attr("data-current_play_type");
	jQuery("#vod_close_button").hide();
	$('#kaltura_player')
	.attr(
			'data',
			"http://kalturalivestream/index.php/kwidget/cache_st/1477654856/wid/_100/uiconf_id/6709457/entry_id/"
			+ entryId);
	$("#kaltura_player param[name=movie]")
	.attr(
			'value',
			"http://kalturalivestream/index.php/kwidget/cache_st/1477654856/wid/_100/uiconf_id/6709457/entry_id/"
			+ entryId);
	$("#player").attr('data-current_play_type', current_play_type);
}
