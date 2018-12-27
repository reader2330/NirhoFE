import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {Proyecto} from '../../clb/models/proyecto';
import {environment} from '../../../../environments/environment';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProyectoApoService {

  api = environment.urlApi;
  headers = new HttpHeaders();
  constructor(private http: HttpClient) {
    this.headers.append('Content-Type', 'application/json');
  }

  getProyects(): Observable<Proyecto[]> {
    return this.http.get<Proyecto[]>(this.api + 'proyectoAPO/todos', {headers: this.headers});
  }
  getProyect(id): Observable<any>{
   return this.http.get(this.api + 'proyectoAPO/porId', {headers: this.headers, params: {'idProyecto': id}});
  }

  saveProyect(data): Observable<any> {
    return this.http.post(this.api + 'proyectoAPO/registrar', data, {headers: this.headers});
  }

  updateProyect(data): Observable<any> {
    return this.http.post(this.api + 'proyectoAPO/agignarPeriodoGarantia', data, {headers: this.headers});
  }

  getNacionality(): Observable<any> {
    return this.http.get(this.api + 'catalogo/pais', {headers: this.headers});
  }

  getGiros(): Observable<any> {
    return this.http.get(this.api + 'catalogo/giro',{headers: this.headers});
  }

  getTypeContact(): Observable<any> {
    return this.http.get(this.api + 'catalogo/tipoContacto');
  }

  saveHead(data): Observable<any> {
    return this.http.post(this.api + 'participantesAPO/headCount', data, {headers: this.headers} );
  }

  saveHeadAmp(data): Observable<any> {
    return this.http.post(this.api + 'participantesAPO/headCountAmp', data, {headers: this.headers} );
  }
  savePeriod(data): Observable<any> {
    return this.http.post(this.api + 'proyectoAPO/agignarPeriodoGarantia', data, {headers: this.headers});
  }
  getProyectID(id): Observable<any> {
    return this.http.get(this.api + 'proyectoAPO/porId', {headers: this.headers , params: {
      'idProyecto': id
      }});
  }
  sendCuestionario(idProyecto) {
    return this.http.get(this.api + 'participantesAPO/enviocorreo', {headers: this.headers, params:   {
        idProyecto: idProyecto
      }});
  }
  getConsultores(): Observable<any> {
    return this.http.get(this.api + 'usuario/consultores', {headers: this.headers});
  }
  saveConsultor(data): Observable<any> {
    return this.http.post(this.api + 'proyectoAPO/asignarConsultor', data, {headers: this.headers});
  }
  getModules(rol): Observable<any> {
    return this.http.get(this.api + 'usuario/role/' + rol + '/submodulosAPO', {headers: this.headers});
  }
  getParticipantes(id): Observable<any> {
    return this.http.get(this.api + 'participantesAPO/participantes', {headers: this.headers , params: {'idProyecto': id }});
  }
  getAmpliacion(id): Observable<any> {
    return this.http.get(this.api + 'participantesAPO/ampliaciones', {headers: this.headers , params: {'idParticipante': id}});
  }
  getStatus(id): Observable<any> {
    return this.http.get(this.api + 'proyectoAPO/estatus', {headers: this.headers , params: {'idProyecto': id}});
  }
  getOrganigrama(id): Observable<any> {
    return this.http.get(this.api + 'participantesAPO/organigrama', {headers: this.headers, params: {'idProyecto': id}});

  }
  getToken(token) {
    return this.http.get(this.api + 'participantesAPO/' + token, {headers: this.headers} );
  }
  guardaActividad(id, data) {
    console.log(id);
    console.log(data);
    return this.http.post(this.api + 'participantesAPO/funciones/' + id + '/actividades/guardar', data, {headers: this.headers});
  }
  updateObjetivos(data) {
    return this.http.post(this.api + 'participantesAPO/funciones/guardar', data, { headers: this.headers});
  }
  updateCalificacionActividad(idFunction, idActividad, calificacion) {
    return this.http.post(this.api + 'participantesAPO/funciones/' + idFunction + '/actividades/' + idActividad + '/calificar/' + calificacion, {}, {headers: this.headers});
  }
  getCalificaciones(id) {
    return this.http.get(this.api + 'participantesAPO/' + id + '/funciones/calificaciones',  {headers: this.headers});
  }
  getStatusActividades(id) {
    return this.http.get(this.api + 'participantesAPO/' + id + '/funciones/actividades/status');
  }


}
