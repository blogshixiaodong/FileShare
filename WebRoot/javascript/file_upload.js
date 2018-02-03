/**
 * 
 */
$(document).ready(function(){
    $(".dropdown").hover(            
        function() {
            $('.dropdown-menu', this).not('.in .dropdown-menu').stop(true,true).slideDown("400");
            $(this).toggleClass('open');        
        },
        function() {
            $('.dropdown-menu', this).not('.in .dropdown-menu').stop(true,true).slideUp("400");
            $(this).toggleClass('open');       
        }
    );
});

function uploadFileCheck() {
	if("" == $(".upload").find("input").val()) {
		alert("请选择上传文件");
		return false;
	}
	return true;
}

