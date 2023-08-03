import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {Observable, throwError} from 'rxjs';
import {Moneda} from '../interfaces/moneda';
import {catchError} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class MonedaService {
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization':  'Bearer ' + sessionStorage.getItem('token')
    })
  }
  private apiServer = environment.config.apiUrl ;

  constructor(private httpClient: HttpClient) { }

  getTipoMonedas(): Observable<Moneda[]> {
    return this.httpClient.get<Moneda[]>(this.apiServer + '/listar-monedas',this.httpOptions)
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
