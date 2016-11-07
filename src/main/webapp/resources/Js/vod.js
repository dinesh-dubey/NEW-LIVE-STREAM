jQuery(document).ready(function() {
  
   /*jQuery("#gallery").unitegallery({
       gallery_width:"100%"
   });*/
  // invokeTavantAds('1_t2ssbfzu');
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
	    	
	   // 	  $("#refresh-after-ajax").load(window.location + "#refresh-after-ajax");
	     //	  $("#refresh-after-ajax").load();
	    	  
	    	  $("#refresh-after-ajax").html($("refresh-after-ajax").html());
	    	// $("#refresh-after-ajax").empty().load(window.location.href + ".#refresh-after-ajax");
	    	//  $("#refresh-after-ajax").innerHTML="";
	    	//  $( "div.third" ).replaceWith( $( ".first" ) );
	    	  
	    		//  $("#refresh-after-ajax").replaceWith ($("#refresh-after-ajax"));

	    	  console.log("vodlist");
	    	  
	      }
	    });
	   
	
}