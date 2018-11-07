import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {environment} from '../../../../environments/environment.prod';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Enterprise} from '../models/enterprise.model';

@Injectable({
  providedIn: 'root'
})
export class EnterprisesService {

  api = environment.urlApi;
  headers = new HttpHeaders();

  constructor(private _http: HttpClient) {

    this.headers.append('Content-Type', 'application/json');

  }

  getEnterprises(): Observable<Enterprise[]> {
    return this._http.get<Enterprise[]>(this.api + 'empresa/todas', {headers: this.headers});
  }

  getEnterpriseByRFC(rfc): Observable<Enterprise[]> {
    return this._http.get<Enterprise[]>(this.api + 'empresa/consultarEmpresaIRHRfc', {headers: this.headers,  params: {'rfc': rfc }});
  }

  getEnterpriseDetail(id): Observable<Enterprise[]> {
    console.log('id: ', id);
    return this._http.get<Enterprise[]>(this.api + 'empresa/consultarEmpresaIRHRfc', {headers: this.headers,  params: {'id': id }});
  }

}
