"use strict";

//var MIME = 'application/json;charset=UTF-8';
var MIME = '';

function getUsers(callback) {
	$.get('user/', callback, MIME);
}

function getUser(userName, callback) {
	$.get('user/' + username, callback, MIME);
}

function postUser(userName, email, password, callback) {
	var user = {
		userName : userName,
		email : email,
		password : password
	}
	user = JSON.stringify(user);
	console.log(user);
	$.post('user/', user, callback, MIME);
}

$(function() {
	$(document).ajaxError(function(event, jqXHR, ajaxSettings, thrownError) {
		// $( ".log" ).text( "Triggered ajaxError handler." );
		//alert(" ERROR \n" + JSON.stringify(data, null, '    '));
		alert(thrownError + "\n\n" + JSON.stringify(jqXHR, null, '    '));
	});
});