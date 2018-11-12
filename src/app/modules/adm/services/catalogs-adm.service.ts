import { Injectable } from '@angular/core';
import {environment} from '../../../../environments/environment';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

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
}
