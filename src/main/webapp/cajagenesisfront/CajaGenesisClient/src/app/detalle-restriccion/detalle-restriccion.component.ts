import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Regla} from '../models/Regla';
import {TipoCliente} from '../interfaces/tipo-cliente';
import {TipoClienteService} from '../services/tipo-cliente.service';
import {SegmentoService} from '../services/segmento.service';
import {Segmento} from '../interfaces/segmento';
import {TipoCuenta} from '../interfaces/tipo-cuenta';
import {TipoCuentaService} from '../services/tipo-cuenta.service';
import {MonedaService} from '../services/moneda.service';
import {Moneda} from '../interfaces/moneda';
import {Oficina} from '../interfaces/oficina';
import {OficinaService} from '../services/oficina.service';
import {Transaccion} from '../interfaces/transaccion';
import {TransaccionService} from '../services/transaccion.service';
import {Miscelaneo} from '../interfaces/miscelaneo';
import {MiscelaneoService} from '../services/miscelaneo.service';
import {AppComponent} from '../app.component';
import {ReglaService} from '../services/regla.service';
import {CurrencyPipe} from "@angular/common";


@Component({
  selector: 'app-detalle-restriccion',
  templateUrl: './detalle-restriccion.component.html',
  styleUrls: ['./detalle-restriccion.component.css']
})
export class DetalleRestriccionComponent {
  @Input() regla !: Regla;
  @Input() listaRestricciones !: any;
  @Output() objModalPadre = new EventEmitter();
  @Output() fnCerrarModalPadre = new EventEmitter();
  @Output() fnCondifmarRegistro = new EventEmitter();

  showModalMensajePadre:boolean = false;
  modificarCampos:boolean = true;
  limiteAbonoFormat: string | null = '';
  limiteAbonoStr: string | null = '';

  modalTipoMensaje:string = '';
  modalMensaje:string = '';

  listaTiposCliente:TipoCliente[]=[];
  listaSegmentos:Segmento[] = [];
  listaTiposCuenta:TipoCuenta[] = [];
  listaMonedas:Moneda[] = [];
  listaOficinas:Oficina[] = [];
  listaTransacciones:Transaccion[] = [];
  listaMiscelaneos:Miscelaneo[] = [];

  constructor(private tipoClienteServ: TipoClienteService
    ,private segmentoServ: SegmentoService
    ,private tipoCuentaServ: TipoCuentaService
    ,private monedaServ: MonedaService
    ,private ofciniaServ: OficinaService
    ,private transaccionServ: TransaccionService
    ,private miscelaneoServ: MiscelaneoService
    ,private reglaServ: ReglaService
    ,private app :AppComponent
    ,private currencyPipe: CurrencyPipe
  ) { }

