/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
angular.module('adminUsuariosModule', [])
 .controller('adminUsuariosController', ['$scope', '$http', function($scope, $http) {         
             
   $scope.data = {
       filtro: {
           idEstado: null,
           idMunicipio: null
       },
       institucionesOptions: null,
       estadosOptions: null,
       municipiosOpcions: null
   };
   
   $http({
        method: 'get',
        url: 'http://localhost:8080/cev/catalogo/estados',
            headers:{
                fechaUltimaSinc : '2017-09-09'
            }
        }).then(function (resultado) {
            console.log("---------------------------------------");
            console.log(resultado.data);
            $scope.data.estadosOptions = resultado.data.result;
        }
   );
   
   $scope.cargarMunicipios= function () {
       console.log('idEstado ['+$scope.data.filtro.idEstado+"]");
       $http({
            method: 'get',
            url: 'http://localhost:8080/cev/catalogo/municipios',
                headers:{
                    fechaUltimaSinc : '2017-09-09'
                }
            }).then(function (resultado) {
                console.log("---------------------------------------");
                console.log(resultado.data.result.length + ' municipios');
                $scope.data.municipiosOpcions = resultado.data.result;
                munTemp = [];
                for(var i=0; i < $scope.data.municipiosOpcions.length; i++){
                    mun = $scope.data.municipiosOpcions[i];
                    console.log(mun.id_estado);
                    if(mun.id_estado == $scope.data.filtro.idEstado){
                        munTemp.push(mun);
                    }
                }
                $scope.data.municipiosOpcions = munTemp;
                console.log($scope.data.municipiosOpcions);
            }
       );
   };
   
   $scope.cargarLocalidades= function () {
       console.log('idMunicipio ['+$scope.data.filtro.idMunicipio+"]");
   };
   
}]);