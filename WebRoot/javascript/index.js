$(document).ready(function(){
	getFileList();
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

function getFileList() {
	$.ajax({
		url: "servlet/FileList",
		//dataType: "json",
		success: function(responseText) {
			var domList = $('#fileList');
			$("#fileList").html("");
			//JSON����תJavaScript����
			var json = JSON.parse(responseText);
			for(var i = 0; i < json.length; i++) {
				//��ȡ�ڵ�ģ�壬������index.jsp
				var tr = $("#fileListTrTemp").html();
				domList.append(tr);
				var index = 0;
				//ͨ�����ڵ��޸�DOM
				tr = $("#fileList").children("tr")[i];
				for(var field in json[i]) {
					$(tr).children("td")[index].innerText = json[i][field];
					index++;
				}
				
				//��������
				$(tr).find(".downloadFile").attr("href", "servlet/FileDownload?fileName=" + json[i].fileName + "." + json[i].fileType);
				$(tr).find(".deleteFile").attr("href", "servlet/FileDelete?fileId=" + json[i].fileId);
				
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
			alert(textStatus);
		}
	});
};