import { Injectable } from '@angular/core';
import {environment} from '../../../../environments/environment';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class CatalogsService {

  api = environment.urlApi;
  header = new HttpHeaders();

  constructor(private _http: HttpClient) {
    this.header.append('Content-Type', 'application/json');
  }
  getCountries(): Observable<any> {
    return this._http.get(this.api + 'catalogo/pais', {headers: this.header});
  }
  getGiros(): Observable<any> {
    return this._http.get(this.api + 'catalogo/giro',{headers: this.header});
  }
  getPuestos(): Observable<any> {
    return this._http.get(this.api + 'catalogo/tipoContactoEmpresa');
  }
  getTypeContact(): Observable<any> {
    return this._http.get(this.api + 'catalogo/tipoContacto');
  }
  getScholarship(): Observable<any> {
    return this._http.get(this.api + 'catalogo/empleado/nivelEscolaridad', {headers: this.header});
  }
}
