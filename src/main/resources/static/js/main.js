function detail1(){
	console.log("detail1()");
	getUsers(function (data) {
		console.log(data);
		$("#detail-1-data").text(data);
	});
}

function detail2() {
	console.log("detail2()");
	username = $("#detail-2-username").val();
	query = "/user/"+username;
	$("#detail-2-query").text(query);
	getUser(function (data) {
		console.log(data);
		$("#detail-2-data").text(data);
	});
}

$(function() {
	$("#detail-1-data").text("loading...");
	$("#detail-2-data").text("loading...");
	
	$("#detail-1-button").click(detail1);
	$("#detail-2-button").click(detail2);
	
	detail1();
	detail2();
});