import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {environment} from '../../environments/environment.prod';
import {Observable} from 'rxjs';
import {Proyecto} from '../models/proyecto';
import {Pregunta} from '../models/pregunta';


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
  getTemas(): Observable<any> {
    return this.http.get(this.api + 'cuestionario/temas/', {headers: this.headers, params: {'idModulo': '1'}});
  }
  getPreguntas(id): Observable<Pregunta[]> {
    return this.http.get<Pregunta[]>(this.api + 'cuestionario/preguntas', {headers: this.headers, params: {'idTema': id }});
  }
  savePreguntas(data): Observable<any> {
    return this.http.post(this.api + 'cuestionario/configurar', data, {headers: this.headers});
  }
  getConsultores(): Observable<any> {
    return this.http.get(this.api + 'usuario/consultores', {headers: this.headers});
  }
  saveConsultor(data): Observable<any> {
    return this.http.post(this.api + 'proyecto/asignarConsultor', data, {headers: this.headers});
  }

}
