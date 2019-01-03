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
    return this.http.post(this.api + 'candidato/' + id + '/contactos/guardar', data, {headers: this.headers})
  }
  deleteContacto(id): Observable<any> {
    return this.http.post(this.api + 'contactoCandidato/eliminar', id, {headers: this.headers})
  }
}
