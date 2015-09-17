var JSON = 'application/json'

function getUsers(callback){
	$.get('user/', callback, JSON);
}

function getUser(userName, callback) {
	$.get('user/' + username, callback, JSON);
}

function postUser(userName, email, password, callback) {
	var user = {
		userName: userName,
		email: email,
		password: password
	}
	user = JSON.stringify(user);
	console.log(user);
	$.post('user/', user, callback, JSON);
}
/*
$(function(){
	console.log('running...');
	getUsers(function(data){
		console.log('test');
		$("#detail-1").text("test");
	});
});
*/

/*
$(function(){
	console.log('running...');
	$.get('/login.html', function(data){
		$("#content").html(data);
	});
});
*/