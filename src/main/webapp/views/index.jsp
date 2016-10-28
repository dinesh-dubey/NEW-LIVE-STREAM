<!DOCTYPE html>
<html>
<title>Tavant Engage</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<head>
<!-- <script type="text/javascript" src="http://kalturalivestream/html5/html5lib/v1.5.13/tests/qunit/qunit-bootstrap.js"></script>
<script type="text/javascript" src="http://kalturalivestream/html5/html5lib/v1.5.13/mwEmbedLoader.php?debug=true"></script>
<script type="text/javascript" src="http://kalturalivestream/html5/html5lib/v1.5.13/modules/KalturaSupport/tests/resources/qunit-kaltura-bootstrap.js"></script> -->
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



<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="live_entryId" value='${requestScope["live_entryId"]}' />
<c:set var="partner_id" value='${requestScope["partner_id"]}' />

<c:set var="script_source" value='${requestScope["script_source"]}' />
<c:set var="ui_conf_id" value='${requestScope["ui_conf_id"]}' />


<c:set var="player_id" value='${requestScope["player_id"]}' />

<script src="https://cdnapisec.kaltura.com/p/2199811/sp/219981100/embedIframeJs/uiconf_id/36961672/partner_id/2199811"></script>
<%-- <script
	src='${script_source}/index.php/kwidget/cache_st/${cache_st}/wid/_${partner_id}/uiconf_id/${ui_conf_id}/entry_id/${entry_id}'> 



			
			</script>--%>
<!-- 
			
<script
	src="https://cdnapisec.kaltura.com/p/2199811/sp/219981100/embedIframeJs/uiconf_id/36776161/partner_id/2199811"></script> -->

<script src="<%=request.getContextPath()%>/resources/Js/custom.js"></script>

<script src="<%=request.getContextPath()%>/resources/Js/live.js"></script>
<script src="<%=request.getContextPath()%>/resources/Js/vod.js"></script>
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
				<img class="pad10 fl"
					src="<%=request.getContextPath()%>/resources/images/engage_logo.png" />
				<span class="pad10 fl livetext"><span class="redDot"></span>Live</span>
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
		<div id="kaltura_player_1477484934" class="player-style"
			style="height: 610px;">
			<iframe src="http://kalturalivestream/index.php/kwidget/cache_st/1477654856/wid/_100/uiconf_id/6709457/entry_id/0_rz76fe6t" height="610px" width="100%" id="live"></iframe>	
		</div>





		<!-- <script>	
				kWidget.embed({
					"targetId" : "kaltura_player_1477484934",
					"wid" : "_2199811",
					"uiconf_id" : 36776161,
					"flashvars" : {
						"streamerType" : "auto",
						"autoplay":true
												
					},
					"cache_st" : 1477484934,
					"entry_id" :"${live_hashmap['live_entryId']}"
				});
			</script> -->
	</div>
	<div class="item" id="refresh-after-ajax">

		<ul id="responsive" class="content-slider">
			<c:forEach items="${list_vod}" var="vod_obj">

				<li><a href="javascript:void(0)"
					onclick="changeMainUrl('${vod_obj['media_entryId']}', 'vod')"><img
						alt="Preview Image 1" src="${vod_obj['media_entry_thumbnail']} "
						data-image="${vod_obj['media_entry_thumbnail']} "
						data-description="${vod_obj['media_entry_name']} "> </a>
					<p>${vod_obj['media_entry_name']}</p></li>


			</c:forEach>

		</ul>



	</div>








	<div></div>



	<script>
		$(document).ready(function() {
			
			
				
			$('#responsive').lightSlider({
				item : 7,
				loop : false,
				slideMove : 2,
				slideMargin : 30,
				easing : 'cubic-bezier(0.25, 0, 0.25, 1)',
				speed : 600,
				responsive : [ {
					breakpoint : 1024,
					settings : {
						item : 6,
						slideMove : 1,
						slideMargin : 6,
					}
				}, {
					breakpoint : 840,
					settings : {
						item : 4,
						slideMove : 1
					}
				}, {
					breakpoint : 640,
					settings : {
						item : 3,
						slideMove : 1
					}
				},
				{
					breakpoint : 480,
					settings : {
						item : 2,
						slideMove : 1
					}
				},
				{
					breakpoint : 320,
					settings : {
						item : 1,
						slideMove : 1
					}
				}]
			});
		});
	</script>

</body>
</html>
