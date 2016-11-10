jQuery(document).ready(function() {
  
   /*jQuery("#gallery").unitegallery({
       gallery_width:"100%"
   });*/
  // invokeTavantAds('1_t2ssbfzu');
   setInterval(function(){
	   invokeTavantAds();
	},5000);
   
});

var entryId = "";
var timeOutVar="";

function invokeTavantAds(){
	
	$.ajax({
	      type:"get",
	      url:"/livestream/FetchKalturaEntry",
	      datatype:"json",
	      success:function(response) {
	    	  var kalturaEntryId = response.entryId;
	    	  var live_entry_id=response.live_entry_id;
	    	  var time=response.time;
	    	  var time_start=response.timediff;
		    	
	    	  console.log(entryId);
	    	  console.log("time_start"+time_start);
	    	  if(kalturaEntryId !='' && kalturaEntryId!=entryId ){
	    		  clearTimeout(timeOutVar);
	    		  console.log(kalturaEntryId);
	    		  entryId = kalturaEntryId;
	    		  console.log(time);
	    		  var delay_for_live=time-time_start;
				  console.log("delay_for_live"+delay_for_live);
	    		  var current= $("#player").attr("data-current_play_type");
	    		  console.log('  current_play_type'+current)	;
				   if (current != 'vod') {
					playAdd(kalturaEntryId, 'ad', time_start);
					timeOutVar = setTimeout(function() {
						changeMainUrl(live_entry_id, 'live');
					}, delay_for_live * 1000);

	    			 
	    			 }
	    		  
	    		  
	    		  
	    	
	    	  }
	    	  
	    	  
	    	  
	    	  
	      }
	    });
	   
	   
	   
	    	  
	
}