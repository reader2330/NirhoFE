import { Injectable } from '@angular/core';
import {environment} from '../../environments/environment.prod';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class CatalogsService {

  api = environment.urlApi;
  constructor(private _http: HttpClient) { }
  getCountries(): Observable<any> {
    return this._http.get(this.api + 'catalogo/pais');
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
