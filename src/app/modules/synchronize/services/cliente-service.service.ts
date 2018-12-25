import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ClienteServiceService {
  headers = new HttpHeaders();
  api = environment.urlApi;
  constructor(private http: HttpClient) {
    this.headers.append('Content-Type', 'application/json');
  }

  saveClient(data): Observable<any> {
    return this.http.post(this.api + 'cliente/agregar', data, {headers: this.headers});
  }
  saveUsuario(data): Observable<any> {
    return this.http.post(this.api + 'usuario/agregar', data, {headers: this.headers});
  }
  saveModules(data): Observable<any> {
    return this.http.post(this.api + 'cliente/modulos/editar', data, {headers: this.headers});
  }

}
