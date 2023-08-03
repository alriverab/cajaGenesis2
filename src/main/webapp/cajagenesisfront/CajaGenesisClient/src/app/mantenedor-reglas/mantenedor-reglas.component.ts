import {Component, Input, ViewChild} from '@angular/core';
import {AppComponent} from '../app.component';
import {Regla} from '../models/Regla';
import {DetalleRestriccionComponent} from '../detalle-restriccion/detalle-restriccion.component';
import {ReglaService} from '../services/regla.service';
import {RegReglas} from '../interfaces/reg-reglas';
import {fnObtenerPrivilegiosPerfil} from '../funciones/funciones';
import {formatNumber} from "@angular/common";
import * as saveAs from 'file-saver';

@Component({
  selector: 'app-mantenedor-reglas',
  templateUrl: './mantenedor-reglas.component.html',
  styleUrls: ['./mantenedor-reglas.component.css']
})

export class MantenedorReglasComponent {

  @Input() parentApi !: any;
  @ViewChild(DetalleRestriccionComponent) myChild: DetalleRestriccionComponent | undefined;

  confirmMensaje: string = '';
  confirmEvento: string = '';
  confirmFuncDesp: any;
  estadoNuevoConfirmado: string = '';
  showModalDetalle: boolean = false;
  showModalConfirmacion: boolean = false;
  showModalMensaje: boolean | undefined = false;
  modalTipoMensaje: string = '';
  modalMensaje: string = '';
  listaRestricciones: Regla[] = [];
  objRestriccionSeleccionada: Regla = new Regla();
  idRestriccionSeleccionada: number = 0;
  tituloModal: string = '';
  permisos: any = fnObtenerPrivilegiosPerfil();
  correoLog = localStorage.getItem('correoLog');

  // pager object
  pager: any = {};
  // paged items
  pagedItems: any[] = [];

  constructor(private reglasServices: ReglaService
    , private app: AppComponent
  ) {
  }

  ngOnInit() {
    this.showModalMensaje = this.myChild?.showModalMensajePadre;

    if (this.permisos.consultar) {
      this.cargarGrilla();
    } else {
    }
  }

  onDownload() {
    if (this.listaRestricciones.length > 0) {
      let csvData = this.convertToCSV(this.listaRestricciones);
      let blob = new Blob(['\ufeff' + csvData], {type: 'text/csv;charset=utf-8;'});
      let dwldLink = window.URL.createObjectURL(blob);
      let filename = 'mantenedor_reglas.csv';
      saveAs(dwldLink, filename);
    }
  }


  convertToCSV(objArray: any[]) {
    let array = typeof objArray != 'object' ? JSON.parse(objArray) : objArray;
    let str = '';
    let headers = ["Estado", "Tipo Cliente", "Segmento", "Tipo Cuenta", "Límite Abonos", "Moneda", "Transacción", "Misceláneo", "Oficina"];
    let headerLine = headers.join(',');
    str += headerLine + '\r\n';

    for (let i = 0; i < array.length; i++) {
      let line = '';
      line += array[i]['estado'] + ',';
      line += array[i]['tipoDeCliente'] + ',';
      line += array[i]['segmento'] + ',';
      line += array[i]['tipoDeCuenta'] + ',';
      line += array[i]['limiteAbonos'] + ',';
      line += array[i]['moneda'] + ',';
      line += array[i]['transaccion'] + ',';
      line += array[i]['miscelaneo'] + ',';
      line += array[i]['oficina'];
      str += line + '\r\n';
    }
    return str;
  }


  cargarGrilla() {
    this.app.showSpinner();
    this.reglasServices.getReglas().subscribe((data: RegReglas) => {
      this.listaRestricciones = data.reglas;

      // initialize to page 1
      this.setPage(1);
      //--- Colocar "nombre" de los campos
      console.log('this.listaRestricciones: ', this.listaRestricciones);
      for (let i = 0; i < this.listaRestricciones.length; i++) {
        this.listaRestricciones[i].nombreEstado = this.listaRestricciones[i].estado?.toString();
        this.listaRestricciones[i].nombreOficina = this.listaRestricciones[i].codOficina?.toString() + ' - ' + this.listaRestricciones[i].oficina?.toString();
        this.listaRestricciones[i].nombreTipoCliente = this.listaRestricciones[i].tipoDeCliente?.toString();
        this.listaRestricciones[i].nombreTipoCuenta = this.listaRestricciones[i].nombreTipoCuenta?.toString();
        this.listaRestricciones[i].nombreSegmento = this.listaRestricciones[i].segmento?.toString();
        this.listaRestricciones[i].nombreTransaccion = this.listaRestricciones[i].transaccion?.toString();
        this.listaRestricciones[i].nombreMiscelaneo = this.listaRestricciones[i].miscelaneo?.toString();
        this.listaRestricciones[i].codMoneda = this.listaRestricciones[i].codMoneda;
        this.listaRestricciones[i].moneda = this.listaRestricciones[i].moneda?.toString();
        this.listaRestricciones[i].nombreMoneda = this.listaRestricciones[i].moneda.toString();
        this.listaRestricciones[i].limiteAbonoValue = parseFloat(this.listaRestricciones[i].limiteAbonos);
      }

      this.app.hideSpinner();
    })
  }

