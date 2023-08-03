import {perfilamiento} from "../../environments/environment.dev";

/**
 * Función para obtener los privilegios según perfil logeado
 */
export function fnObtenerPrivilegiosPerfil(){
  const perfilLog:any = localStorage.getItem('perfilLog');
  let perfilLogStr:string = '';
  if(perfilLog != undefined){ perfilLogStr = perfilLog.toString(); }
  let objPriv:any = undefined;
  objPriv = perfilamiento.find(x=>x.nombrePerfil == perfilLogStr);
  if(objPriv != undefined){ objPriv = objPriv.permisos; }
  return objPriv;
}

export function funcionPrueba(){
  return '';
}
