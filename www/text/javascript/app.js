var API_BASE_URL = "http://localhost:8080/Bumaye-api";
var mercadoApp = angular.module('mercadoApp',['ngCookies']);

mercadoApp.controller('TabController', function(){
	this.tab = 1;

	this.setTab = function(newValue){
		this.tab = newValue;
	};

	this.isSet = function(tabName){
		return this.tab === tabName;
	};
});

mercadoApp.controller('MercadoCtrl', function($cookieStore, $scope, $http, $window){
	var urlMercado= API_BASE_URL + '/mercado/entradas';
	var urlInventarioUser= API_BASE_URL + '/user/listaobjetos/';
	var urlComprarMercado= API_BASE_URL + '/mercado/';
	var urlVenderMercado= API_BASE_URL + '/mercado/vender';
	var urlLoginMercado= API_BASE_URL + '/user';
	this.mercadoPers = $cookieStore.get("personaje");
	
	$scope.loadMercado = function(){
		$http.get(urlMercado).success(function(mercadoList){
			console.log(mercadoList);
			$scope.mercado_list = mercadoList;
		})	

	};
	$scope.loadInventarioUser = function(){

		var idUser = $cookieStore.get("idUser");

		$http.get(urlInventarioUser + idUser).success(function(inventarioList){
			console.log(inventarioList);
			$scope.inventario_list = inventarioList;
		})	

	};
	$scope.compraMercado = function(identrada){

		var idUser = $cookieStore.get("idUser");

		$http.get(urlComprarMercado + idUser +'/comprar/'+ identrada +'/cantidad/' + $scope.compraCantidad).success(function(data){
			$cookieStore.put("personaje", data);
			this.mercadoPers = $cookieStore.get("personaje");
			console.log(data);
			$window.location.reload();

		})	
	};
	
	$scope.venderMercado = function(){
		var config = {
				headers :{
					"Content-Type": "application/vnd.bumaye.api.mercado+json"
				}	
		};
		var data = {
				cantidad: $scope.venderCantidad,
				precioUnidad:$scope.venderPrecio,
				idpersonaje: $cookieStore.get("idUser"),
				idobjeto:$scope.venderIdObjeto
		};	
		$http.post(urlVenderMercado, data, config).success(function(data, status, headers, config){
		})	
//		$window.location.reload();
	};
	$scope.loginMercado = function(){
		var config = {
				headers :{
					"Content-Type": "application/vnd.bumaye.api.user+json"
				}	
		};
		var data = {
				pass: $scope.password,
				username:$scope.username
		};	
		$http.post(urlLoginMercado, data, config).success(function(responsedata, status, headers, config){
			$scope.personaje = responsedata;
			var personaje = responsedata;
			$cookieStore.put("personaje", responsedata);
			this.mercadoPers = $cookieStore.get("personaje");
			$cookieStore.put("idUser", responsedata.iduser);
			console.log($cookieStore.get("idUser"));

		    $window.location.replace("/auth.html");
		})	
	      
	};
	
	$scope.logOutMercado = function(){
	    $window.location.replace("/index.html");

		
	};
	
});