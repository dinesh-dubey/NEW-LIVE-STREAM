jQuery(document).ready(function() {
  
   
   setInterval(function(){
	   getVod();
	},properties.time_ajax_vod);
   
});

function getVod(){
	$.ajax({
	      type:"get",
	      url:"/livestream/Live",
	      datatype:"json",
	      success:function(response) {
	    	  location.reload();
	    	  console.log("vodlist");
	      }
	    });
	   
	
}