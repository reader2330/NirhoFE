import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {environment} from '../../../../environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class LoginEvdService {

  api = environment.urlApi;
  header = new HttpHeaders();

  constructor(private _http: HttpClient) {
    this.header.append('Content-Type', 'application/json');
  }

  sendLogin(params): Observable<any> {
    return this._http.post(this.api + 'usuario/login', params, {headers: this.header});
  }
  getModules(): Observable<any> {
    return this._http.get(this.api + 'usuario/submodulosClb', {headers: this.header});
  }
  getUser(): Observable<any> {
    return this._http.get(this.api + 'empresas/todas', {headers: this.header});
  }
  closeSession(): Observable<any> {
    return this._http.get(this.api + 'usuario/logout', {headers: this.header});
  }
  updateAvatar(ruta): Observable<any> {
    return this._http.get(this.api + '/usuario/guardarAvatar', {headers: this.header, params: {'ruta': ruta}});
  }
}
