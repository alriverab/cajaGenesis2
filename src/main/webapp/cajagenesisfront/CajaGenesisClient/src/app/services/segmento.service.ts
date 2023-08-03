import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {Observable, throwError} from 'rxjs';
import {Segmento} from '../interfaces/segmento';
import {catchError} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class SegmentoService {
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization':  'Bearer ' + sessionStorage.getItem('token')
    })
  }
  private apiServer = environment.config.apiUrl ;

  constructor(private httpClient: HttpClient) { }

  getSegmentos(id:number): Observable<Segmento[]> {
    return this.httpClient.get<Segmento[]>(this.apiServer + '/listar-segmentos-tipo-cliente?codigoTipoCliente='+id,this.httpOptions)
      .pipe(
        catchError(this.errorHandler)
      )
  }

  errorHandler(error: { error: { message: string; }; status: any; message: any; }) {
    let errorMessage = '';
    if(error.error instanceof ErrorEvent) {
      // Get client-side error
      errorMessage = error.error.message;
    } else {
      // Get server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }

    return throwError(errorMessage);
  }
}
