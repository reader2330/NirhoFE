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
}
