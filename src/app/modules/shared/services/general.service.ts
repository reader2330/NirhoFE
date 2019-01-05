import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {environment} from '../../../../environments/environment';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GeneralService {
  headers = new HttpHeaders();
  api = environment.urlApi;
  constructor(private http: HttpClient) {
    this.headers.append('Content-Type', 'application/json');
  }
  getProyectsByModule(mod): Observable<any> {
   return  this.http.get(this.api + 'proyecto' + mod + '/todos', {headers: this.headers });
  }
  getParticipantebyProyect(id , mod?): Observable<any> {
    if (mod) {
      return this.http.get(this.api + 'participantes' + mod + '/participantes', {headers: this.headers, params: {'idProyecto': id}});
    }
    return this.http.get(this.api + 'participantes/participantes', {headers: this.headers, params: {'idProyecto': id}});
  }

  generarReporteProyect(mod, id)  {
    window.open(this.api + 'proyecto' + mod + '/reporte' + '?idProyecto=' + id);
  }
  generarReporteCompany(id) {
    window.open(this.api + 'empresa/reporte' + '?idEmpresa=' + id);

  }
  generarReporteParticipante(mod, idParticipante , id? ) {
    if (!id) {
      window.open(this.api + 'proyecto' + mod +  '/reporte/participante' + '?idParticipante=' + idParticipante);
    } else {
      window.open(this.api + 'proyecto' + mod +  '/reporte/participante' + '?idProyecto=' + id + '&idParticipante=' + idParticipante);
    }

  }
  getEmpresas(): Observable<any> {
    return this.http.get(this.api + 'empresa/todas', {headers: this.headers});
  }

  generarReporteParticipantePVC(id) {
    window.open(this.api + 'participantesPVC' +  '/reporte/participante' + '?idParticipante=' + id);
  }
  getCandidatos(): Observable<any>{
    return this.http.get(this.api + 'candidato/todos', {headers:this.headers});
  }
  generarReporteCandidato(id) {
    window.open(this.api + 'candidato' +  '/reporte/participante' + '?idCandidato=' + id);
  }





}
