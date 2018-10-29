import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {environment} from '../../environments/environment.prod';
import {Observable} from 'rxjs';
import {Proyecto} from '../models/proyecto';

@Injectable({
  providedIn: 'root'
})
export class ProyectoService {
  api = environment.urlApi;
  headers = new HttpHeaders();
  constructor(private http: HttpClient, ) {

    this.headers.append('Content-Type', 'application/json');
  }

  saveProyect(data): Observable<any> {
    return this.http.post(this.api + 'proyecto/registrar', data, {headers: this.headers});
  }
  getProyects(): Observable<Proyecto[]> {
    return this.http.get<Proyecto[]>(this.api + 'proyecto/todos', {headers: this.headers});
  }

  saveHead(data): Observable<any> {
    return this.http.post(this.api + 'participantes/headCount', data, {headers: this.headers} );
  }

  savePeriod(data): Observable<any> {
    return this.http.post(this.api + 'proyecto/agignarPeriodoGarantia', data, {headers: this.headers});
  }
  getOrganigrama(id): Observable<any> {
    return this.http.get(this.api + 'participantes/organigrama/', {headers: this.headers, params: {'idProyecto': id}});
  }

}
