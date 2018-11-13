import { Injectable } from '@angular/core';
import {environment} from '../../../../environments/environment';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {laboral_interface} from '../components/labor-adm/labor-adm.component';

@Injectable({
  providedIn: 'root'
})
export class CatalogsAdmService {

  api = environment.urlApi;
  header = new HttpHeaders();

  constructor(private _http: HttpClient) {
    this.header.append('Content-Type', 'application/json');
  }
  getNacionality(): Observable<any> {
    return this._http.get(this.api + 'catalogo/empleado/nacionalidad', {headers: this.header});
  }
  getJob(): Observable<any> {
    return this._http.get(this.api + 'catalogo/empleado/puesto', {headers: this.header});
  }
  getBank(): Observable<any> {
    return this._http.get(this.api + 'catalogo/empleado/banco', {headers: this.header});
  }
  getScholarship(): Observable<any> {
    return this._http.get(this.api + 'catalogo/empleado/nivelEscolaridad', {headers: this.header});
  }
  getLanguage(): Observable<any> {
    return this._http.get(this.api + 'catalogo/idioma', {headers: this.header});
  }
  getTypeContac(): Observable<any> {
    return this._http.get(this.api + 'catalogo/empleado/tipoContacto', {headers: this.header});
  }
  getLevelJob(): Observable<any> {
    return this._http.get(this.api + 'catalogo/empleado/nivelLaboral', {headers: this.header});
  }
  getLevelLanguage(): Observable<any> {
    return this._http.get(this.api + 'catalogo/nivelIdioma', {headers: this.header});
  }
  getLevelHabilities(): Observable<any> {
    return this._http.get(this.api + 'catalogo/nivelConocimiento', {headers: this.header});
  }
  getEmploye(): Observable<laboral_interface[]> {
    return this._http.get<laboral_interface[]>(this.api + 'empleado/todos', {headers: this.header});
  }

}
