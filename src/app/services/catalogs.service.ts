import { Injectable } from '@angular/core';
import {environment} from '../../environments/environment.prod';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class CatalogsService {

  api = 'http://localhost:8080/Nirho/';
  header = new HttpHeaders();
  constructor(private _http: HttpClient) {
  }
  getCountries(): Observable<any> {
    this.header.append('Content-Type', 'application/json');
    return this._http.get(this.api + 'catalogo/pais', {headers: this.header});
  }
  getGiros(): Observable<any> {
    return this._http.get(this.api + 'catalogo/giro');
  }
  getPuestos(): Observable<any> {
    return this._http.get(this.api + 'catalogo/tipoContactoEmpresa');
  }
  getTypeContact(): Observable<any>{
    return this._http.get(this.api + 'catalogo/tipoContacto');
  }
}
