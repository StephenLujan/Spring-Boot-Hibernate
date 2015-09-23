"use strict";

var MIME = 'application/json;charset=UTF-8';
//var MIME = '';
var USER_ENDPOINT = 'api/user/';

function getUsers(callback) {
	$.get(USER_ENDPOINT, callback, '');
}

function getUser(userName, callback) {
	$.get(USER_ENDPOINT + username, callback, '');
}

function postUser(userName, email, password, callback) {
	var user = {
		userName : userName,
		email : email,
		password : password
	}
	user = JSON.stringify(user);
	console.log(user);
	$.post(USER_ENDPOINT, user, callback, MIME);
}

function put(url, json, callback) {
	$.ajax({
		  url: url,
		  method: "PUT",
		  data: json,
		  contentType: MIME,
		  success: callback
		});
}

function post(url, json, callback)
{
	$.ajax({
		  url: url,
		  method: "POST",
		  data: json,
		  contentType: MIME,
		  success: callback
		});
}

$(function() {
	$(document).ajaxError(function(event, jqXHR, ajaxSettings, thrownError) {
		// $( ".log" ).text( "Triggered ajaxError handler." );
		// alert(" ERROR \n" + JSON.stringify(data, null, ' '));
		alert(thrownError + "\n\n" + JSON.stringify(jqXHR, null, '    '));
	});
});