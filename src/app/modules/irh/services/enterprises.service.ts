import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {environment} from '../../../../environments/environment';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Enterprise} from '../models/enterprise.model';
import {Pregunta} from '../../clb/models/pregunta';

@Injectable({
  providedIn: 'root'
})
export class EnterprisesService {

  api = environment.urlApi;
  headers = new HttpHeaders();

  constructor(private http: HttpClient) {

    this.headers.append('Content-Type', 'application/json');

  }

  getEnterprises(): Observable<Enterprise[]> {
    return this.http.get<Enterprise[]>(this.api + 'empresa/todas', {headers: this.headers});
  }

  getEnterpriseByRFC(rfc): Observable<Enterprise[]> {
    return this.http.get<Enterprise[]>(this.api + 'empresa/consultarEmpresaIRHRfc', {headers: this.headers,  params: {'rfc': rfc }});
  }

  getTemas(): Observable<any> {
    return this.http.get(this.api + 'cuestionario/temas/', {headers: this.headers, params: {'idModulo': '1'}});
  }
  getPreguntas(id): Observable<Pregunta[]> {
    return this.http.get<Pregunta[]>(this.api + 'cuestionario/plantilla', {headers: this.headers, params: {'idModulo': '1' }});
  }
  getPreguntasEmpresa(id) :Observable<any>{
    return this.http.get(this.api + 'cuestionarioEmpresaIRH/empresa/'+ id, {headers: this.headers});
  }
  savePreguntas(data): Observable<any> {
    return this.http.post(this.api + 'cuestionarioEmpresaIRH/agregar', data, {headers: this.headers});
  }
  saveEntripise(data): Observable<any>{
    return this.http.post(this.api + 'empresa/registrarEmpresaIRH', data, {headers: this.headers});
  }

}
