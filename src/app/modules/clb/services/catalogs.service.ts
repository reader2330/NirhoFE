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
  getMotivos(): Observable<any> {
    return this._http.get(this.api + 'catalogo/motivoVacante', {headers: this.header});
  }
  getNivelCompetencia(): Observable<any> {
    return this._http.get(this.api + 'catalogo/nivelCompetencia', {headers: this.header});
  }
  getTipoCompetencia(): Observable<any> {
    return this._http.get(this.api + 'catalogo/tipoCompetencia', { headers: this.header});
  }
  getNacionalidades(): Observable<any> {
    return this._http.get(this.api + 'catalogo/empleado/nacionalidad', { headers: this.header})
  }
  getTypeContactEmpleado(): Observable<any> {
    return this._http.get(this.api + 'catalogo/empleado/tipoContacto', { headers: this.header})
  }
  getHabilidades(): Observable<any> {
    return this._http.get(this.api + 'catalogo/empleado/habilidades', {headers: this.header});
  }
  getIdiomas(): Observable<any> {
    return this._http.get(this.api + 'catalogo/idioma', {headers: this.header});
  }
  getHabilidadesIdioma(): Observable<any> {
    return this._http.get(this.api + 'catalogo/nivelConocimiento', {headers: this.header});
  }
  getNivelesIdioma(): Observable<any> {
    return this._http.get(this.api + 'catalogo/empleado/nivelIdioma', {headers: this.header});
  }

}

