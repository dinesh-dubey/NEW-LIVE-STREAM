var taggedVideo="";

jQuery(document).ready(function() {
   
   taggedVideo  = $('#tagvideo').val();
   $('#tagvideo').on('change', function() {
	   taggedVideo  = this.value;
	 });
	 $('#submit').click(function() {
	 $.ajax({
	       type: 'GET',    
	       url:'AddContextVar',
	       data:'taggedVideo='+ taggedVideo,
	       success: function(data){
	          
	    	   console.log(taggedVideo);
	          
	    	   
	           }
	   
	   });
	  });

});