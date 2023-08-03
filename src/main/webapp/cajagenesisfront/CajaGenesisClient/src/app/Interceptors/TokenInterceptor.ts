import {Injectable} from "@angular/core";
import {HttpHandler, HttpInterceptor,HttpEvent,HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  private excludedUrls: string[] = ['/index.html','/cajagenesis','/cajagenesis/index.html'];

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (this.isExcludedUrl(request.url)) {
      return next.handle(request);
    }
    // Obtener el token del almacenamiento local o de sesión
    const token = localStorage.getItem('token') || sessionStorage.getItem('token');
    // Clonar la solicitud y agregar el token al encabezado "Authorization"
    if (token) {
      console.log("token obtenido: ",token);
      if(token!=null){
        request = request.clone({
          setHeaders: {
            Authorization: `Bearer ${token}`
          }
        });
        console.log("request: ",request);
      }
    }
    return next.handle(request);
  }
  private isExcludedUrl(url: string): boolean { return this.excludedUrls.some((excludedUrl) => url.includes(excludedUrl)); }
}
