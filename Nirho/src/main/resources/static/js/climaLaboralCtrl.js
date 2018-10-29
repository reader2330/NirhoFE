/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
angular.module('climaLaboralModule', ['xeditable','ui.bootstrap'])
 .controller('climaLaboralController', ['$scope', '$http', '$window', function($scope, $http, $window) {   
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
    	   showTemasCuestionarios: false,
    	   consultarEmpresaShow : false
       },
       usuario: null,
       proyecto: null,
       empresa: null,
       registrarEmpresa: null,
       proyectoList: null,
       fechaRegistro: null,
       fechaFin: null,
   };
   
   $http({
       method: 'get',
       url: './proyecto/usuario',
       }).then(function (resultado) {
    	   console.log(resultado.data);
    	   if(resultado.data != ''){
        	   $scope.data.usuario = resultado.data;
        	   console.log($scope.data.usuario.fullName);
           } else {
        	   $window.location = './loginNirho.html';
           }
       }
   );
      
   $http({
        method: 'get',
        url: './proyecto/todos',
        }).then(function (resultado) {
            console.log(resultado.data);
            $scope.data.proyectoList = resultado.data;
        }
   );

	$http({
        method: 'get',
        url: './usuario/getUsuarioLogueado',
        }).then(function (resultado) {
            console.log(resultado.data);
            $scope.usuarioLogueado = resultado.data;
        }
   );
   
   $scope.seleccionarProyecto = function (proyecto) {
	   $scope.data.bandeja.idProyectoSel = proyecto.idProyecto;
	   console.log('idProyecto ['+$scope.data.bandeja.idProyectoSel+"]");
	   $scope.data.proyecto = proyecto;	   
   };
   
   $scope.mostrarBandejaProyectos = function () {
	   var mostrarBandeja = false;
	   if($scope.mp!= null && $scope.mp == 1){
		   mostrarBandeja = true;
	   }
	   $scope.data.opciones.bandejaShow = mostrarBandeja;	
	   return mostrarBandeja;
   };
   
   $scope.mostrarEmpresas = function () {
	   var mostrarEmpresas = false;
	   if($scope.me!= null &&  $scope.me == 1){
		   mostrarEmpresas = true;
	   }
	   $scope.data.opciones.empresasShow = mostrarEmpresas;	
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
			   $scope.data.opciones.consultarEmpresaShow = false;   
			   $scope.data.opciones.showGrafica = false;  
			   $scope.data.opciones.evaluacion = false;  
			   $scope.data.opciones.showTemasCuestionarios = false;
			   $scope.data.opciones.showCuestionario1 = false; 
			   $scope.data.opciones.showCuestionario2 = false; 	
			   $scope.data.opciones.showCuestionario3 = false; 	
			   $scope.data.opciones.showCuestionario4 = false; 	
			   $scope.data.opciones.showTemasCuestionarios = false;	
			   $scope.data.opciones.showPreguntasTemaCuestionario = false;		
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

		   $scope.data.opciones.consultarEmpresaShow = false;   
		   $scope.data.opciones.showGrafica = false;  
		   $scope.data.opciones.evaluacion = false;  
		   $scope.data.opciones.showTemasCuestionarios = false;
		   $scope.data.opciones.showCuestionario1 = false; 
		   $scope.data.opciones.showCuestionario2 = false; 	
		   $scope.data.opciones.showCuestionario3 = false; 	
		   $scope.data.opciones.showCuestionario4 = false; 	
		   $scope.data.opciones.showTemasCuestionarios = false;	
		   $scope.data.opciones.showPreguntasTemaCuestionario = false;
	   }
   }

   $scope.redirectEmpresas = function(){   
	   console.log('usuario logueado' + $scope.usuarioLogueado);  
	   var idRol = 0;
	   var redirectTo = './climaLaboral.html';
	   if($scope.usuarioLogueado != null){
		   idRol = $scope.usuarioLogueado.rol;
	   }
	   switch(idRol){
		   case 1:
			   redirectTo = './empresasc.html?me=1';
			   break;
		   case 2:
			   redirectTo = './empresasg.html';
			   break;
	   }
	   $window.location = redirectTo;	   
   }

   
   $http({
       method: 'get',
       url: './empresa/todas',
       }).then(function (resultado) {
           console.log(resultado.data);
           $scope.data.empresasList = resultado.data;
       }
  );
   

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

			   $scope.data.opciones.consultarEmpresaShow = false;   
			   $scope.data.opciones.showGrafica = false;  
			   $scope.data.opciones.evaluacion = false;  
			   $scope.data.opciones.showTemasCuestionarios = false;
			   $scope.data.opciones.showCuestionario1 = false; 
			   $scope.data.opciones.showCuestionario2 = false; 	
			   $scope.data.opciones.showCuestionario3 = false; 	
			   $scope.data.opciones.showCuestionario4 = false; 	
			   $scope.data.opciones.showTemasCuestionarios = false;	
			   $scope.data.opciones.showPreguntasTemaCuestionario = false;
	   } else {
			alert('Seleccione un proyecto');
	   }
   }
   
   $scope.datosProyecto = function(){
	   console.log('idProyecto ['+$scope.data.bandeja.idProyectoSel+']');
	   if(Number.isInteger($scope.data.bandeja.idProyectoSel)){
		    $scope.data.opciones.bandejaShow = false;
		    $scope.data.opciones.empresaShow = false;
			$scope.data.opciones.contactoShow = false;
			$scope.data.opciones.periodosShow = false;
			$scope.data.opciones.consultarShow = false;
			$scope.data.opciones.headCountShow = false;
			$scope.data.opciones.proyectoShow = true;

			   $scope.data.opciones.consultarEmpresaShow = false;   
			   $scope.data.opciones.showGrafica = false;  
			   $scope.data.opciones.evaluacion = false;  
			   $scope.data.opciones.showTemasCuestionarios = false;
			   $scope.data.opciones.showCuestionario1 = false; 
			   $scope.data.opciones.showCuestionario2 = false; 	
			   $scope.data.opciones.showCuestionario3 = false; 	
			   $scope.data.opciones.showCuestionario4 = false; 	
			   $scope.data.opciones.showTemasCuestionarios = false;	
			   $scope.data.opciones.showPreguntasTemaCuestionario = false;
	   } else {
			alert('Seleccione un proyecto');
	   }
   }
   
   $scope.consultarProyecto = function(){
	   console.log('idProyecto ['+$scope.data.bandeja.idProyectoSel+']');
	   if(Number.isInteger($scope.data.bandeja.idProyectoSel)){
		    $scope.data.opciones.bandejaShow = false;
		    $scope.data.opciones.empresaShow = false;
			$scope.data.opciones.contactoShow = false;
			$scope.data.opciones.periodosShow = false;
			$scope.data.opciones.proyectoShow = false;
			$scope.data.opciones.headCountShow = false;
			$scope.data.opciones.consultarShow = true;

			   $scope.data.opciones.consultarEmpresaShow = false;   
			   $scope.data.opciones.showGrafica = false;  
			   $scope.data.opciones.evaluacion = false;  
			   $scope.data.opciones.showTemasCuestionarios = false;
			   $scope.data.opciones.showCuestionario1 = false; 
			   $scope.data.opciones.showCuestionario2 = false; 	
			   $scope.data.opciones.showCuestionario3 = false; 	
			   $scope.data.opciones.showCuestionario4 = false; 	
			   $scope.data.opciones.showTemasCuestionarios = false;	
			   $scope.data.opciones.showPreguntasTemaCuestionario = false;
	   } else {
			alert('Seleccione un proyecto');
	   }
   }
   
   $scope.altaProyecto = function(){
	   $scope.data.proyecto = null;
	   $scope.data.bandeja.idProyectoSel = 0;
	   $scope.data.opciones.bandejaShow = false;
	   $scope.data.opciones.contactoShow = false;
	   $scope.data.opciones.proyectoShow = false;
	   $scope.data.opciones.periodosShow = false;
	   $scope.data.opciones.consultarShow = false;
	   $scope.data.opciones.headCountShow = false;
	   $scope.data.opciones.empresaShow = true;   

	   $scope.data.opciones.consultarEmpresaShow = false;   
	   $scope.data.opciones.showGrafica = false;  
	   $scope.data.opciones.evaluacion = false;  
	   $scope.data.opciones.showTemasCuestionarios = false;
	   $scope.data.opciones.showCuestionario1 = false; 
	   $scope.data.opciones.showCuestionario2 = false; 	
	   $scope.data.opciones.showCuestionario3 = false; 	
	   $scope.data.opciones.showCuestionario4 = false; 	
	   $scope.data.opciones.showTemasCuestionarios = false;	
	   $scope.data.opciones.showPreguntasTemaCuestionario = false;
   }
   
   $scope.cargarExcel = function(){
	   $scope.data.proyecto = null;
	   $scope.data.bandeja.idProyectoSel = 0;
	   $scope.data.opciones.bandejaShow = false;
	   $scope.data.opciones.contactoShow = false;
	   $scope.data.opciones.proyectoShow = false;
	   $scope.data.opciones.periodosShow = false;
	   $scope.data.opciones.consultarShow = false;
	   $scope.data.opciones.empresaShow = false;
	   $scope.data.opciones.headCountShow = true;

	   $scope.data.opciones.consultarEmpresaShow = false;   
	   $scope.data.opciones.showGrafica = false;  
	   $scope.data.opciones.evaluacion = false;  
	   $scope.data.opciones.showTemasCuestionarios = false;
	   $scope.data.opciones.showCuestionario1 = false; 
	   $scope.data.opciones.showCuestionario2 = false; 	
	   $scope.data.opciones.showCuestionario3 = false; 	
	   $scope.data.opciones.showCuestionario4 = false; 	
	   $scope.data.opciones.showTemasCuestionarios = false;	
	   $scope.data.opciones.showPreguntasTemaCuestionario = false;
   }
   
   $scope.enviarGerente = function(){
	   console.log('proyecto ['+$scope.data.proyecto+']');
	   console.log('idProyecto ['+$scope.data.bandeja.idProyectoSel+']');
	   var info = $scope.data.proyecto;
	   $http({
			url : "./proyecto/registrar",
			dataType : "json",
			method : "POST",
			data : info,
			headers : {
				"Content-Type" : "application/json"
			}
		}).then(function successCallback(response) {
			console.log('response ['+response+']');
			alert('El proyecto se guardo satisfactoriamente')
			$window.location = './climaLaboral.html';
		}, function errorCallback(response) {
			console.log('error ['+response+']');
			alert('*** Error en la operación ***');
		});
   }
   
   $scope.datosPeriodo = function(){
	   if(Number.isInteger($scope.data.bandeja.idProyectoSel)){
		   $scope.data.opciones.bandejaShow = false;
		   $scope.data.opciones.empresaShow = false;
		   $scope.data.opciones.contactoShow = false;
		   $scope.data.opciones.proyectoShow = false;
		   $scope.data.opciones.periodosShow = true;

		   $scope.data.opciones.consultarEmpresaShow = false;   
		   $scope.data.opciones.showGrafica = false;  
		   $scope.data.opciones.evaluacion = false;  
		   $scope.data.opciones.showTemasCuestionarios = false;
		   $scope.data.opciones.showCuestionario1 = false; 
		   $scope.data.opciones.showCuestionario2 = false; 	
		   $scope.data.opciones.showCuestionario3 = false; 	
		   $scope.data.opciones.showCuestionario4 = false; 	
		   $scope.data.opciones.showTemasCuestionarios = false;	
		   $scope.data.opciones.showPreguntasTemaCuestionario = false;
		   
		   if($scope.data.proyecto.fechaRegistro == null) {
			   $scope.data.fechaRegistro = new Date();
		   } else {
			   console.log('********* fecha registro ***********');
			   var sfechaR = $scope.data.proyecto.fechaRegistro.substring(0, 10).split('-').reverse().join('/');
			   var arreFechaR = sfechaR.split('/');
			   sfechaR = arreFechaR[1] + '/' + arreFechaR[0] + '/' + arreFechaR[2];
			   console.log(sfechaR);
			   var dfechaR = new Date(sfechaR);
			   console.log(dfechaR);
			   $scope.data.fechaRegistro = dfechaR;
		   } 
		   if($scope.data.proyecto.fechaFin != null) {
			   console.log('********* fecha fin ***********');
			   var sfechaFin = $scope.data.proyecto.fechaFin.substring(0, 10).split('-').reverse().join('/');
			   var arreFechaFin = sfechaFin.split('/');
			   sfechaFin = arreFechaFin[1] + '/' + arreFechaFin[0] + '/' + arreFechaFin[2];
			   console.log(sfechaFin);
			   var dfechaFin = new Date(sfechaFin);
			   console.log(dfechaFin);
			   $scope.data.fechaFin = dfechaFin;
		   }
	   } else {
			alert('Seleccione un proyecto');
	   }
   }
   
   $scope.asignarPeriodoGarantia = function(){
	   console.log('********** asignar periodo y garantia ************');
	   console.log('proyecto ['+$scope.data.proyecto+']');
	   console.log('idProyecto ['+$scope.data.bandeja.idProyectoSel+']');
	   var fechaR = $scope.data.fechaRegistro + '';
	   var fechaF = $scope.data.fechaFin + '';
	   console.log('fechaRegistro ['+fechaR+'] fechaFin ['+fechaF+']');
	   fechaR = fechaR.substring(4, 24);
	   fechaF = fechaF.substring(4, 24);
	   console.log('fechaRegistro ['+fechaR+'] fechaFin ['+fechaF+']');
	   var info = {
			proyecto: $scope.data.proyecto,
	        fechaRegistro: fechaR,
	   		fechaFin: fechaF,
	   }
	   $http({
			url : "./proyecto/agignarPeriodoGarantia",
			dataType : "json",
			method : "POST",
			data : info,
			headers : {
				"Content-Type" : "application/json"
			}
		}).then(function successCallback(response) {
			console.log('response ['+response+"]");
			alert('El periodo del proyecto y los días de gantia se asignaron satisfactoriamente')
			$window.location = './climaLaboral.html';
		}, function errorCallback(response) {
			console.log('error ['+response+"]");
			alert('*** Error en la operación ***');
		});
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
   
   var excelJsonObject = [];
   $scope.uploadExcel = function () {
		excelJsonObject
		var myFile = document.getElementById('fileExcel');
		var input = myFile;
		var reader = new FileReader();
		reader.onload = function () {
			var fileData = reader.result;
			var workbook = XLSX.read(fileData, { type: 'binary' });
			var iteracion = 0;
			workbook.SheetNames.forEach(function (sheetName) {
				var rowObject = XLSX.utils.sheet_to_row_object_array(workbook.Sheets[sheetName]);
				if (iteracion == 0) {
					excelJsonObject = rowObject;
				}
				iteracion = iteracion + 1;
			});
			console.log(JSON.stringify(excelJsonObject));
			for (var i = 0; i < excelJsonObject.length; i++) {
				var data = excelJsonObject[i];
				$('#tableExcel tbody:last-child').append("<tr><td>" + data.Fecha_de_ingreso + "</td><td>" + data.Antiguedad_en_el_puesto + "</td><td>" + data.Nivel_de_escolaridad + "</td><td>" + data.Otros_estudios + "</td><td>" + data.Idioma + "</td><td>" + data.Nivel + "</td><td>" + data.Correo_electronico + "</td><td>" + data.Sede + "</td><td>" + data.Area_organizacional + "</td></tr>");
			}
		};
		reader.readAsBinaryString(input.files[0]);

	};
	

	   $scope.cargaCatPaises = function(){
		   $http({
		       method: 'get',
		       url: './catalogos/paises',
		       }).then(function (resultado) {
		           console.log(resultado.data);
		           $scope.data.catPaises = resultado.data;
		       }
		  );
	   }

	   $scope.cargaCatGirosEmpresas = function(){
		   $http({
		       method: 'get',
		       url: './catalogos/girosEmpresas',
		       }).then(function (resultado) {
		           console.log(resultado.data);
		           $scope.data.catGirosEmpresas = resultado.data;
		       }
		  );
	   }
	   
	   $scope.showConsultarEmpresa = function(){
           console.log('En showConsultarEmpresa');
           $scope.data.empresa = null;
	       $scope.selectedRow = null;
	       $scope.data.empresa = null;
	       $scope.data.registrarEmpresa = null;
		   $scope.data.opciones.bandejaShow = false;
		   $scope.data.opciones.contactoShow = false;
		   $scope.data.opciones.proyectoShow = false;
		   $scope.data.opciones.periodosShow = false;
		   $scope.data.opciones.consultarShow = false;
		   $scope.data.opciones.headCountShow = false;
		   $scope.data.opciones.empresaShow = false;  
		   $scope.data.opciones.empresasShow = false;  
    	   $scope.data.opciones.showEvaluacion = false;  
    	   $scope.data.opciones.showGrafica = false;  
		   $scope.data.opciones.showTemasCuestionarios = false;
		   $scope.data.opciones.showPreguntasTemaCuestionario = false;
    	   $scope.data.opciones.showCuestionario1 = false; 
    	   $scope.data.opciones.showCuestionario2 = false; 	
    	   $scope.data.opciones.showCuestionario3 = false; 	
    	   $scope.data.opciones.showCuestionario4 = false; 
		   $scope.data.opciones.consultarEmpresaShow = true;  
	   }
	   
	   $scope.consultarEmpresa = function(){
		   var rfc = '';
           console.log('data.empresa.rfc : ' + $scope.data.empresa.rfc);
		   
		   $http({
		       method: 'get',
		       url: './empresa/consultarEmpresaIRHRfc?rfc='+$scope.data.empresa.rfc,
		       }).then(function successCallback(response) {
		           console.log(response.data);
		           $scope.data.empresa = response.data.empresa;
		           $scope.data.empresaConsultada = response.data;
				   $scope.data.opciones.bandejaShow = false;
				   $scope.data.opciones.contactoShow = false;
				   $scope.data.opciones.proyectoShow = false;
				   $scope.data.opciones.periodosShow = false;
				   $scope.data.opciones.consultarShow = false;
				   $scope.data.opciones.headCountShow = false;
				   $scope.data.opciones.empresasShow = false;  
		    	   $scope.data.opciones.showEvaluacion = false;  
		    	   $scope.data.opciones.showGrafica = false;
				   $scope.data.opciones.showTemasCuestionarios = false;
				   $scope.data.opciones.showPreguntasTemaCuestionario = false;
		    	   $scope.data.opciones.showCuestionario1 = false; 
		    	   $scope.data.opciones.showCuestionario2 = false; 	
		    	   $scope.data.opciones.showCuestionario3 = false; 	
		    	   $scope.data.opciones.showCuestionario4 = false; 
				   $scope.data.opciones.empresaShow = true;  
				   $scope.data.opciones.consultarEmpresaShow = false; 
				   $scope.cargaCatPaises();
				   $scope.cargaCatGirosEmpresas();
				}, function errorCallback(response) {
					$scope.data.empresa = null;
					console.log('error ['+response+"]");
					alert('*** La empresa no existe ***');
			           $scope.data.empresa = response.data;
					   $scope.data.opciones.bandejaShow = false;
					   $scope.data.opciones.contactoShow = false;
					   $scope.data.opciones.proyectoShow = false;
					   $scope.data.opciones.periodosShow = false;
					   $scope.data.opciones.consultarShow = false;
					   $scope.data.opciones.headCountShow = false;
					   $scope.data.opciones.empresasShow = false;  
			    	   $scope.data.opciones.showEvaluacion = false;  
			    	   $scope.data.opciones.showGrafica = false;
					   $scope.data.opciones.showTemasCuestionarios = false;
					   $scope.data.opciones.showPreguntasTemaCuestionario = false;
			    	   $scope.data.opciones.showCuestionario1 = false; 
			    	   $scope.data.opciones.showCuestionario2 = false; 	
			    	   $scope.data.opciones.showCuestionario3 = false; 	
			    	   $scope.data.opciones.showCuestionario4 = false; 
					   $scope.data.opciones.empresaShow = true;  
					   $scope.data.opciones.consultarEmpresaShow = false; 
					   $scope.cargaCatPaises();
					   $scope.cargaCatGirosEmpresas();
				}
		  );
	   }
	   
	   
	   $scope.selectedRow = null;  // initialize our variable to null
	   $scope.setClickedRow = function(emp){  //function that sets the value of selectedRow to current index
	      $scope.selectedRow = emp;
	      console.log('En setClickedRow ' + emp);
	   }
	   
	  
	   $scope.showDetalleEmpresa = function(){
		   $http({
		       method: 'get',
		       url: './empresa/consultarEmpresaIRHRfc?rfc='+$scope.data.empresa.rfc,
		       }).then(function successCallback(response) {
		           console.log(response.data);
		           $scope.data.registrarEmpresa = response.data;
				}, function errorCallback(response) {
					$scope.data.empresa = null;
					console.log('erroral consultar la empresa ['+response+"]");
			        $scope.data.registrarEmpresa = null;
				}
		       );
           $scope.cargaCatPaises();
           $scope.cargaCatGirosEmpresas();
		   $scope.data.opciones.bandejaShow = false;
		   $scope.data.opciones.contactoShow = false;
		   $scope.data.opciones.proyectoShow = false;
		   $scope.data.opciones.periodosShow = false;
		   $scope.data.opciones.consultarShow = false;
		   $scope.data.opciones.headCountShow = false;
    	   $scope.data.opciones.consultarEmpresaShow = false;
		   $scope.data.opciones.empresasShow = false;  
    	   $scope.data.opciones.showEvaluacion = false;  
    	   $scope.data.opciones.showGrafica = false;  
		   $scope.data.opciones.showTemasCuestionarios = false;
		   $scope.data.opciones.showPreguntasTemaCuestionario = false;
    	   $scope.data.opciones.showCuestionario1 = false; 
    	   $scope.data.opciones.showCuestionario2 = false; 	
    	   $scope.data.opciones.showCuestionario3 = false; 	
    	   $scope.data.opciones.showCuestionario4 = false; 
    	   $scope.data.opciones.empresaShow = true;  
		   }
	   
	   
	   $scope.registrarEmpresa = function(){
		   console.log(JSON.stringify($scope.data.registrarEmpresa));
		   var infoEmpresa = $scope.data.registrarEmpresa;
		   if(infoEmpresa == null || infoEmpresa.empresa == null || infoEmpresa.entrevistado == null){
			   alert('Debes ingresar los campos requeridos');
			   return;
		   }else if(infoEmpresa.empresa.empresa == null || infoEmpresa.empresa.empresa==''){
			   alert('El campo NOMBRE es requerido');
			   return;
		   }else if(infoEmpresa.empresa.rfc == null || infoEmpresa.empresa.rfc==''){
			   alert('El campo RFC es requerido');
			   return;
		   }else if(infoEmpresa.entrevistado.nombreResponsableLlenado == null || infoEmpresa.entrevistado.nombreResponsableLlenado==''){
			   alert('El campo NOMBRE DEL RESPONSABLE DEL LLENADO es requerido');
			   return;
		   }else if(infoEmpresa.entrevistado.puestoResponsableLlenado == null || infoEmpresa.entrevistado.puestoResponsableLlenado==''){
			   alert('El campo PUESTO DEL RESPONSABLE DEL LLENADO es requerido');
			   return;
		   }else if(infoEmpresa.entrevistado.nombreEntrevistador == null || infoEmpresa.entrevistado.nombreEntrevistador==''){
			   alert('El campo NOMBRE DEL ENTREVISTADOR es requerido');
			   return;
		   }else if(infoEmpresa.entrevistado.nombreEntrevistado == null || infoEmpresa.entrevistado.nombreEntrevistado==''){
			   alert('El campo NOMBRE DEL ENTREVISTADO es requerido');
			   return;
		   }else if(infoEmpresa.entrevistado.puestoEntrevistado == null || infoEmpresa.entrevistado.puestoEntrevistado==''){
			   alert('El campo PUESTO DEL ENTREVISTADO es requerido');
			   return;
		   }else if(infoEmpresa.entrevistado.correoElectronico == null || infoEmpresa.entrevistado.correoElectronico==''){
			   alert('El campo CORREO ELECTRÓNICO es requerido');
			   return;
		   }else if(infoEmpresa.entrevistado.telefonoCelular == null || infoEmpresa.entrevistado.telefonoCelular==''){
			   alert('El campo CELULAR es requerido');
			   return;
		   }else if(infoEmpresa.empresa.productoServicioEstrella == null || infoEmpresa.empresa.productoServicioEstrella==''){
			   alert('El campo PRODUCTO/SERVICIO ESTRELLA es requerido');
			   return;
		   }else if(infoEmpresa.empresa.principalesProductosServicios == null || infoEmpresa.empresa.principalesProductosServicios==''){
			   alert('El campo PRINCIPALES PRODUCTOS/SERVICIOS es requerido');
			   return;
		   }else if(infoEmpresa.empresa.tipoContratacionEmpleados == null || infoEmpresa.empresa.tipoContratacionEmpleados==''){
			   alert('El campo TIPO CONTRATACIÓN EMPLEADOS es requerido');
			   return;
		   }else{
			   
		   }
		   $http({
				url : "./empresa/registrarEmpresaIRH",
				dataType : "json",
				method : "POST",
				data : infoEmpresa,
				headers : {
					"Content-Type" : "application/json"
				}
			}).then(function successCallback(response) {
				console.log('response ['+response+']');
				   $scope.data.opciones.empresaShow = false;  
				   $scope.data.opciones.bandejaShow = false;
				   $scope.data.opciones.contactoShow = false;
				   $scope.data.opciones.proyectoShow = false;
				   $scope.data.opciones.periodosShow = false;
				   $scope.data.opciones.consultarShow = false;
				   $scope.data.opciones.headCountShow = false;
				   $scope.data.opciones.showGrafica = false; 	
				   $scope.data.opciones.showTemasCuestionarios = false;		
				   $scope.data.opciones.showPreguntasTemaCuestionario = false;   
				   $scope.data.opciones.showEvaluacion = false; 
				   $scope.data.opciones.showCuestionario1 = false; 
				   $scope.data.opciones.showCuestionario2 = false; 	
				   $scope.data.opciones.showCuestionario3 = false; 	
				   $scope.data.opciones.showCuestionario4 = false;
				   $scope.data.opciones.consultarEmpresaShow = true;  
				alert('La empresa se guardo satisfactoriamente');

				 $http({
				       method: 'get',
				       url: './empresa/todas',
				       }).then(function (resultado) {
				           console.log(resultado.data);
				           $scope.data.empresasList = resultado.data;
				       }
				  );
			}, function errorCallback(response) {
				console.log('error ['+response+']');
				alert('*** Error en la operación ***');
			});
	   }
	   
	   $scope.showOcultarEmpresa = function(){
	       console.log('En showOcultarrEmpresa');
	       $scope.selectedRow = null;
	       $scope.data.empresa = null;
	       $scope.data.registrarEmpresa = null;
		   $scope.data.opciones.bandejaShow = false;
		   $scope.data.opciones.contactoShow = false;
		   $scope.data.opciones.proyectoShow = false;
		   $scope.data.opciones.periodosShow = false;
		   $scope.data.opciones.consultarShow = false;
		   $scope.data.opciones.headCountShow = false;
		   $scope.data.opciones.empresaShow = false;  
		   $scope.data.opciones.empresasShow = false;  
		   $scope.data.opciones.showEvaluacion = false; 
		   $scope.data.opciones.showGrafica = false;  
		   $scope.data.opciones.showTemasCuestionarios = false;
		   $scope.data.opciones.showPreguntasTemaCuestionario = false;
		   $scope.data.opciones.showEvaluacion = false; 
		   $scope.data.opciones.showCuestionario1 = false; 
		   $scope.data.opciones.showCuestionario2 = false; 	
		   $scope.data.opciones.showCuestionario3 = false; 	
		   $scope.data.opciones.showCuestionario4 = false;
		   $scope.data.opciones.consultarEmpresaShow = true; 
	   }
	   
	   $scope.selectedIdTema=0;
	   
	   $scope.showCuestionario1 = function(){
	       console.log('En showCuestionario1');
	       $scope.selectedIdTema=1;
		   $scope.data.opciones.bandejaShow = false;
		   $scope.data.opciones.contactoShow = false;
		   $scope.data.opciones.proyectoShow = false;
		   $scope.data.opciones.periodosShow = false;
		   $scope.data.opciones.consultarShow = false;
		   $scope.data.opciones.headCountShow = false;
		   $scope.data.opciones.consultarEmpresaShow = false;  
		   $scope.data.opciones.empresaShow = false;  
		   $scope.data.opciones.empresasShow = false;  
		   $scope.data.opciones.showGrafica = false;  
		   $scope.data.opciones.showTemasCuestionarios = false;
		   $scope.data.opciones.showPreguntasTemaCuestionario = false;
		   $scope.data.opciones.showEvaluacion = false; 
		   $scope.data.opciones.showCuestionario1 = true; 
		   $scope.data.opciones.showCuestionario2 = false; 	
		   $scope.data.opciones.showCuestionario3 = false; 	
		   $scope.data.opciones.showCuestionario4 = false;
		   $http({
		       method: 'get',
		       url: './cuestionarioEmpresa/consultarPreguntasTemaCuestionarioConRespuesta?idTema='
		    	   +$scope.selectedIdTema+'&idParticipante='+$scope.data.registrarEmpresa.empresa.id,
		       }).then(function (resultado) {
		           console.log(resultado.data);
		           $scope.preguntasCuestionario1List = resultado.data;
		       }
		  );
	   }
	   
	   $scope.showCuestionario2 = function(){
	       console.log('En showCuestionario2');
	       $scope.selectedIdTema=2;
		   $scope.data.opciones.bandejaShow = false;
		   $scope.data.opciones.contactoShow = false;
		   $scope.data.opciones.proyectoShow = false;
		   $scope.data.opciones.periodosShow = false;
		   $scope.data.opciones.consultarShow = false;
		   $scope.data.opciones.headCountShow = false;
		   $scope.data.opciones.consultarEmpresaShow = false;  
		   $scope.data.opciones.empresaShow = false;  
		   $scope.data.opciones.empresasShow = false;  
		   $scope.data.opciones.showGrafica = false;  
		   $scope.data.opciones.showTemasCuestionarios = false;
		   $scope.data.opciones.showPreguntasTemaCuestionario = false;
		   $scope.data.opciones.showEvaluacion = false; 
		   $scope.data.opciones.showCuestionario1 = false; 
		   $scope.data.opciones.showCuestionario2 = true; 	
		   $scope.data.opciones.showCuestionario3 = false; 	
		   $scope.data.opciones.showCuestionario4 = false;
		   $http({
		       method: 'get',
		       url: './cuestionarioEmpresa/consultarPreguntasTemaCuestionarioConRespuesta?idTema='
		    	   +$scope.selectedIdTema+'&idParticipante='+$scope.data.registrarEmpresa.empresa.id,
		       }).then(function (resultado) {
		           console.log(resultado.data);
		           $scope.preguntasCuestionario1List = resultado.data;
		       }
		  ); 		   
	   }
	   
	   $scope.showCuestionario3 = function(){
	       console.log('En showCuestionario3');
	       $scope.selectedIdTema=3;
		   $scope.data.opciones.bandejaShow = false;
		   $scope.data.opciones.contactoShow = false;
		   $scope.data.opciones.proyectoShow = false;
		   $scope.data.opciones.periodosShow = false;
		   $scope.data.opciones.consultarShow = false;
		   $scope.data.opciones.headCountShow = false;
		   $scope.data.opciones.consultarEmpresaShow = false;  
		   $scope.data.opciones.empresaShow = false;  
		   $scope.data.opciones.empresasShow = false;  
		   $scope.data.opciones.showGrafica = false;  
		   $scope.data.opciones.showTemasCuestionarios = false;
		   $scope.data.opciones.showPreguntasTemaCuestionario = false;
		   $scope.data.opciones.showEvaluacion = false; 
		   $scope.data.opciones.showCuestionario1 = false; 
		   $scope.data.opciones.showCuestionario2 = false; 	
		   $scope.data.opciones.showCuestionario3 = true; 	
		   $scope.data.opciones.showCuestionario4 = false; 	
		   $http({
		       method: 'get',
		       url: './cuestionarioEmpresa/consultarPreguntasTemaCuestionarioConRespuesta?idTema='
		    	   +$scope.selectedIdTema+'&idParticipante='+$scope.data.registrarEmpresa.empresa.id,
		       }).then(function (resultado) {
		           console.log(resultado.data);
		           $scope.preguntasCuestionario1List = resultado.data;
		       }
		  );	   
	   }
	   
	   $scope.showCuestionario4 = function(){
	       console.log('En showCuestionario4');
	       $scope.selectedIdTema=4;
		   $scope.data.opciones.bandejaShow = false;
		   $scope.data.opciones.contactoShow = false;
		   $scope.data.opciones.proyectoShow = false;
		   $scope.data.opciones.periodosShow = false;
		   $scope.data.opciones.consultarShow = false;
		   $scope.data.opciones.headCountShow = false;
		   $scope.data.opciones.consultarEmpresaShow = false;  
		   $scope.data.opciones.empresaShow = false;  
		   $scope.data.opciones.empresasShow = false;  
		   $scope.data.opciones.showGrafica = false;  
		   $scope.data.opciones.showTemasCuestionarios = false;
		   $scope.data.opciones.showPreguntasTemaCuestionario = false;
		   $scope.data.opciones.showEvaluacion = false; 
		   $scope.data.opciones.showCuestionario1 = false; 
		   $scope.data.opciones.showCuestionario2 = false; 	
		   $scope.data.opciones.showCuestionario3 = false; 	
		   $scope.data.opciones.showCuestionario4 = true; 		
		   $http({
		       method: 'get',
		       url: './cuestionarioEmpresa/consultarPreguntasTemaCuestionarioConRespuesta?idTema='
		    	   +$scope.selectedIdTema+'&idParticipante='+$scope.data.registrarEmpresa.empresa.id,
		       }).then(function (resultado) {
		           console.log(resultado.data);
		           $scope.preguntasCuestionario1List = resultado.data;
		       }
		  );   
	   }
	   
	   $scope.showEvaluacion = function(){
	       console.log('En showEvaluacion');
		   $scope.data.opciones.bandejaShow = false;
		   $scope.data.opciones.contactoShow = false;
		   $scope.data.opciones.proyectoShow = false;
		   $scope.data.opciones.periodosShow = false;
		   $scope.data.opciones.consultarShow = false;
		   $scope.data.opciones.headCountShow = false;
		   $scope.data.opciones.consultarEmpresaShow = false;  
		   $scope.data.opciones.empresaShow = false;  
		   $scope.data.opciones.empresasShow = false;  
		   $scope.data.opciones.showGrafica = false;   
		   $scope.data.opciones.showCuestionario1 = false; 
		   $scope.data.opciones.showCuestionario2 = false; 	
		   $scope.data.opciones.showCuestionario3 = false; 	
		   $scope.data.opciones.showCuestionario4 = false; 	
		   $scope.data.opciones.showTemasCuestionarios = false;
		   $scope.data.opciones.showPreguntasTemaCuestionario = false;
		   $scope.data.opciones.showEvaluacion = true; 
		   
	   }
	   
	   $scope.showGrafica = function(){
	       console.log('En showGrafica');
		   $scope.data.opciones.bandejaShow = false;
		   $scope.data.opciones.contactoShow = false;
		   $scope.data.opciones.proyectoShow = false;
		   $scope.data.opciones.periodosShow = false;
		   $scope.data.opciones.consultarShow = false;
		   $scope.data.opciones.headCountShow = false;
		   $scope.data.opciones.consultarEmpresaShow = false;  
		   $scope.data.opciones.empresaShow = false;  
		   $scope.data.opciones.showEvaluacion = false; 
		   $scope.data.opciones.showCuestionario1 = false; 
		   $scope.data.opciones.showCuestionario2 = false; 	
		   $scope.data.opciones.showCuestionario3 = false; 	
		   $scope.data.opciones.showCuestionario4 = false;
		   $scope.data.opciones.showTemasCuestionarios = false;
		   $scope.data.opciones.showPreguntasTemaCuestionario = false;
		   $scope.data.opciones.showGrafica = true; 	   
		   

		   var chart = new CanvasJS.Chart("chartContainer", {
			   	animationEnabled: true,
			   	theme: "light2",
			   	title:{
			   		text: "Resultado Evaluación"
			   	},
				axisX: {
			   		text: "Evaluaciones",
			   	    labelAngle: 15,
			   	    labelAutoFit: true,
			   	    labelFontSize: 10,
				},
			   	axisY:{
			   		text: "Ponderación"
			   	},
				toolTip: {
					shared: true
				},
				legend: {
					cursor: "pointer",
					verticalAlign: "top",
					horizontalAlign: "center",
					dockInsidePlotArea: true,
					itemclick: toogleDataSeries
				},
			   	data: [
			   	  {        
			   		type: "line",       
					name: "Calificación",
					showInLegend: true,
					markerSize: 0,
			   		dataPoints: [
			   			{ x: 1, y: 0.8 ,markerColor: "green", label : "Reclutamiento y selección"},
			   			{ x: 2, y: 0.7 ,markerColor: "green", label : "Administración de recursos humanos"},
			   			{ x: 3, y: 0.75 ,markerColor: "green", label : "Desarrollo organizacional"},
			   			{ x: 4, y: 0.6  ,markerColor: "green", label : "Capacitación"}
			   		]
			   	  },
			   	  {        
				   		type: "line",       
						name: "Media",
						color: "orange",
						showInLegend: true,
						markerSize: 0,
				   		dataPoints: [
				   		   			{ x: 1, y: 0.6 ,markerColor: "orange", label : "Reclutamiento y selección"},
				   		   			{ x: 2, y: 0.6 ,markerColor: "orange", label : "Administración de recursos humanos"},
				   		   			{ x: 3, y: 0.55 ,markerColor: "orange", label : "Desarrollo organizacional"},
				   		   			{ x: 4, y: 0.6  ,markerColor: "orange", label : "Capacitación"}
				   		]
				   }
			   	
			   	]
			   });
			   chart.render();	  
			   $scope.showGraficaRadar();
	   }
	   
	   $scope.showGraficaRadar = function(){  
		   var ctx = document.getElementById("myRadarChart");
		   var myRadarChart = new Chart(ctx, {
			    type: 'radar',
			    data: {
			        labels: ['Reclutamiento y selección', 'Administración de recursos humanos', 
			                 'Desarrollo organizacional', 'Capacitación'],
			        datasets: [{
			            data: [0.8, 0.7, 0.75, 0.6]
			        }]
			    }
			});
		   myRadarChart.options.title.text = "Resultados";   // test, total fail
		   myRadarChart.options.legend.display = false; 
		   
	   }
	   
	   function toogleDataSeries(e){
			if (typeof(e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
				e.dataSeries.visible = false;
			} else{
				e.dataSeries.visible = true;
			}
			chart.render();
		}
	   
	   $scope.showTemasCuestionario = function(){
		   $http({
		       method: 'get',
		       url: './cuestionarioEmpresa/consultarTemasCuestionario',
		       }).then(function (resultado) {
		           console.log(resultado.data);
		           $scope.data.registrarEmpresa=null;
		           $scope.temasCuestionarioList = resultado.data;
		           $scope.totalItems = $scope.temasCuestionarioList.length;
				   $scope.data.opciones.bandejaShow = false;
				   $scope.data.opciones.contactoShow = false;
				   $scope.data.opciones.proyectoShow = false;
				   $scope.data.opciones.periodosShow = false;
				   $scope.data.opciones.consultarShow = false;
				   $scope.data.opciones.headCountShow = false;
				   $scope.data.opciones.consultarEmpresaShow = false;  
				   $scope.data.opciones.empresaShow = false;  
				   $scope.data.opciones.empresasShow = false;  
				   $scope.data.opciones.showGrafica = false;   
				   $scope.data.opciones.showCuestionario1 = false; 
				   $scope.data.opciones.showCuestionario2 = false; 	
				   $scope.data.opciones.showCuestionario3 = false; 	
				   $scope.data.opciones.showCuestionario4 = false; 	
				   $scope.data.opciones.showEvaluacion = false; 
				   $scope.data.opciones.showPreguntasTemaCuestionario = false;	
				   $scope.data.opciones.showTemasCuestionarios = true;
		       }
		  );
	   }

	   //pagination at initialization
	   $scope.totalItems = 0;
	   $scope.itemsPerPage = 5;
	   $scope.currentPage = 1;
	   // set page click
	   $scope.setPage = function(pageNo) {
	     $scope.currentPage = pageNo;
	   };
	   
	   $scope.numPages = function() {
	    $scope.temasCuestionarioList.length / $scope.numPerPage;
	   }
	   
	   $scope.addTemaCuestionario = function() {
		    $scope.inserted = {
//		      id: $scope.temasCuestionarioList.length+1,
		      id: 0,
		      nombre: '',
		      descripcion: ''
		    };
		 
		    $scope.temasCuestionarioList.push($scope.inserted);
		 // counting modulo of users.length with pagesize which is 4
		    if($scope.temasCuestionarioList != null){
			    var pagecount = $scope.temasCuestionarioList.length % 5;
			    if (pagecount >= 3){
			    	$scope.noOfPages++;
			    } // if 3 which is before new page add new page
			    $scope.totalItems = $scope.temasCuestionarioList.length;
		    }		      
		    
		  };
	   
	   $scope.guardarTemaCuestionario = function(data, tema) {
	    	 data.idTema = tema.idTema;
//	    	 $scope.cuestionarioEmpresa = {
//	    			 empresa: $scope.data.empresa,
//	    			 temaCuestionario: data
//	    	 };
	  	   	console.log(JSON.stringify(data));
	  	  $http({
				url : "./cuestionarioEmpresa/agregarTemaCuestionario",
				dataType : "json",
				method : "POST",
				data : data,
				headers : {
					"Content-Type" : "application/json"
				}
			}).then(function successCallback(response) {
				console.log('response ['+response+']'); 
				   $http({
				       method: 'get',
				       url: './cuestionarioEmpresa/consultarTemasCuestionario',
				       }).then(function (resultado) {
				           console.log(resultado.data);		
				           $scope.temasCuestionarioList = resultado.data;
				       });
					alert('El cuestionario se guardo satisfactoriamente');
			}, function errorCallback(response) {
				console.log('error ['+response+']');
				alert('*** Error en la operación ***');
			});
	       };

	       
	       $scope.showPreguntasTemaCuestionario = function(tema){
		       console.log('En showPreguntasTemaCuestionario');
		       $scope.selectedIdTema = tema.idTema;
		       $scope.selectedNombreTema = tema.nombre;
			   $http({
			       method: 'get',
			       url: './cuestionarioEmpresa/consultarPreguntasTemaCuestionario?idTema='+$scope.selectedIdTema,
			       }).then(function (resultado) {
			           console.log(resultado.data);
			           $scope.preguntasTemaCuestionarioList = resultado.data;
					   $scope.totalItemsPreguntas = $scope.preguntasTemaCuestionarioList.length;	
					   $scope.data.opciones.bandejaShow = false;
					   $scope.data.opciones.contactoShow = false;
					   $scope.data.opciones.proyectoShow = false;
					   $scope.data.opciones.periodosShow = false;
					   $scope.data.opciones.consultarShow = false;
					   $scope.data.opciones.headCountShow = false;
					   $scope.data.opciones.consultarEmpresaShow = false;  
					   $scope.data.opciones.empresaShow = false;  
					   $scope.data.opciones.empresasShow = false;  
					   $scope.data.opciones.showGrafica = false;  
					   $scope.data.opciones.evaluacion = false;  
					   $scope.data.opciones.showTemasCuestionarios = false;
					   $scope.data.opciones.showCuestionario1 = false; 
					   $scope.data.opciones.showCuestionario2 = false; 	
					   $scope.data.opciones.showCuestionario3 = false; 	
					   $scope.data.opciones.showCuestionario4 = false; 	
					   $scope.data.opciones.showPreguntasTemaCuestionario = true;	
			       }
			  );   
		   }
	       

		   $scope.totalItemsPreguntas = 0;
		   $scope.itemsPerPagePreguntas = 5;
		   $scope.currentPagePreguntas = 1;
	       
	       $scope.addPreguntaTemaCuestionario = function() {
			    $scope.insertedPregunta = {
//			      id: $scope.temasCuestionarioList.length+1,
//			      idPregunta: 0,
			      idTema: $scope.selectedIdTema,
			      nombreTema: $scope.selectedNombreTema,
			      enunciado: '',
			      documentoReferencia: ''
			    };
			 
			    $scope.preguntasTemaCuestionarioList.push($scope.insertedPregunta);
			 // counting modulo of users.length with pagesize which is 4
			    if($scope.preguntasTemaCuestionarioList != null){
				    var pagecount = $scope.preguntasTemaCuestionarioList.length % 5;
				    if (pagecount >= 3){
				    	$scope.noOfPagesPreguntas++;
				    } // if 3 which is before new page add new page			
				    $scope.totalItemsPreguntas = $scope.preguntasTemaCuestionarioList.length;	      
			    }
			    
			  };
			  
			  $scope.guardarPreguntaTemaCuestionario = function(data, pregunta) {
			    	 data.idPregunta = pregunta.idPregunta;
			    	 data.idTema = pregunta.idTema;
			    	 data.tipo = 1;
			    	 data.dePlantilla = 1;
			  	   	console.log(JSON.stringify(data));
			  	  $http({
						url : "./cuestionarioEmpresa/agregarPregunta",
						dataType : "json",
						method : "POST",
						data : data,
						headers : {
							"Content-Type" : "application/json"
						}
					}).then(function successCallback(response) {
						console.log('response ['+response+']'); 
						   $http({
						       method: 'get',
						       url: './cuestionarioEmpresa/consultarPreguntasTemaCuestionario?idTema='+$scope.selectedIdTema,
						       }).then(function (resultado) {
						           console.log(resultado.data);
						           $scope.preguntasTemaCuestionarioList = resultado.data;
						       }
						  );   
							alert('La pregunta se ha agregado al cuestionario satisfactoriamente');
					}, function errorCallback(response) {
						console.log('error ['+response+']');
						alert('*** Error en la operación ***');
					});
			       };
   
}]).filter('startFrom', function() {
  return function(input, start) {
    start = +start; //parse to int
    return input.slice(start);
  }
}).filter('startFromPreguntas', function() {
	  return function(input, start) {
		    start = +start; //parse to int
		    return input.slice(start);
		  }
		});


