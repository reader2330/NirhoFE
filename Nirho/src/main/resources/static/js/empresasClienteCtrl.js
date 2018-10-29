/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
angular.module('empresasClienteModule', ['ngTable', 'xeditable', 'ui.bootstrap'])
 .controller('empresasClienteController', ['$scope', '$http', '$window', 'NgTableParams', '$filter', '$dialog',
                                           function($scope, $http, $window, NgTableParams, $filter, $dialog) { 

	 
	 $scope.data = {
		       bandeja: {
		           idProyectoSel: null,
		           idEmpresaSel: null
		       },
			   opciones:{
				   showGrafica: false,
		    	   empresaShow: false,
		    	   consultarEmpresaShow: true,
		    	   showEvaluacion: false
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
	       url: './empresa/todas',
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
		       url: './empresa/todas',
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
                $scope.usuarioLogueado = resultado.data;
         	   $scope.data.usuario = resultado.data;
         	   console.log($scope.data.usuario.fullName);
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

   $scope.redirectEmpresas = function(){   
	   console.log('usuario logueado' + $scope.usuarioLogueado);  
	   var idRol = 0;
	   var redirectTo = './climaLaboral.html';
	   if($scope.usuarioLogueado != null){
		   idRol = $scope.usuarioLogueado.rol;
	   }
	   switch(idRol){
		   case 1:
			   redirectTo = './empresasC.html';
			   break;
		   case 2:
			   redirectTo = './empresasG.html';
			   break;
	   }
	   $window.location = redirectTo;	   
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
   
   $scope.showEmpresas = function(){
       console.log('En showEmpresas');
       $scope.selectedRow = null;
       $scope.data.empresa = null;
	   $scope.data.opciones.empresaShow = false;  
	   $scope.data.opciones.showGrafica = false;  
	   $scope.data.opciones.showEvaluacion = false;  
	   $scope.data.opciones.showCuestionario1 = false; 
	   $scope.data.opciones.showCuestionario2 = false; 	
	   $scope.data.opciones.showCuestionario3 = false; 	
	   $scope.data.opciones.showCuestionario4 = false;
	   $scope.data.opciones.consultarEmpresaShow = true;  
   }
   
   $scope.showRegistrarEmpresa = function(){
       console.log('En showConsultarEmpresa');
       $scope.cargaCatPaises();
       $scope.cargaCatGirosEmpresas();
       $scope.selectedRow = null;
       $scope.data.empresa = null;
       $scope.data.registrarEmpresa = null;
	   $scope.data.opciones.consultarEmpresaShow = false;
	   $scope.data.opciones.showEvaluacion = false;  
	   $scope.data.opciones.showGrafica = false;  
	   $scope.data.opciones.showCuestionario1 = false; 
	   $scope.data.opciones.showCuestionario2 = false; 	
	   $scope.data.opciones.showCuestionario3 = false; 	
	   $scope.data.opciones.showCuestionario4 = false; 
	   $scope.data.opciones.empresaShow = true;  
   }
   

   $scope.showDetalleEmpresa = function(){
   $http({
       method: 'get',
       url: './empresa/consultarEmpresaIRHRfc?rfc='+$scope.selectedRow.rfc,
       }).then(function successCallback(response) {
           console.log(response.data);
           $scope.data.registrarEmpresa = response.data;
           $scope.cargaCatPaises();
           $scope.cargaCatGirosEmpresas();
    	   $scope.data.opciones.consultarEmpresaShow = false;
    	   $scope.data.opciones.showEvaluacion = false;  
    	   $scope.data.opciones.showGrafica = false;  
    	   $scope.data.opciones.showCuestionario1 = false; 
    	   $scope.data.opciones.showCuestionario2 = false; 	
    	   $scope.data.opciones.showCuestionario3 = false; 	
    	   $scope.data.opciones.showCuestionario4 = false; 
    	   $scope.data.opciones.empresaShow = true;  
		}, function errorCallback(response) {
			$scope.data.empresa = null;
			console.log('erroral consultar la empresa ['+response+"]");
		}
       );
   }
   
   $scope.showOcultarEmpresa = function(){
       console.log('En showOcultarrEmpresa');  
       $scope.data.empresa = null;
       $scope.selectedRow = null;
       $scope.data.empresa = null;
	   $scope.data.opciones.empresaShow = false;  
	   $scope.data.opciones.showEvaluacion = false; 
	   $scope.data.opciones.showGrafica = false;  
	   $scope.data.opciones.showCuestionario1 = false; 
	   $scope.data.opciones.showCuestionario2 = false; 	
	   $scope.data.opciones.showCuestionario3 = false; 	
	   $scope.data.opciones.showCuestionario4 = false;
	   $scope.data.opciones.consultarEmpresaShow = true; 
   }
   
   $scope.showGrafica = function(){
       console.log('En showGrafica');
	   $scope.data.opciones.consultarEmpresaShow = false;  
	   $scope.data.opciones.empresaShow = false;  
	   $scope.data.opciones.showEvaluacion = false; 
	   $scope.data.opciones.showCuestionario1 = false; 
	   $scope.data.opciones.showCuestionario2 = false; 	
	   $scope.data.opciones.showCuestionario3 = false; 	
	   $scope.data.opciones.showCuestionario4 = false;
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
   
   $scope.selectedIdTema=0;
   
   $scope.showCuestionario1 = function(){
       console.log('En showCuestionario1');
       $scope.selectedIdTema=1;
	   $scope.data.opciones.consultarEmpresaShow = false;  
	   $scope.data.opciones.empresaShow = false;  
	   $scope.data.opciones.showGrafica = false;  
	   $scope.data.opciones.showCuestionario1 = true; 
	   $scope.data.opciones.showCuestionario2 = false; 	
	   $scope.data.opciones.showCuestionario3 = false; 	
	   $scope.data.opciones.showCuestionario4 = false;
	   $http({
	       method: 'get',
	       url: './cuestionarioEmpresa/consultarPreguntasTemaCuestionarioConRespuesta?idTema='
	    	   +$scope.selectedIdTema+'&idParticipante='+$scope.selectedRow.id,
	       }).then(function (resultado) {
	           console.log(resultado.data);
	           $scope.preguntasCuestionario1List = resultado.data;
	       }
	  );
   }
   
   $scope.showCuestionario2 = function(){
       console.log('En showCuestionario2');
       $scope.selectedIdTema=2;
	   $scope.data.opciones.consultarEmpresaShow = false;  
	   $scope.data.opciones.empresaShow = false;  
	   $scope.data.opciones.showGrafica = false;  
	   $scope.data.opciones.showCuestionario1 = false; 
	   $scope.data.opciones.showCuestionario2 = true; 	
	   $scope.data.opciones.showCuestionario3 = false; 	
	   $scope.data.opciones.showCuestionario4 = false;
	   $http({
	       method: 'get',
	       url: './cuestionarioEmpresa/consultarPreguntasTemaCuestionarioConRespuesta?idTema='
	    	   +$scope.selectedIdTema+'&idParticipante='+$scope.selectedRow.id,
	       }).then(function (resultado) {
	           console.log(resultado.data);
	           $scope.preguntasCuestionario1List = resultado.data;
	       }
	  ); 		   
   }
   
   $scope.showCuestionario3 = function(){
       console.log('En showCuestionario3');
       $scope.selectedIdTema=3;
	   $scope.data.opciones.consultarEmpresaShow = false;  
	   $scope.data.opciones.empresaShow = false;  
	   $scope.data.opciones.showGrafica = false;  
	   $scope.data.opciones.showCuestionario1 = false; 
	   $scope.data.opciones.showCuestionario2 = false; 	
	   $scope.data.opciones.showCuestionario3 = true; 	
	   $scope.data.opciones.showCuestionario4 = false; 	
	   $http({
	       method: 'get',
	       url: './cuestionarioEmpresa/consultarPreguntasTemaCuestionarioConRespuesta?idTema='
	    	   +$scope.selectedIdTema+'&idParticipante='+$scope.selectedRow.id,
	       }).then(function (resultado) {
	           console.log(resultado.data);
	           $scope.preguntasCuestionario1List = resultado.data;
	       }
	  );	   
   }
   
   $scope.showCuestionario4 = function(){
       console.log('En showCuestionario4');
       $scope.selectedIdTema=4;
	   $scope.data.opciones.consultarEmpresaShow = false;  
	   $scope.data.opciones.empresaShow = false;  
	   $scope.data.opciones.showGrafica = false;  
	   $scope.data.opciones.showCuestionario1 = false; 
	   $scope.data.opciones.showCuestionario2 = false; 	
	   $scope.data.opciones.showCuestionario3 = false; 	
	   $scope.data.opciones.showCuestionario4 = true; 		
	   $http({
	       method: 'get',
	       url: './cuestionarioEmpresa/consultarPreguntasTemaCuestionarioConRespuesta?idTema='
	    	   +$scope.selectedIdTema+'&idParticipante='+$scope.selectedRow.id,
	       }).then(function (resultado) {
	           console.log(resultado.data);
	           $scope.preguntasCuestionario1List = resultado.data;
	       }
	  );   
   }
   
   $scope.showEvaluacion = function(){
       console.log('En showEvaluacion');
	   $scope.data.opciones.consultarEmpresaShow = false;  
	   $scope.data.opciones.empresaShow = false;  
	   $scope.data.opciones.showGrafica = false;   
	   $scope.data.opciones.showCuestionario1 = false; 
	   $scope.data.opciones.showCuestionario2 = false; 	
	   $scope.data.opciones.showCuestionario3 = false; 	
	   $scope.data.opciones.showCuestionario4 = false; 	
	   $scope.data.opciones.showEvaluacion = true; 
	   
   }
   
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
			   $scope.data.opciones.showGrafica = false; 			   
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
   
   $scope.selectedRow = null;  // initialize our variable to null
   $scope.setClickedRow = function(emp){  //function that sets the value of selectedRow to current index
      $scope.selectedRow = emp;
      console.log('En setClickedRow ' + emp);
   }
   
   $scope.showCumplimiento = function(pregunta) {
       var selected = [];
       if(pregunta.respuesta.cumplimiento) {
         selected = $filter('filter')($scope.opcionesCumplimiento, {value: pregunta.respuesta.cumplimiento});
       }
       return selected.length ? selected[0].text : 'No seleccionado';
     };
     
     $scope.opcionesCumplimiento = [
                        {value: 'Si', text: 'Si'},
                        {value: 'No', text: 'No'},
                        {value: 'No aplica', text: 'No aplica'}
                      ]; 
     
     $scope.showVigencia = function(pregunta) {
         var selected = [];
         if(pregunta.respuesta.vigencia) {
           selected = $filter('filter')($scope.opcionesVigencia, {value: pregunta.respuesta.vigencia});
         }
         return selected.length ? selected[0].text : 'No seleccionado';
       };
       
     $scope.opcionesVigencia = [
                        {value: 'Vigente', text: 'Vigente'},
                        {value: 'Vencido', text: 'Vencido'},
                        {value: 'No aplica', text: 'No aplica'}
                      ]; 
     

     $scope.showEstado = function(pregunta) {
         var selected = [];
         if(pregunta.respuesta.estado) {
           selected = $filter('filter')($scope.opcionesEstado, {value: pregunta.respuesta.estado});
         }
         return selected.length ? selected[0].text : 'No seleccionado';
       };

     $scope.opcionesEstado = [
                        {value: 'En desarrollo', text: 'En desarrollo'},
                        {value: 'Formalizado y autorizado', text: 'Formalizado y autorizado'},
                        {value: 'Formalizado', text: 'Formalizado'},
                        {value: 'Autorizado y administrado', text: 'Autorizado y administrado'}
                      ]; 
   
     $scope.guardarPregunta = function(data, idRespuestaPregunta, idPregunta) {
         //$scope.user not updated yet
    	 data.idRespuestaPregunta = idRespuestaPregunta;
    	 data.idPregunta = idPregunta;
    	 data.idParticipante = $scope.selectedRow.id;
    	 data.idTema = $scope.selectedIdTema=1;
  	   	console.log(JSON.stringify(data));
  	  $http({
			url : "./cuestionarioEmpresa/agregarEditarRespuestasCuestionarioActivoEmpresaSingle",
			dataType : "json",
			method : "POST",
			data : data,
			headers : {
				"Content-Type" : "application/json"
			}
		}).then(function successCallback(response) {
			console.log('response ['+response+']'); 
			$scope.showDialog();
			alert('La pregunta se guardo satisfactoriamente');
		}, function errorCallback(response) {
			console.log('error ['+response+']');
			$scope.showDialog();
			alert('*** Error en la operación ***');
		});
//         angular.extend(data, {id: id});
//         return $http.post('/saveUser', data);
       };
       
       var dialogOptions = {
    		    controller: 'DialogCtrl',
    		    templateUrl: './dialog.html'
    		  };
   
       $scope.showDialog = function(){    	    
    	    $dialog.dialog(dialogOptions)
    	      .open()
    	      .then(function(result) {
    	    	  console.log('En showDialog result ' + result);
    	    });
    	  };
    	  
    	  function DialogCtrl($scope, msg, dialog){    		  
    		  $scope.msg = msg;
    		  
    		  $scope.save = function() {
    		    dialog.close(undefined);
    		  };
    		  
    		  $scope.close = function(){
    		    dialog.close(undefined);
    		  };
    	  }
    	  
   
}]);