  ngOnInit(){

    this.getModificarCambios();

    const promise = new Promise<void>((resolve, reject) => {

      this.app.showSpinner();

      this.tipoClienteServ.getTipoClientes().subscribe((data:any) => {
        this.listaTiposCliente = data;
        let tipoCliente:TipoCliente = this.listaTiposCliente
          .filter(x=>x.glosa==this.regla.tipoDeCliente.toString())[0];
        if(tipoCliente != undefined){
          this.regla.tipoDeCliente = tipoCliente.codigo;

          this.segmentoServ.getSegmentos(tipoCliente.codigo).subscribe((data:any)=>{
            this.listaSegmentos = data;

            let segmento:Segmento = this.listaSegmentos.filter(x=>x.glosa==this.regla.segmento.toString())[0];
            this.regla.segmento = segmento.codigoSegmento;
          });
        }
        else{ this.regla.segmento = -1; }

      });

      this.tipoCuentaServ.getTipoCuentas().subscribe((data:any) => {
        this.listaTiposCuenta = data;
        let tipoCuenta:TipoCuenta = this.listaTiposCuenta.filter(x=>x.glosa==this.regla.tipoDeCuenta.toString())[0];
        if(tipoCuenta != undefined){
          this.regla.tipoDeCuenta = tipoCuenta.glosa.toString();
          this.regla.nombreTipoCuenta = tipoCuenta.token.toString();
        }
      });

      this.monedaServ.getTipoMonedas().subscribe((data:any) => {
        this.listaMonedas = data;
        let moneda:Moneda = this.listaMonedas.filter(x=>x.nombre==this.regla.moneda.toString())[0];
        if(moneda != undefined){
          this.regla.codMoneda = moneda.codMoneda;
          this.regla.moneda = moneda.nombre;

          this.miscelaneoServ.getMiscelaneo(moneda.codMoneda).subscribe((data:any)=>{
            this.listaMiscelaneos = data;
            let miscelaneo:Miscelaneo = this.listaMiscelaneos.filter(x=>x.glosaExtendido== this.regla.miscelaneo)[0];
            this.regla.miscelaneo = miscelaneo.codigoExtendido.toString();
          })
        }
        else{
          this.regla.codMoneda = -1;
          this.regla.miscelaneo = '-1';
        }
      });

      this.transaccionServ.getTransacciones().subscribe((data:any) => {
        this.listaTransacciones = data;
        let transaccion:Transaccion = this.listaTransacciones
          .filter(x=>x.glosaTransaccion==this.regla.transaccion.toString())[0];
        if(transaccion != undefined){
          this.regla.transaccion = transaccion.codigoTransaccion;
        }
      });

      //--- Límite abono
      this.regla.limiteAbonos = this.regla.limiteAbonoValue.toString();
      this.numeroAux = parseFloat(this.regla.limiteAbonos);
      this.regla.limiteAbonos = this.formatearNumero(this.numeroAux);

      this.ofciniaServ.getOficinas().subscribe((data:any) => {
        this.listaOficinas = data;
        let oficina:Oficina = this.listaOficinas.filter(x=>x.nombre==this.regla.oficina.toString())[0];
        if(oficina != undefined){
          this.regla.oficina = oficina.codoficina;
        }

        this.app.hideSpinner();
      });

      resolve();

    });
  }

  private getModificarCambios() {
    if (this.regla.idRegla == 0) {
      this.modificarCampos = true;
    } else {
      this.modificarCampos = false;
    }
  }

  isDecimal(event:any){
    const ex = /^\d{1,8}(\.\d{0,2})?$/;
    let result = ex.test(event.target.value);
    if(!result){
      event.target.value = event.target.value.substring(0,event.target.value.length - 1);
    }
  }

  changeValor(event:any, tipoCampo:string){
    if(tipoCampo == 'miscelaneo'){ this.regla.miscelaneo = event.target.value; }

    if(tipoCampo == 'idTipoCuenta'){
      this.regla.tipoDeCuenta = event.target.value;

    }
    if(tipoCampo == 'nombreTipoCliente'){ this.regla.nombreTipoCliente = event.target.value; }
    if(tipoCampo == 'idTipoCliente'){
      this.regla.tipoDeCliente = event.target.value;
      this.segmentoServ.getSegmentos(event.target.value).subscribe((data:any)=>{
        this.listaSegmentos = data;
      })
    }
    if(tipoCampo == 'idTransaccion'){
      this.regla.transaccion = event.target.value;
    }
    if(tipoCampo == 'idSegmento'){ this.regla.segmento = event.target.value; }
    if(tipoCampo == 'limiteAbono'){
      this.regla.limiteAbonos = event.target.value; }
    if(tipoCampo == 'idEstado'){ this.regla.estado = event.target.value; }
    if(tipoCampo == 'idMoneda'){
      this.regla.codMoneda = event.target.value;
      const nombreMoneda:any = this.listaMonedas.find(x=>x.codMoneda == this.regla.codMoneda)?.nombre;
      this.regla.moneda = nombreMoneda;

      this.miscelaneoServ.getMiscelaneo(this.regla.codMoneda).subscribe((data:any)=>{
        this.listaMiscelaneos = data;
      })
    }
    if(tipoCampo == 'idOficina'){ this.regla.oficina = event.target.value; }
  }

  clickCerrarModal(nombreModal:string){
    if(nombreModal == 'detalle'){ this.fnCerrarModalPadre.emit(); }
  }

  clickConfirmarGuardar(){
    this.fnCondifmarRegistro.emit();
  }

  formatearNumero(num:any){
    const n1 = parseFloat(num); //777888.44;
    const formatted1 = n1.toLocaleString('es-CL');
    return formatted1;
  }

  formatLimiteAbonosClick(event:any){
    event.target.value = this.numeroAux;
  }

  numeroAux:number = 0;
  formatLimiteAbonosBlur(event:any){
    this.numeroAux = event.target.value;
    event.target.value = this.formatearNumero(this.numeroAux);
  }


