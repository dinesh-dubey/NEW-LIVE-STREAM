jQuery(document).ready(function() {
   setInterval(function(){
	   getVod();
	   },900000);
	//half an hour
});
function getVod(){
	$.ajax({
	      type:"get",
	      url:"/livestream/Live",
	      datatype:"json",
	      success:function(response) {
			 var json = JSON.parse(response);
	    	 var data = json.vodJson;
			 if(data != undefined){
			  var contentSlider = "";
			  var silderContent='<ul id="responsive" class="content-slider">';
			  	for(var i=0 ; i<data.length; i++){
	    			var item = data[i];
	    			var vodVideos = $('#vodTemplate').html()
	    			.replace(/{{media_entryId}}/g,item.media_entryId)
	    			.replace(/{{media_entry_thumbnail}}/g,item.media_entry_thumbnail)
	    			.replace(/{{media_entry_name}}/g,item.media_entry_name);
					contentSlider = contentSlider + vodVideos;
	    		}
				silderContent = silderContent+contentSlider+'</ul>';
				$('#refresh-after-ajax').html('');
				$('#refresh-after-ajax').html(silderContent);
				buildSlider();
	    	  $(".thumbnail").click(function () {
			$(".thumbnail").removeClass("active");
			$(this).addClass("active");  
			});
			 }
	      }
	    });
	}
	function buildSlider(){
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
		}
	