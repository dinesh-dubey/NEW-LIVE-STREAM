<!DOCTYPE html>
<html>
<title>Tavant Engage</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<head>
<jsp:include page="/Live" />

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/lightslider.css">

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/style.css">

<link href="http://fonts.googleapis.com/css?family=Raleway"
	rel="stylesheet" type="text/css">
<script
	src="<%=request.getContextPath()%>/resources/libs/jquery.min.v3.1.1.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/libs/lightslider.js"></script>
<!--<script type="text/javascript" src="http://html5.kaltura.org/js"></script>-->



<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="live_entryId" value='${requestScope["live_entryId"]}' />
<c:set var="partner_id" value='${requestScope["partner_id"]}' />

<c:set var="script_source" value='${requestScope["script_source"]}' />
<c:set var="ui_conf_id" value='${requestScope["ui_conf_id"]}' />
<c:set var="player_id" value='${requestScope["player_id"]}' />
<script	src='${script_source}/p/${partner_id}/sp/${partner_id}00/embedIframeJs/uiconf_id/${ui_conf_id}/partner_id/${partner_id}'></script>
<script src="<%=request.getContextPath()%>/resources/Js/custom.js"></script>
<script src="<%=request.getContextPath()%>/resources/Js/live.js"></script>
<script src="<%=request.getContextPath()%>/resources/Js/vod.js"></script>
	<script id="vodTemplate" type="text/template">  
				<li><a href="javascript:void(0)" class="thumbnail"
					onclick="changeMainUrl('{{media_entryId}}', 'vod')"><img
						alt="Preview Image 1" src="{{media_entry_thumbnail}}"
						data-image="{{media_entry_thumbnail}}"
						data-description="{{media_entry_name}}">
							<small></small>
							 </a>
					<p>{{media_entry_name}}</p>
				</li>
	</script>
<script src="//assets.adobedtm.com/d8777c4d9cbebfbdad3723de5a9e9b30c24ad5fe/satelliteLib-fb9bc886fa23292d2d7ede842c6d56507a62d6b7-staging.js"></script>
</head>
<body class="body">
	<header class="header overhid">
		<span class="fl pad10"><img
			src="<%=request.getContextPath()%>/resources/images/tavant_logo.png"
			height="30px" /></span> <span class="fr pad10"><img
			src="<%=request.getContextPath()%>/resources/images/tech_connect_logo.png"
			height="35px" /></span>
	</header>
	<div class="content-container">
		<div class="tealive">
			<div class="fr">
				<span id="liveLogo" class="pad10 fl livetext"><span class="redDot"></span>Live</span>
				<img class="pad10 fl"	src="<%=request.getContextPath()%>/resources/images/engage_logo.png" />
			</div>
		</div>

	</div>
	<span class="clear"></span>
	<c:set var="live_entryId" value='${requestScope["live_entryId"]}' />
	<c:set var="partnerId" value='${requestScope["partner_id"]}' />

	<c:set var="live_hashmap" value='${requestScope["hashMap_live"]}' />


	<div class="video-player-test clear" id="player">
		<div class="close-outer">
			<button id="vod_close_button" class="closeBtn"
				onclick="changeMainUrl('${live_hashmap['live_entryId']}','close')"></button>
		</div>

		<script> jQuery("#vod_close_button").hide();</script>
		<div id="kaltura_player_1477484309" class="player-style"
			style="height: 610px;"></div>
		<script>	
				kWidget.embed({
					"targetId" : "kaltura_player_1477484309",
					"wid" : "_${partner_id}",
					"uiconf_id" : "${ui_conf_id}",
					"flashvars" : {
						"streamerType" : "auto",
						"autoplay":true,
						"titleLabel": {
							'plugin' : true,
							'align' : 'left',
							'text' : '{mediaProxy.entry.name}'
						}					
					},
					"cache_st" : 1478856194,
					"entry_id" :"${live_hashmap['live_entryId']}"
				});
			</script>
	</div>
	<div class="item" id="refresh-after-ajax">
		<ul id="responsive" class="content-slider">
			<c:forEach items="${list_vod}" var="vod_obj">
				<li>
				<a href="javascript:void(0)" class="thumbnail"
					onclick="changeMainUrl('${vod_obj['media_entryId']}', 'vod')"
					data-videoName="${vod_obj['media_entry_name']}"><img
						alt="${vod_obj['media_entry_name']}" src="${vod_obj['media_entry_thumbnail']} "
						data-image="${vod_obj['media_entry_thumbnail']} "
						data-description="${vod_obj['media_entry_name']} ">
							<small></small>
							 </a>
					<p>${vod_obj['media_entry_name']}</p>
					</li>
			</c:forEach>
		</ul>
	</div>
	<div>
	</div>

	<script>
		$(document).ready(function() {	
			$('#responsive').lightSlider({
				item : 10,
				loop : false,
				slideMove : 2,
				slideMargin : 20,
				easing : 'cubic-bezier(0.25, 0, 0.25, 1)',
				speed : 600,
				responsive : [ {
					breakpoint : 1024,
					settings : {
						item : 7,
						slideMove : 1,
						slideMargin : 6,
					}
				}, {
					breakpoint : 840,
					settings : {
						item : 6,
						slideMove : 1
					}
				}, 
				{
					breakpoint : 720,
					settings : {
						item : 5,
						slideMove : 1,
						slideMargin : 10,
					}
				}, 
				{
					breakpoint : 640,
					settings : {
						item : 4,
						slideMove : 1,
						slideMargin : 10,
					}
				},
				{
					breakpoint : 520,
					settings : {
						item : 3,
						slideMove : 1,
						slideMargin : 10,
					}
				},
				{
					breakpoint : 480,
					settings : {
						item : 3,
						slideMove : 1,
						slideMargin : 8,
					}
				},
				{
					breakpoint : 360,
					settings : {
						item : 2,
						slideMove : 1,
						slideMargin : 5,
					}
				}]
			});
			var winWid = $(window).width();
			if (winWid < 375){
				$("#kaltura_player").attr("height");
				$("#kaltura_player").attr("height", 330);
			} else if (winWid<410 && winWid>375){
				$("#kaltura_player").attr("height");
				$("#kaltura_player").attr("height", 420);
			}else if (winWid<640 && winWid>410){
				$("#kaltura_player").attr("height");
				$("#kaltura_player").attr("height", 500);
			}else if (winWid<800 && winWid>640){
				$("#kaltura_player").attr("height");
				$("#kaltura_player").attr("height", 720);
			}
			
		});
	</script>
	<input type="hidden" id="xID" name="x" value="${livestreamflag}">
	<script type="text/javascript">_satellite.pageBottom();</script>
	<input type="hidden" id="uiconf_id" name="x" value="${ui_conf_id}">
	<input type="hidden" id="partnerid" name="x" value="${partner_id}">
</body>
</html>