  clickGuardarRestriccion(){
    this.clickCerrarModal('confirmacion');

    //---- Validar los datos...
    if(this.validaIngresoRestriccion()){
      this.app.showSpinner();

      //---- Registrar los datos
      if(this.regla.idRegla == 0){
        //--- Ingresar nueva restricción
        const promise = new Promise<void>((resolve, reject) => {
          let mis =  (this.listaMiscelaneos.find(data => data.codigoExtendido == parseInt(this.regla.miscelaneo)));
          this.getReglaExtendido(mis);

          //--- Creado Por
          //const correoLog = (localStorage.getItem('correoLog') == null)?'';
          //this.regla.creadoPor = (correoLog ?? undefined)?correoLog:'';
          const localcorreo = localStorage.getItem('correoLog');
          const correoLog = (localcorreo == null) ? '' : localcorreo;
          this.regla.creadoPor = (correoLog ?? undefined) ? correoLog : '';

          //--- Estado regla (por defeto)
          this.regla.estado = 'Deshabilitado';

          this.regla.moneda = this.regla.codMoneda.toString();

          //--- Limpieza limite abonos (parte entera)
          this.regla.limiteAbonos = this.numeroAux.toString();

          this.reglaServ.createRegla(this.regla).subscribe(() => {
              this.app.hideSpinner();
              this.openModalMensaje('ok', 'Restricción ingresada correctamente');
              this.cerrarModalHijo();
              resolve();
            },
            error => {
              this.openModalMensaje('error', 'Error al crear la regla');
            });
        });
      }
      else if(this.regla.idRegla > 0){
        //--- Modifiar una restricción existente
        const promise = new Promise<void>((resolve, reject) => {
          let mis =  (this.listaMiscelaneos.find(data => data.codigoExtendido == parseInt(this.regla.nombreMiscelaneo)));
          if(mis!=undefined){
            this.regla.extendido = mis.glosaCodigoVario;
          }

          this.regla.moneda = this.regla.codMoneda.toString();

          //--- Limpieza limite abonos (parte entera)
          this.regla.limiteAbonos = this.numeroAux.toString();

          this.reglaServ.updateRegla(this.regla).subscribe(() => {
              this.app.hideSpinner();
              this.openModalMensaje('ok', 'Restricción actualizada correctamente');
              this.cerrarModalHijo();
              resolve();
            },
            error => {
              this.openModalMensaje('error', 'Error al actualizar la regla');
            });
        });
      }
    } else {
      this.openModalMensaje('error', 'Todos los campos son requeridos');
    }
  }

  private getReglaExtendido(mis: Miscelaneo | undefined) {
    if (mis != undefined) {
      this.regla.extendido = mis.glosaCodigoVario;
    }
  }

  cerrarModalHijo(){
    //--- Cerrar modal padre desde el hijo
    let btnCerrarModal:any = document.getElementById("btnCerrarModalHijo");
    btnCerrarModal.click();
  }

  openModalMensaje(tipoMensaje:string, mensaje:string){
    const objOpen:any = {
      mensaje:mensaje,
      tipoMensaje:tipoMensaje,
      showModal:true,
    };
    this.objModalPadre.emit(objOpen);
  }

  validaIngresoRestriccion(){
    if(this.regla.tipoDeCliente == 0){
      return false;
    }

    if(this.regla.segmento == 0){
      return false;
    }

    if(this.regla.tipoDeCuenta == ""){
      return false;
    }

    if(this.regla.limiteAbonos.toString() == '' || parseFloat(this.regla.limiteAbonos) < 0){
      return false;
    }

    if(this.regla.codMoneda < 0){
      return false;
    }

    if(this.regla.oficina == 0){
      return false;
    }

    if(this.regla.transaccion == 0){
      return false;
    }
    return  true;
  }

  formatearMoneda(event: any){
    if (event.target != undefined){
      event.target.value = event.target.value.replace('.', '');
      const ex = /^\d{1,8}(\.\d{0,2})?$/;
      let result = ex.test(event.target.value);
      if(!result){
        event.target.value = event.target.value.substring(0,event.target.value.length - 1);
      }else {
        this.limiteAbonoFormat = new Intl.NumberFormat("es-CL").format(event.target.value);
      }
    }
  }

}
