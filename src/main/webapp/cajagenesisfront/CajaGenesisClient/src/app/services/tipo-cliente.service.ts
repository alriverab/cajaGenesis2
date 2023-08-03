import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {TipoCliente} from '../interfaces/tipo-cliente';
import {Observable, throwError} from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TipoClienteService {

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization':  'Bearer ' + sessionStorage.getItem('token')
    })
  }
  private apiServer = environment.config.apiUrl ;

  constructor(private httpClient: HttpClient) { }

    getTipoClientes(): Observable<TipoCliente[]> {
      return this.httpClient.get<TipoCliente[]>(this.apiServer + '/listar-tipos-cliente',this.httpOptions)
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
