jQuery(document).ready(function() {
   setInterval(function(){
	   getVod();
	},10000);
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
	    		for(var i=0 ; i<data.length; i++){
	    			var item = data[i];
	    			var vodVideos = $('#vodTemplate').html()
	    			.replace(/{{media_entryId}}/g,item.media_entryId)
	    			.replace(/{{media_entry_thumbnail}}/g,item.media_entry_thumbnail)
	    			.replace(/{{media_entry_name}}/g,item.media_entry_name);
					contentSlider = contentSlider + vodVideos;
	    		}
				$('.content-slider').html(contentSlider); 
				console.log("vodlist");
	    	  $(".thumbnail").click(function () {
			$(".thumbnail").removeClass("active");
			$(this).addClass("active");  
			
	});
			 }
			
	      }
	    });
	   
	
}