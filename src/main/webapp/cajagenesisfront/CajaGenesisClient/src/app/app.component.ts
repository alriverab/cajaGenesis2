import {Component, ViewEncapsulation} from '@angular/core';
import {MsalService} from '@azure/msal-angular';
import {AuthenticationResult} from '@azure/msal-browser';
import {perfilamiento} from '../environments/environment';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class AppComponent {

  nombreSistema = 'Sistemas Oficinas';
  loggedId:boolean = false;
  nombreUsuarioLog = 'Juan Luis Guerra';
  perfilLog:string = 'Consultor';
  paginaActual = 'home';
  showModalLoading:boolean = false;
  showModalConfirmacion:boolean = false;
  confirmEvento:string = '';
  confirmMensaje:string = '';
  perfil ={};
  constructor(
    private msalService: MsalService
  ){}

  ngOnInit(){
    this.isLoggedIn();
  }

  isLoggedIn(): boolean {

    const objAccount:any = this.msalService.instance.getActiveAccount();
    if(objAccount != undefined){
      const nombreUsuario:string = objAccount.name;
      const correoUsuario:string = objAccount.username;
      const rolUsuarioAureAD:string = objAccount.idTokenClaims.roles[0];  // siempre el 1er rol del arreglo
      let nombreRolzureAD:string = '';
      if(rolUsuarioAureAD == 'MARE_EJC'){ nombreRolzureAD = 'Consulta'; }
      if(rolUsuarioAureAD == 'MARE_SUP'){ nombreRolzureAD = 'Supervisor'; }
      if(rolUsuarioAureAD == 'MARE_ADM'){ nombreRolzureAD = 'Total'; }

      this.nombreUsuarioLog = nombreUsuario;
      this.perfilLog = nombreRolzureAD; //  Consulta, Supervisor, Total
      localStorage.setItem('perfilLog',this.perfilLog)
      localStorage.setItem('correoLog',correoUsuario)

      let perfiles=perfilamiento.filter(x=>x.idPerfil==this.perfilLog)
      if(perfiles.length>0){
        this.perfil = perfilamiento.filter(x=>x.idPerfil==this.perfilLog)[0];
      }
    }

    this.loggedId = this.msalService.instance.getActiveAccount()!= null;
    return this.loggedId;
  }

  logIn(){
    this.msalService.loginPopup().subscribe((response:AuthenticationResult)=>{
      this.msalService.instance.setActiveAccount(response.account);
      sessionStorage.setItem('token', response.idToken);
    })
  }

  clickCerrarModal(nombreModal:string){
    if(nombreModal == 'confirmacion'){ this.showModalConfirmacion = false; }
  }

  cerrarSesion(){
    this.confirmMensaje = '¿Desea cerrar sesión?';
    this.confirmEvento = 'cerrarSesion';
    this.showModalConfirmacion = true;
  }

  clickConfirmarAceptacion(){
    this.showModalConfirmacion = false;
    if(this.confirmEvento == 'cerrarSesion'){ this.logOut(); }
  }

  logOut(){
    this.msalService.loginPopup().subscribe((response:AuthenticationResult)=>{

        this.msalService.logout()
    })
  }

  clickMenuPagina(pagina:string){
    this.paginaActual = pagina;
  }

  public showSpinner(): void {
    this.showModalLoading = true;
  }

  public hideSpinner(): void {
    this.showModalLoading = false;
  }

}
