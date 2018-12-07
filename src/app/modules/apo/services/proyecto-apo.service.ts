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
}
