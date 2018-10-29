/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
angular.module('empresasGerenteModule', ['ngTable'])
 .controller('empresasGerenteController', ['$scope', '$http', '$window', 'NgTableParams', 
                                           function($scope, $http, $window, NgTableParams) { 

	 
	 $scope.data = {
		       bandeja: {
		           idProyectoSel: null,
		           idEmpresaSel: null
		       },
			   opciones:{
		    	   bandejaShow: false,
		    	   empresaShow: false,
		    	   empresasShow: true,
		    	   contactoShow: false,
		    	   proyectoShow: false,
		    	   periodosShow: false,
		    	   consultarShow: false,
		    	   headCountShow: false,
		       },
		       usuario: null,
		       proyecto: null,
		       empresa: null,
		       proyectoList: null,
		       fechaRegistro: null,
		       fechaFin: null,
		   };

//	 var data = $scope.data.empresasList;
	 
	 $http({
	       method: 'get',
	       url: './empresa/solicitadoEvaluacion',
	       }).then(function (resultado) {
	           console.log(resultado.data);
	           $scope.data.empresasList = resultado.data;
	       }
	  );
	 $scope.tableParams = new NgTableParams({}, { dataset: $scope.data.empresasList});
	 
	 
	 function getDataEmpresas(){
		 var datos = null;
		 $http({
		       method: 'get',
		       url: './empresa/solicitadoEvaluacion',
		       }).then(function (resultado) {
		           console.log(resultado.data);
		           $scope.data.empresasList = resultado.data;
		       }
		  );
		 return datos;
	 }
	 
	 
   $http({
        method: 'get',
        url: './usuario/getUsuarioLogueado',
        }).then(function (resultado) {
            console.log(resultado.data);
            if(resultado.data != ''){
            	if(resultado.data.rol != 2){
              	   $window.location = './climaLaboral.html';            		
            	} else {
                   $scope.usuarioLogueado = resultado.data;
              	   $scope.data.usuario = resultado.data;
              	   console.log($scope.data.usuario.fullName);            		
            	}
            } else {
         	   $window.location = './loginNirho.html';
            }
        }
   );
   
   
   $scope.mostrarEmpresas = function () {
       console.log('En mostrarEmpresas');
	   var mostrarEmpresas = false;
	   $scope.empresaShow = true; 	
	   if($scope.me!= null &&  $scope.me == 1){
		   mostrarEmpresas = true;
		   $scope.empresaShow = false; 	
	   }
	   $scope.data.opciones.empresasShow = mostrarEmpresas;	
	   console.log('mostrarEmpresas ' + mostrarEmpresas);
	   console.log('$scope.empresaShow ' + $scope.empresaShow);
	   return mostrarEmpresas;
   };
   
   $scope.datosEmpresa = function(){
	   console.log('idProyecto ['+$scope.data.bandeja.idProyectoSel+"]"); 
//	   cargaCatPaises();
//	   cargaCatGirosEmpresas();
	   $http({
	       method: 'get',
	       url: './catalogos/paises',
	       }).then(function (resultado) {
	           console.log(resultado.data);
	           $scope.data.catPaises = resultado.data;
	       }
	  );
	   
	   $http({
	       method: 'get',
	       url: './catalogos/girosEmpresas',
	       }).then(function (resultado) {
	           console.log(resultado.data);
	           $scope.data.catGirosEmpresas = resultado.data;
	       }
	  );
	   
	   if(Number.isInteger($scope.data.bandeja.idProyectoSel)){
			$scope.data.opciones.bandejaShow = false;
			$scope.data.opciones.contactoShow = false;
			$scope.data.opciones.proyectoShow = false;
			$scope.data.opciones.periodosShow = false;
			$scope.data.opciones.consultarShow = false;
			$scope.data.opciones.headCountShow = false;
			$scope.data.opciones.empresasShow = false; 	
			$scope.data.opciones.empresaShow = true; 			
	   } else {
		   $scope.data.proyecto = null;
		   $scope.data.bandeja.idProyectoSel = 0;
		   $scope.data.opciones.bandejaShow = false;
		   $scope.data.opciones.contactoShow = false;
		   $scope.data.opciones.proyectoShow = false;
		   $scope.data.opciones.periodosShow = false;
		   $scope.data.opciones.consultarShow = false;
		   $scope.data.opciones.headCountShow = false;
			$scope.data.opciones.empresasShow = false; 
		   $scope.data.opciones.empresaShow = true;  
	   }
   }

   $scope.seleccionarEmpresa = function (empresa) {
	   $scope.data.bandeja.idEmpresaSel = empresa.id;
	   console.log('idEmpresaSel ['+$scope.data.bandeja.idEmpresaSel+"]");
	   $scope.data.empresa = empresa;	   
   };
   
   $scope.registrarEmpresa = function(){
	   console.log('empresa ['+$scope.data.empresa+']');
	   var infoEmpresa = $scope.data.empresa;
	   $http({
			url : "./empresa/registrar",
			dataType : "json",
			method : "POST",
			data : infoEmpresa,
			headers : {
				"Content-Type" : "application/json"
			}
		}).then(function successCallback(response) {
			console.log('response ['+response+']');
			alert('La empresa se guardo satisfactoriamente')
			$window.location = './climaLaboral.html?mp=0&me=1';
		}, function errorCallback(response) {
			console.log('error ['+response+']');
			alert('*** Error en la operación ***');
		});
   }
   
   $scope.datosContacto = function(){
	   console.log('idProyecto ['+$scope.data.bandeja.idProyectoSel+']');
	   if(Number.isInteger($scope.data.bandeja.idProyectoSel)){
		    $scope.data.opciones.bandejaShow = false;
		    $scope.data.opciones.empresaShow = false;
			$scope.data.opciones.proyectoShow = false;
			$scope.data.opciones.periodosShow = false;
			$scope.data.opciones.consultarShow = false;
			$scope.data.opciones.headCountShow = false;
			$scope.data.opciones.contactoShow = true;
	   } else {
			alert('Seleccione un proyecto');
	   }
   }
   
   $scope.logout = function(){
	   $http({
	        method: 'get',
	        url: './usuario/logout',
	        }).then(function (resultado) {
	            console.log(resultado);
	            $window.location = './loginNirho.html';
	        }
	   );
   }
   
   
   $scope.resetRow = function(row, rowForm){
	      row.isEditing = false;
	      rowForm.$setPristine();
//	      self.tableTracker.untrack(row);
//	      getDataEmpresas();
	    }
   
   $scope.cancel = function(row, rowForm){
	      row.isEditing = false;
	      rowForm.$setPristine();
	      getDataEmpresas();
	      $scope.tableParams.reload();
	    }

   $scope.save = function (row, rowForm) {
	   console.log('Llego edicion row: ' + row);
	   console.log('rowForm: ' + rowForm);
	   $http({
			url : "./empresa/registrar",
			dataType : "json",
			method : "POST",
			data : row,
			headers : {
				"Content-Type" : "application/json"
			}
		}).then(function successCallback(response) {
			console.log('response ['+response+']');
		      getDataEmpresas();
		      row.isEditing = false;
		      rowForm.$setPristine();
				$scope.tableParams.reload();
			alert('La empresa se guardo satisfactoriamente')
		}, function errorCallback(response) {
			console.log('error ['+response+']');
			alert('*** Error en la operación la empresa tiene información asociada borre, primero esa información***');
		});
   }
   
   $scope.del =  function (row) {
	   console.log('Llego edicion row: ' + row);
	   $http({
			url : "./empresa/eliminar",
			dataType : "json",
			method : "POST",
			data : row,
			headers : {
				"Content-Type" : "application/json"
			}
		}).then(function successCallback(response) {
			console.log('response ['+response+']');
		      getDataEmpresas();
				$scope.tableParams.reload();
		      row.isEditing = false;
			alert('La empresa se elimino satisfactoriamente')
		}, function errorCallback(response) {
			console.log('error ['+response+']');
			alert('*** Error en la operación la empresa tiene información asociada borre, primero esa información***');
		});
   }
   
   $scope.processEmpresaEvaluacion=function(data){
       console.log('En processEmpresaEvaluacion ' + data);
   }
   
   $scope.selectedRow = null;  // initialize our variable to null
   $scope.setClickedRow = function(emp){  //function that sets the value of selectedRow to current index
      $scope.selectedRow = emp;
      console.log('En setClickedRow ' + emp);
   }
   
}]);
