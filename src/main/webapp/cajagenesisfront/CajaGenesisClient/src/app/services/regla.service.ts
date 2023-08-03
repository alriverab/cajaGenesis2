import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {Observable, throwError} from 'rxjs';
import {Regla} from '../models/Regla';
import {catchError} from 'rxjs/operators';
import {ResponseDTO} from '../interfaces/response-dto';
import {ConsultaCaja} from '../interfaces/consulta-caja';
import {RegReglas} from '../interfaces/reg-reglas';

@Injectable({
  providedIn: 'root'
})
export class ReglaService {
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + sessionStorage.getItem('token')
    })
  }


  private apiServer = environment.config.apiUrl;

  constructor(private httpClient: HttpClient) {

  }

  createRegla(reglaS: Regla): Observable<Regla> {

    const regla = {
      "idRegla": reglaS.idRegla,
      "tipoDeCliente": reglaS.tipoDeCliente,
      "segmento": reglaS.segmento,
      "estado": reglaS.estado,
      "tipoDeCuenta": reglaS.tipoDeCuenta,
      "moneda": reglaS.moneda,
      "transaccion": reglaS.transaccion,
      "miscelaneo": reglaS.miscelaneo,
      "oficina": reglaS.oficina,
      "limiteAbonos": reglaS.limiteAbonos,
      "extendido": reglaS.extendido,
      "creadoPor": reglaS.creadoPor,
    };

    return this.httpClient.post<Regla>(this.apiServer + '/insertar-regla', regla ,this.httpOptions)
      .pipe(
        catchError(this.errorHandler)
      )
  }

  getTokenAuth(): Observable<string> {
    return this.httpClient.get<string>(this.apiServer + '/login')
      .pipe(
        catchError(this.errorHandler)
      )
  }

  getReglas(): Observable<RegReglas> {
    return this.httpClient.get<RegReglas>(this.apiServer + '/listar-reglas/', this.httpOptions)
      .pipe(
        catchError(this.errorHandler)
      )
  }

  downloadCsv() {
    let httpOp = new HttpHeaders({
      'Authorization': 'Bearer ' + sessionStorage.getItem('token'),
      'Content-Type': 'application/json'
    });


    
    return this.httpClient.get(this.apiServer + '/reglas/csv', { 
      responseType: 'blob',
      headers: httpOp
    }).pipe(
      catchError(this.errorHandler)
    );
  }

  getConsularCaja(): Observable<ConsultaCaja> {
    return this.httpClient.get<ConsultaCaja>(this.apiServer + '/listar-consulta-caja/', this.httpOptions)
      .pipe(
        catchError(this.errorHandler)
      )
  }

  updateRegla(reglaU: Regla): Observable<ResponseDTO> {
    const regla = {
      "idRegla": reglaU.idRegla,
      "tipoDeCliente": reglaU.tipoDeCliente,
      "segmento": reglaU.segmento,
      "estado": reglaU.estado,
      "tipoDeCuenta": reglaU.tipoDeCuenta,
      "moneda": reglaU.moneda,
      "transaccion": reglaU.transaccion,
      "miscelaneo": reglaU.miscelaneo,
      "oficina": reglaU.oficina,
      "limiteAbonos": reglaU.limiteAbonos,
      "extendido": reglaU.extendido,
      "creadoPor": reglaU.creadoPor,
    };

    return this.httpClient.put<ResponseDTO>(this.apiServer + '/update-regla', regla, this.httpOptions)
      .pipe(
        catchError(this.errorHandler)
      )
  }

  desactivarRegla(id: number, estado: string): Observable<ResponseDTO> {
    return this.httpClient.put<ResponseDTO>(
      this.apiServer + '/desactivar-regla?idRegla=' + id + '&estado=' + estado, this.httpOptions)
      .pipe(
        catchError(this.errorHandler)
      )
  }

  deleteRegla(idRegla: number): Observable<ResponseDTO> {
    return this.httpClient.delete<ResponseDTO>(this.apiServer + '/eliminar-regla?idRegla=' + idRegla,this.httpOptions)
      .pipe(
        catchError(this.errorHandler)
      )
  }

  errorHandler(error: { error: { message: string; }; status: any; message: any; }) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // Get client-side error
      errorMessage = error.error.message;
    } else {
      // Get server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }

    return throwError(errorMessage);
  }
}
