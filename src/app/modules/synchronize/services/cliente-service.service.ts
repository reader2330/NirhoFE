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
    return this.http.post(this.api + 'usuario/guardar/usuario', data, {headers: this.headers});
  }
  saveModules(data): Observable<any> {
    return this.http.post(this.api + 'cliente/modulos/editar', data, {headers: this.headers});
  }
  getUsers(): Observable<any> {
    return this.http.get(this.api + 'usuario/todos', {headers: this.headers});
  }
  getModules(): Observable<any> {
    return this.http.get(this.api + 'cliente/modulos/checked', {headers: this.headers});
  }

  getProyectsTablero(id): Observable<any> {
    console.log(id);
    return this.http.get(this.api + 'proyecto/todos/porConsultor', {headers: this.headers, params: {'idUsuario': id}});
  }

}
