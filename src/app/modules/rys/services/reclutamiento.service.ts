import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '../../../../../node_modules/@angular/common/http';
import {environment} from '../../../../environments/environment';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReclutamientoService {

  headers = new HttpHeaders();
  api = environment.urlApi;
  constructor(private http: HttpClient) {
    this.headers.append('Content-Type', 'application/json');
  }
  saveSolicitante(data): Observable<any> {
    return this.http.post(this.api + 'solicitante/guardar', data, {headers: this.headers});
  }
  getSolicitantes(): Observable<any> {
    return this.http.get(this.api + 'solicitante/todos', {headers: this.headers});
  }
  getSolicitanteByRFC(rfc): Observable<any> {
    return this.http.get(this.api + 'solicitante/porRFC', {headers: this.headers, params: {'rfc': rfc}});
  }
  saveContacto(id, data): Observable<any> {
    return this.http.post(this.api + 'solicitante/' + id + '/contacto/guardar', data, {headers: this.headers} );
  }
  saveVacante(id, data): Observable<any> {
    return this.http.post(this.api + 'solicitante/' + id + '/vacante/guardar', data, { headers: this.headers});
  }
  saveActividad(id, data) {
    return this.http.post(this.api + 'vacante/' + id + '/actividades/guardar', data , {headers: this.headers});
  }
  deleteActividad(id) {
    return this.http.post(this.api + 'actividadesPuestoVacante/eliminar', id, {headers: this.headers});
  }
  saveCaracteristicas(id, data){
    return this.http.post(this.api + 'vacante/' + id + '/caracteristicas/guardar', data, {headers: this.headers});
  }
  saveCompetencia(id, data) {
    return this.http.post(this.api + 'vacante/' + id + '/competencias/guardar', data, {headers: this.headers});
  }
  deleteCompetencias(id) {
    return this.http.post(this.api + 'competenciasVacante/eliminar', id, {headers: this.headers});
  }
  saveConocimiento(id, data) {
    return this.http.post(this.api + 'vacante/' + id + '/conocimientos/guardar', data, {headers: this.headers})
  }
  deleteConocimiento(id) {
    return this.http.post(this.api + 'conocimientoVacante/eliminar', id, {headers: this.headers});
  }
  getVacantes(): Observable<any> {
    return this.http.get(this.api + 'vacante/todos', { headers: this.headers});
  }
  removeVacante(id): Observable<any> {
    return this.http.post(this.api + 'vacante/eliminar', id, {headers: this.headers});
  }
  saveCandidato(data): Observable<any> {
    return this.http.post(this.api + 'candidato/guardar', data, {headers: this.headers});
  }
  saveContactoCandidato(id , data): Observable<any> {
    return this.http.post(this.api + 'candidato/' + id + '/contactos/guardar', data, {headers: this.headers});
  }
  deleteContacto(id): Observable<any> {
    return this.http.post(this.api + 'contactoCandidato/eliminar', id, {headers: this.headers});
  }
  saveIdioma(id, data): Observable<any> {
    return this.http.post(this.api + 'candidato/' + id + '/idiomas/guardar', data, {headers: this.headers});
  }
  deleteIdioma(id): Observable<any> {
    return this.http.post(this.api + 'idiomaCandidato/eliminar', id, {headers: this.headers});
  }
  savePuestos(id, data) {
    return this.http.post(this.api + 'candidato/' + id + '/puestos/guardar', data, {headers: this.headers});
  }
  deletePuesto(id): Observable<any> {
    return this.http.post(this.api + 'experienciaCandidato/eliminar', id, {headers: this.headers});
  }
  getCandidatoRFC(rfc): Observable<any> {
    return this.http.get(this.api + 'candidato/porRFC', {headers: this.headers, params: {'rfc': rfc}});
  }
  saveCaractesristicasCandidato(id,data): Observable<any> {
    return this.http.post(this.api + 'candidato/' + id + '/caracteristicas/guardar', data, { headers: this.headers});
  }
  saveConocimientoCandidato(id, data): Observable<any> {
    return this.http.post(this.api + 'candidato/' + id + '/conocimientos/guardar', data, {headers: this.headers});
  }
  deleteConocimientoCandidato(id): Observable<any> {
    return this.http.post(this.api + 'conocimientoCandidato/eliminar', id, {headers: this.headers});
  }
  sendLogin(data): Observable<any> {
    return this.http.post(this.api + 'candidato/login', data, {headers: this.headers});
  }
  getCandidatosTotales(): Observable<any> {
    return this.http.get(this.api + 'candidato/conteo', {headers: this.headers});
  }
  getVacantesTotales(): Observable<any> {
    return this.http.get(this.api + 'vacante/conteo', {headers: this.headers});
  }
  getVacanteTipo(): Observable<any> {
    return this.http.get(this.api + 'vacante/totales', {headers: this.headers});
  }
  setVacante(idCand, id): Observable<any> {
    return this.http.post(this.api + 'candidato/' + idCand + '/vacante/' + id + '/guardar', {}, {headers: this.headers});
  }

}
