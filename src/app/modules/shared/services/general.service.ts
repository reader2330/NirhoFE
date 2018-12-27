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
  getParticipantebyProyect(id): Observable<any> {
    return this.http.get(this.api + 'participantes/participantes', {headers: this.headers, params: {'idProyecto': id}});
  }
  generarReporteProyect(mod, id)  {
    //return this.http.get(this.api + 'proyecto' + mod +  '/reporte', {headers: this.headers, params: {'idProyecto': id}});
    window.open(this.api + 'proyecto' + mod + '/reporte' + '?idProyecto=' + id);
  }
  generarReporteCompany(id) {
    window.open(this.api + 'empresa/reporte' + '?idEmpresa=' + id);

  }
  generarReporteParticipante(mod, id): Observable<any> {
    return this.http.get(this.api + 'proyecto' + mod +  '/reporte/participante', {headers: this.headers, params: {'idParticipante': id}});
  }
  getEmpresas() : Observable<any> {
    return this.http.get(this.api + 'empresa/todas', {headers: this.headers});
  }



}
