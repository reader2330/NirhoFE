import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../../../environments/environment';
import {HttpHeaders} from '../../../../../node_modules/@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class LoginService {

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
    return this._http.get(this.api + 'usuario/usuarioEnSesion', {headers: this.header});
  }
  closeSession(): Observable<any> {
    return this._http.get(this.api + 'usuario/logout', {headers: this.header});
  }
  updateAvatar(ruta): Observable<any> {
    return this._http.get(this.api + 'usuario/guardarAvatar', {headers: this.header, params: {'ruta': ruta}});
  }
  getToken() {
    if (localStorage.getItem('token')) {
      return localStorage.getItem('token');
    }
    return false;
  }



}
