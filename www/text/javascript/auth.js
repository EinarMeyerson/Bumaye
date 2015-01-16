var API_BASE_URL = "http://localhost:8080/Bumaye-api";//cambiar spot-api por spot-auth
var API_BASE = "http://localhost:8080";
var password;
var username;
$("#button-auth").click(function(e) {
	e.preventDefault();

	if ($('#nombre-auth').val() == "" || $('#password-auth').val() == "") {
		$("#alerta-auth-div-login").show();
		$("#error-auth-div").hide();
	} else {
		var user = new Object();
		user.username = $('#nombre-auth').val();
		user.userpass = $('#password-auth').val();
		password = $('#password-auth').val();
		login(JSON.stringify(user));
	}
});

function login(user){
	var url = API_BASE_URL + '/user';
	$.ajax({
		url : url,
		type : 'POST',
		crossDomain : true,
		contentType: 'application/vnd.bumaye.api.user+json',
		data: user
		}).done(function (data, status, jqxhr) {
			var usuario = new User(data);
			if(password == usuario.userpass){
			   //guardamos el valor del nombre del usuario en la cookie
				var nombreusuario = usuario.username;
			  $.cookie('username', nombreusuario);
		      var currentusr = $.cookie('username');
		      window.location.replace("/index.html");
			}
			else{
				    $("#alerta-auth-div-login").hide();
				    $("#error-auth-div").show();
			}
	})
    .fail(function (jqXHR, textStatus) {
		console.log(textStatus);
	});
}