  //--- Ordenar tabla (click)
  sortTable(n: number) {

    //---- V2
    let table: any, rows: any, switching: any, i: any, x: any, y: any, shouldSwitch: any, dir: any,
      switchcount: number = 0;
    table = document.getElementById("tblReglas");
    switching = true;
    // Set the sorting direction to ascending:
    dir = 'asc';
    // Make a loop that will continue until no switching has been done:
    while (switching) {
      // Start by saying: no switching is done:
      switching = false;
      rows = table.rows;
      // Loop through all table rows (except the first, which contains table headers):
      for (i = 1; i < (rows.length - 1); i++) {
        // Start by saying there should be no switching:
        shouldSwitch = false;
        //Get the two elements you want to compare, one from current row and one from the next:
        x = rows[i].getElementsByTagName("TD")[n];
        y = rows[i + 1].getElementsByTagName("TD")[n];
        // Check if the two rows should switch place, based on the direction, asc or desc:
        if (dir == "asc") {
          if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
            // If so, mark as a switch and break the loop:
            shouldSwitch = true;
            break;
          }
        } else if (dir == "desc") {
          if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
            // If so, mark as a switch and break the loop:
            shouldSwitch = true;
            break;
          }
        }
      }
      if (shouldSwitch) {
        // If a switch has been marked, make the switch and mark that a switch has been done:
        rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
        switching = true;
        // Each time a switch is done, increase this count by 1:
        switchcount++;
      } else {
        //If no switching has been done AND the direction is 'asc',
        // set the direction to "desc" and run the while loop again.
        if (switchcount == 0 && dir == 'asc') {
          dir = 'desc';
          switching = true;
        }
      }
    }

  }

  setPage(page: number) {
    if (page < 1 || page > this.pager.totalPages) {
      return;
    }

    // get pager object from service
    this.pager = this.getPager(this.listaRestricciones.length, page);

    // get current page of items
    this.pagedItems = this.listaRestricciones.slice(this.pager.startIndex, this.pager.endIndex + 1);
  }

  getPager(totalItems: number, currentPage: number = 1, pageSize: number = 10) {
    // calculate total pages
    let totalPages = Math.ceil(totalItems / pageSize);

    let startPage: number, endPage: number;

    if (totalPages <= 5) {
      startPage = 1;
      endPage = totalPages;
    } else {
      if (currentPage <= 3) {
        startPage = 1;
        endPage = 5;
      } else if (currentPage + 1 >= totalPages) {
        startPage = totalPages - 4;
        endPage = totalPages;
      } else {
        startPage = currentPage - 2;
        endPage = currentPage + 2;
      }
    }

    // calculate start and end item indexes
    let startIndex = (currentPage - 1) * pageSize;
    let endIndex = Math.min(startIndex + pageSize - 1, totalItems - 1);

    // create an array of pages to ng-repeat in the pager control
    //let pages = _.range(startPage, endPage + 1);
    let pages: any = [];
    for (let i = startPage; i <= endPage; i++) {
      pages.push(i);
    }

    // return object with all pager properties required by the view
    const objPager: any = {
      totalItems: totalItems,
      currentPage: currentPage,
      pageSize: pageSize,
      totalPages: totalPages,
      startPage: startPage,
      endPage: endPage,
      startIndex: startIndex,
      endIndex: endIndex,
      pages: pages
    };

    return objPager;
  }

  salir() {
    this.confirmMensaje = '¿Desea salir de la consulta?';
    this.confirmEvento = 'salir';
    this.showModalConfirmacion = true;
  }

  obtenerReglaDesdeId(idRegla: number) {
    const objRegl: any = this.listaRestricciones.find(x => x.idRegla == idRegla);
    if (objRegl == undefined || objRegl == null) {
      this.objRestriccionSeleccionada = new Regla();
    } else {
      this.objRestriccionSeleccionada = objRegl;
    }
  }

  clickNuevaRestriccion() {
    this.showModalDetalle = true;
    this.tituloModal = 'Agregar Regla';
    this.objRestriccionSeleccionada = new Regla();
  }

  clickEditarRestriccion(idRes: number) {
    this.tituloModal = 'Modificar Regla';
    this.obtenerReglaDesdeId(idRes);
    this.showModalDetalle = true;
  }

  clickEliminarRestriccion(id: number) {
    this.confirmMensaje = '¿Desea eliminar este registro?';
    this.idRestriccionSeleccionada = id;
    this.confirmEvento = 'eliminar';
    this.showModalConfirmacion = true;
  }

  clickCerrarModal(nombreModal: string) {
    if (nombreModal == 'detalle') {
      this.showModalDetalle = false;
    }
    if (nombreModal == 'confirmacion') {
      this.showModalConfirmacion = false;
    }
  }

  clickCerrarModalMensaje() {
    this.showModalMensaje = false;
  }

  clickConfirmarAceptacion() {
    if (this.confirmEvento == 'salir') {
      this.parentApi.paginaActual = 'home';
    } else if (this.confirmEvento == 'guardar') {
      this.myChild?.clickGuardarRestriccion();
    } else if (this.confirmEvento == 'eliminar') {
      this.eliminarRestriccion(this.idRestriccionSeleccionada);
    } else if (this.confirmEvento == 'activarDesactivar') {
      this.objRestriccionSeleccionada.estado = this.estadoNuevoConfirmado;
      this.activarDesactivarRegla(this.objRestriccionSeleccionada);
    }

    this.showModalConfirmacion = false;
  }

  eliminarRestriccion(idRestriccion: number) {
    const promise = new Promise<void>((resolve, reject) => {

      this.reglasServices.deleteRegla(idRestriccion).subscribe(() => {
        this.app.hideSpinner();
        this.openModalMensaje('ok', 'Restricción #' + idRestriccion + ' eliminada correctamente');

        resolve();
      });
    });
  }

  obtenerDataDesdeHijo(event: any) {
    this.openModalMensaje(event.tipoMensaje, event.mensaje);
  }

  openModalMensaje(tipoMensaje: string, mensaje: string) {
    let tipoIcon: string = '';
    if (tipoMensaje == 'ok') {
      tipoIcon = '<i class="bi bi-check-circle-fill icon-color-ok"></i>';
    }
    if (tipoMensaje == 'info') {
      tipoIcon = '<i class="bi bi-info-circle-fill icon-color-info"></i>';
    }
    if (tipoMensaje == 'alert') {
      tipoIcon = '<i class="bi bi-exclamation-triangle-fill icon-color-alert"></i>';
    }
    if (tipoMensaje == 'error') {
      tipoIcon = '<i class="bi bi-x-circle-fill icon-color-error"></i>';
    }

    this.showModalMensaje = true;
    this.modalMensaje = mensaje;
    this.modalTipoMensaje = tipoIcon;

    if (tipoMensaje == 'ok') {
      //--- Actualizar grilla
      this.cargarGrilla();
    }
  }

  clickConfirmGuardar() {
    this.confirmMensaje = '¿Desea guardar el registro?';
    this.confirmEvento = "guardar";
    this.showModalConfirmacion = true;
  }

  clickCambioEstadoRestriccion(idRegla: number, estadoNue: string) {

    this.obtenerReglaDesdeId(idRegla);
    this.activarDesaactivarConfirm(estadoNue);
  }

  activarDesaactivarConfirm(estado: string) {
    let mjeActDesAct: string = '';
    if (estado === 'Habilitado') {
      mjeActDesAct = 'deshabilitar';
      this.estadoNuevoConfirmado = 'Deshabilitado';
    } else if (estado === 'Deshabilitado') {
      mjeActDesAct = 'habilitar';
      this.estadoNuevoConfirmado = 'Habilitado';
    }

    this.confirmMensaje = '¿Desea ' + mjeActDesAct + ' esta restricción?';
    this.confirmEvento = 'activarDesactivar';
    this.showModalConfirmacion = true;
    this.idRestriccionSeleccionada = this.objRestriccionSeleccionada.idRegla;
  }


  activarDesactivarRegla(objRegla: any) {
    const promise = new Promise<void>((resolve, reject) => {

      this.reglasServices.desactivarRegla(objRegla.idRegla, objRegla.estado).subscribe(() => {
        this.app.hideSpinner();
        this.openModalMensaje('ok', 'Restricción cambio estado correctamente correctamente');

        resolve();
      });
    });
  }

  protected readonly formatNumber = formatNumber;
}
