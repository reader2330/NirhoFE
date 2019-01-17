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
  saveActividad(id, data): Observable<any> {
    return this.http.post(this.api + 'vacante/' + id + '/actividades/guardar', data , {headers: this.headers});
  }
  deleteActividad(id): Observable<any> {
    return this.http.post(this.api + 'actividadesPuestoVacante/eliminar', id, {headers: this.headers});
  }
  saveCaracteristicas(id, data): Observable<any> {
    return this.http.post(this.api + 'vacante/' + id + '/caracteristicas/guardar', data, {headers: this.headers});
  }
  saveCompetencia(id, data): Observable<any> {
    return this.http.post(this.api + 'vacante/' + id + '/competencias/guardar', data, {headers: this.headers});
  }
  deleteCompetencias(id): Observable<any> {
    return this.http.post(this.api + 'competenciasVacante/eliminar', id, {headers: this.headers});
  }
  saveConocimiento(id, data): Observable<any> {
    return this.http.post(this.api + 'vacante/' + id + '/conocimientos/guardar', data, {headers: this.headers})
  }
  deleteConocimiento(id): Observable<any> {
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
  savePuestos(id, data): Observable<any> {
    return this.http.post(this.api + 'candidato/' + id + '/puestos/guardar', data, {headers: this.headers});
  }
  deletePuesto(id): Observable<any> {
    return this.http.post(this.api + 'experienciaCandidato/eliminar', id, {headers: this.headers});
  }
  getCandidatoRFC(rfc): Observable<any> {
    return this.http.get(this.api + 'candidato/porRFC', {headers: this.headers, params: {'rfc': rfc}});
  }
  saveCaractesristicasCandidato(id, data): Observable<any> {
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
  getCandidatos(): Observable<any> {
    return this.http.get(this.api + 'candidato/todos', {headers: this.headers});
  }
  getEntrevista(): Observable<any> {
    return this.http.get(this.api + 'entrevistaVacante/todos', {headers: this.headers});
  }
  saveEntrevista(data): Observable<any> {
    return this.http.post(this.api + 'entrevistaVacante/guardar', data, {headers: this.headers});
  }
  getVacantesBySolicitante(username): Observable<any> {
    return this.http.get(this.api + 'solicitante/vacantes', {headers: this.headers, params:{
      'username': username
      }});
  }
  saveComentario(id, type, data) {
    return this.http.post(this.api + 'entrevistaVacante/' + id + '/' + type + '/comentario', data, {headers: this.headers});
  }
  deleteCandidatoVacante(idCandidato) {
    return this.http.post(this.api + 'candidato/' + idCandidato + '/vacante/eliminar', {}, {headers: this.headers});
  }
  saveContrato(data) {
    return this.http.post(this.api + 'contratacionVacante/guardar', data, {headers: this.headers});
  }
  getEntrevistaByType(type, username): Observable<any> {
      return this.http.get(this.api + 'entrevistaVacante/porUsername' + type, {headers: this.headers, params: {
      'username': username
      }});
  }
  getContrato(id): Observable<any> {
    return this.http.get(this.api + 'vacante/' + id + '/contrato', { headers: this.headers});
  }
  getContratosCandidato(username): Observable<any> {
    return this.http.get(this.api + 'candidato/contrato', {headers: this.headers, params: {'username': username}});
  }
  setAceptado(id, value) {
   return this.http.get(this.api + 'contratacionVacante/' + id + '/aceptado/' + value, {headers: this.headers});
  }
  saveDocument(data) {
    return this.http.post(this.api + 'candidatoDocumento/guardar', data, {headers: this.headers});
  }
  getDocumentosByCandidato(id): Observable<any> {
    return this.http.get(this.api + 'candidatoDocumento/porCandidato', {headers: this.headers , params: {
        idCandidato: id
      }});
  }
  getDocumentoByID(id) {
    window.open(this.api + 'candidatoDocumento/' + id + '/descargar');
  }

  deleteDocument(id) {
    return this.http.delete(this.api + 'candidatoDocumento/' + id + '/eliminar', {headers: this.headers});
  }
  savePeriod(id, data) {
    return this.http.post(this.api + 'vacante/' + id + '/guardarGarantia',  data, {headers: this.headers});
  }
  getConsultores(): Observable<any> {
    return this.http.get(this.api + 'usuario/consultores', {headers: this.headers});
  }
  saveConsultor(idVacante, idCandidato): Observable<any> {
    return this.http.post(this.api + 'vacante/' + idVacante + '/asignarConsultor/' + idCandidato, {}, { headers: this.headers});
  }
  getVacantesConsultor(id): Observable<any> {
    return this.http.post(this.api + 'vacante/todos/porIdConsultor', {} ,  {headers: this.headers, params: {
        idConsultor: id
      }});
  }
  getCandidatoByID(id): Observable<any> {
    return this.http.get(this.api + 'candidato/' + id, {headers: this.headers});
  }


}
