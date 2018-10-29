/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var app = angular.module('loginNirhoModule', []);

 app.controller('loginNirhoController', ['$scope', '$http', '$window', function($scope, $http, $window) {         
             
   $scope.data = {
	   usuario: {
		   username: null,
	       password: null
	   }
   };
   
   $scope.ingresar = function(){
	   console.log('usuario ['+$scope.data.usuario.username+"]");
	   var info = $scope.data.usuario;
	   $http({
			url : "./usuario/login",
			dataType : "json",
			method : "POST",
			data : info,
			headers : {
				"Content-Type" : "application/json"
			}
		}).then(function successCallback(response) {
			console.log('response ['+response+"]");
			$http({
		        method: 'get',
		        url: './usuario/getUsuarioLogueado',
		        }).then(function (resultado) {
		            console.log(resultado.data);
		            $scope.usuarioLogueado = resultado.data;
		            console.log('usuario logueado' + $scope.usuarioLogueado);  
		     	   var idRol = 0;
		     	   var redirectTo = './loginNirho.html';
		     	   if($scope.usuarioLogueado != null){
		     		   idRol = $scope.usuarioLogueado.rol;
		     	   }
		     	   switch(idRol){
		     		   case 1:
		     			   redirectTo = './climaLaboral.html';
		     			   break;
		     		   case 2://gerente
		     			   redirectTo = './climaLaboral.html';
		     			   break;
		     		   case 3://cliente
		     			   redirectTo = './empresasc.html';
		     			   break;
		     	   }
		     	   $window.location = redirectTo;
		        }
		   );
			
//			$window.location = '';
		}, function errorCallback(response) {
			console.log('response ['+response+"]");
			alert('*** Usuario inválido ***');
		});
   }
   
}]);
