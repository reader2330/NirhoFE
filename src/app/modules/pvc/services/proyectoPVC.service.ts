import { Injectable } from '@angular/core';
import {environment} from '../../../../environments/environment';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Proyecto} from '../../clb/models/proyecto';
import {Pregunta} from '../../clb/models/pregunta';

@Injectable({
  providedIn: 'root'
})
export class ProyectoPVCService {

  api = environment.urlApi;

  headers = new HttpHeaders();
  constructor(private http: HttpClient, ) {

    this.headers.append('Content-Type', 'application/json');
  }

  saveProyect(data): Observable<any> {
    return this.http.post(this.api + 'proyectoPVC/registrar', data, {headers: this.headers});
  }
    getProyects(): Observable<Proyecto[]> {
    return this.http.get<Proyecto[]>(this.api + 'proyectoPVC/todos', {headers: this.headers});
  }
  getProyectID(id): Observable<any> {
    return this.http.get(this.api + 'proyectoPVC/porId', {headers: this.headers , params: {
        'idProyecto': id
      }});
  }

  saveHead(data): Observable<any> {
    return this.http.post(this.api + 'participantesPVC/headCount', data, {headers: this.headers} );
  }

  savePeriod(data): Observable<any> {
    return this.http.post(this.api + 'proyectoPVC/agignarPeriodoGarantia', data, {headers: this.headers});
  }
  getOrganigrama(id): Observable<any> {
    return this.http.get(this.api + 'participantes/organigrama/', {headers: this.headers, params: {'idProyecto': id}});
  }
  getTemas(): Observable<any> {
    return this.http.get(this.api + 'cuestionario/temas', {headers: this.headers, params: {'idModulo': '3'}});
  }
  getPreguntas(id): Observable<Pregunta[]> {
    return this.http.get<Pregunta[]>(this.api + 'cuestionario/plantilla', {headers: this.headers, params: {'idModulo': '3' }});
  }
  savePreguntas(data): Observable<any> {
    return this.http.post(this.api + 'cuestionario/configEVD', data, {headers: this.headers});
  }
  getConsultores(): Observable<any> {
    return this.http.get(this.api + 'usuario/consultores', {headers: this.headers});
  }
  saveConsultor(data): Observable<any> {
    return this.http.post(this.api + 'proyectoPVC/asignarConsultor', data, {headers: this.headers});
  }
  getProyectsbyRol(id): Observable<Proyecto[]> {
    return this.http.get<Proyecto[]>(this.api + 'proyectoPVC/porConsultor', {headers: this.headers, params: {'idUsuario': id }});
  }
  getPreguntasProyect(id): Observable<any> {
    return this.http.get(this.api + 'cuestionario/preguntasEVD', {headers: this.headers, params: {'idProyecto': id}});
  }
  getPreguntasParticipante(id): Observable<any> {
    return this.http.get(this.api + 'cuestionario/participanteEVD', {headers: this.headers, params: {'token': id}});
  }
  updatePregunta(data) {
    return this.http.post(this.api + 'cuestionario/contestaPregPart', data, {headers: this.headers});
  }
  getGraficas(idProyecto) {
    return this.http.get(this.api + 'graficas/proyecto',{headers: this.headers, params:   {
        idProyecto: idProyecto
      }});
  }
  sendCuestionario(idProyecto) {
    return this.http.get(this.api + 'participantesPVC/enviocorreo',{headers: this.headers, params:   {
        idProyecto: idProyecto
      }});
  }
  getProyect(id) {
    return this.http.get(this.api + 'proyectoEVO360/porId', {headers: this.headers, params: {
        'idProyecto': id
      }});

  }
  closeProyect(id) {
    return this.http.get(this.api + 'proyectoEVO360/cierre' , {headers: this.headers , params: {
        'idProyecto': id
      }});
  }

  saveComents(data) {
    return this.http.post(this.api + 'graficas/guardarComentario', data, {headers: this.headers});
  }

  getThemaByProyect(id): Observable<any> {
    return this.http.get(this.api + 'cuestionario/temasEVD', {headers: this.headers , params: {'idProyecto': id}});
  }
  getAnswerbyTema(id): Observable<any> {
    return this.http.get(this.api + 'cuestionario/opciones', {headers: this.headers , params: {'idTema': id}});
  }
  saveAnswer(data): Observable<any> {
    return this.http.post(this.api + 'cuestionario/guardarOpciones', data, {headers: this.headers});
  }
  sendCuestionarios(id): Observable<any> {
    return this.http.get(this.api + 'participantes/cuestionariosSend', {headers: this.headers , params: {'idProyecto': id}});
  }

  getParticipanteByProyect(id): Observable<any>{
    return this.http.get(this.api + 'participantes/participantes', {headers: this.headers , params: {'idProyecto': id}});
  }
  guardarRelacion(data): Observable<any> {
    return this.http.post(this.api + 'proyectoEVO360/guardarEvaluados', data, {headers: this.headers});
  }

  getAsignacion(id): Observable<any> {
    return this.http.get(this.api + 'proyectoEVO360/evaluados', {headers: this.headers , params: {'idProyecto': id}});
  }

  getAreasByProyect(id): Observable<any> {
    return this.http.get(this.api + 'proyectoPVC/' + id + '/areas', {headers: this.headers});
  }

  guardarAreas(data): Observable<any> {
    return this.http.post(this.api + 'proyectoPVC/areas/guardarTodas', data, {headers: this.headers});
  }

  guardarEsferas(id, data): Observable<any> {
    return this.http.post(this.api + 'proyectoPVC/areas/' + id + '/esferas/guardarTodas', data, {headers: this.headers});
  }

  getEsferas(id): Observable<any> {
    return this.http.get(this.api + 'proyectoPVC/areas/' + id + '/esferas', {headers: this.headers});
  }

  guardarNiveles(id, data): Observable<any> {
    return this.http.post(this.api + 'proyectoPVC/esferas/' + id + '/niveles/guardarTodas', data, {headers: this.headers});
  }
  getNiveles(id): Observable<any> {
    return this.http.get(this.api + 'proyectoPVC/esferas/' + id + '/niveles', {headers: this.headers});
  }
  getEspecialidades(id): Observable<any> {
    return this.http.get(this.api + 'proyectoPVC/niveles/' + id + '/especialidades', {headers: this.headers});
  }
  saveEspecialidades(id, data): Observable<any> {
    return this.http.post(this.api + 'proyectoPVC/niveles/' + id + '/especialidades/guardarTodas', data, {headers: this.headers});
  }
  getConocimientos(id) : Observable<any> {
    return this.http.get(this.api + 'proyectoPVC/especialidades/' + id + '/conocimiento', {headers: this.headers} );
  }
  saveConocimientos(id, data, tipo): Observable<any> {
    if (tipo === 1) {
       return this.http.post(this.api + 'proyectoPVC/especialidades/' + id + '/conocimientos/tecnicos/guardarTodas', data, {headers: this.headers} );
    } else {
       return this.http.post(this.api + 'proyectoPVC/especialidades/' + id + '/conocimientos/humanisticos/guardarTodas', data, {headers: this.headers} );
    }
  }
  getToken(token) : Observable<any> {
    return this.http.get(this.api + 'participantesPVC/' + token, { headers: this.headers} );
  }


}
