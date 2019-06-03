
$(document).ready(function(){
		var c = 2;
    	$("#addPicture").click(function(){
    		var newDiv = $("<div class='row'>" +
    							"<div class='form-group col-md-12'>" +
    								"<label class='col-md-3' for='file'>Add picture:</label>" +
    								"<div class='col-md-7'>" +
    									"<input type='file' name='files' id='files["+ c +"]' class='form-control input-sm' />" +
    								"</div>" +
    							"</div>" +
    						"</div>");
    		c++;
        	$("#newAddPicture").before(newDiv);
    	});
});