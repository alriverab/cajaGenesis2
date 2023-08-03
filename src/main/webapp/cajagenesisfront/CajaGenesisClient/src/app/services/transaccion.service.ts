import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from '../../environments/environment';
import {Observable, throwError} from 'rxjs';
import {Transaccion} from '../interfaces/transaccion';
import {catchError} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TransaccionService {
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization':  'Bearer ' + sessionStorage.getItem('token')
    })
  }
  private apiServer = environment.config.apiUrl ;

  constructor(private httpClient: HttpClient) { }

  getTransacciones(): Observable<Transaccion[]> {
    return this.httpClient.get<Transaccion[]>(this.apiServer + '/listar-transacciones',this.httpOptions)
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
