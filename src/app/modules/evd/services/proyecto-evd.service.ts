import { Injectable } from '@angular/core';
import {environment} from '../../../../environments/environment';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Proyecto} from '../../clb/models/proyecto';
import {Pregunta} from '../../clb/models/pregunta';

@Injectable({
  providedIn: 'root'
})
export class ProyectoEvdService {

  api = environment.urlApi;
  headers = new HttpHeaders();
  constructor(private http: HttpClient, ) {

    this.headers.append('Content-Type', 'application/json');
  }

  saveProyect(data): Observable<any> {
    return this.http.post(this.api + 'proyectoEVD/registrar', data, {headers: this.headers});
  }

  getProyects(): Observable<Proyecto[]> {
    return this.http.get<Proyecto[]>(this.api + 'proyectoEVD/todos', {headers: this.headers});
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

  savePreguntas(data): Observable<any> {
    return this.http.post(this.api + 'cuestionario/configurar', data, {headers: this.headers});
  }
  getConsultores(): Observable<any> {
    return this.http.get(this.api + 'usuario/consultores', {headers: this.headers});
  }
  saveConsultor(data): Observable<any> {
    return this.http.post(this.api + 'proyecto/asignarConsultor', data, {headers: this.headers});
  }
  getProyectsbyRol(id): Observable<Proyecto[]> {
    return this.http.get<Proyecto[]>(this.api + 'cuestionario/preguntas', {headers: this.headers, params: {'idUsuario': id }});
  }
  getPreguntasProyect(id): Observable<any> {
    return this.http.get(this.api + 'cuestionario/verPreguntas', {headers: this.headers, params: {'idProyecto': id}});
  }
  getPreguntasParticipante(id): Observable<any> {
    return this.http.get(this.api + 'cuestionario/participante',{headers:this.headers, params: {'token': id}});
  }
  updatePregunta(data) {
    return this.http.post(this.api + 'cuestionario/contestaPregPart', data, {headers:this.headers})
  }
  getNacionality(): Observable<any> {
    return this.http.get(this.api + 'catalogo/pais', {headers: this.headers});
  }
  getGiros(): Observable<any> {
    return this.http.get(this.api + 'catalogo/giro', {headers: this.headers});
  }
  getPreguntas(id): Observable<Pregunta[]> {
    return this.http.get<Pregunta[]>(this.api + 'cuestionario/plantilla', {headers: this.headers, params: {'idModulo': '1' }});
  }
}
