function myFormat(data) {
	var string = JSON.stringify(data, null, "    ");
	return string.replace(new RegExp(' ', 'g'), '\xa0')
				 .replace(new RegExp('\\n', 'g'), '<br/>');
}

function detail1() {
	console.log("detail1()");
	getUsers(function(data) {
		console.log(data);
		$("#detail-1-data").html(myFormat(data));
	});
}

function detail2() {
	console.log("detail2()");
	username = $("#detail-2-username").val();
	query = "/user/" + username;
	$("#detail-2-query").text(query);
	getUser(username, function(data) {
		console.log(data);
		$("#detail-2-data").html(myFormat(data));
	});
}

//function detail3-get-user(){
//	var user = {
//		userName : $('#detail-3-username').val(),
//		email : $('#detail-3-email').val(),
//		password : $('#detail-3-email').val(),
//	}
//}

function detail3put(){
	var query = $('#detail-3-query').val();
	var data = $('#detail-3-data').val();
	
	put(query, data);
}

function detail3post(){
	var query = $('#detail-3-query').val();
	var data = $('#detail-3-data').val();
	
	post(query, data);
}

$(function() {
	$("#detail-1-data").text("Click below...");
	$("#detail-2-data").text("Click below...");

	$("#detail-1-button").click(detail1);
	$("#detail-2-button").click(detail2);

	$("#detail-3-put").click(detail3put);
	$("#detail-3-post").click(detail3post);
});