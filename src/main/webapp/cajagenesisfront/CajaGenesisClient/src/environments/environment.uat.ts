export const environment = {
  production: true,
  config:{
    apiUrl:'http://10.205.56.67:7001/cajagenesis/api'
  },
  azureConfig:{
    clientId:'22af08b7-825b-4efb-a1cc-df2fe294be8b',
    redirectUri:'http://10.205.56.67:7001/cajagenesis/api/index.html',
  }
};
export const perfilamiento= [
  {
    idPerfil: 'MARE_EJC',
    nombrePerfil:'Consulta',
    permisos:{editar:0,agregar:0,eliminar:0,consultar:1,salir:1,habilitar:0,desabilitar:0,estadoInsert:'',cambioReglaCreadoPorusaurio:0},
  },
  {
    idPerfil: 'MARE_SUP',
    nombrePerfil:'Supervisor',
    permisos:{editar:1,agregar:1,eliminar:1,consultar:1,salir:1,habilitar:0,desabilitar:0,estadoInsert:'Desabilitadas',cambioReglaCreadoPorusaurio:1},
  },
  {
    idPerfil: 'MARE_ADM',
    nombrePerfil:'Total',
    permisos:{editar:1,agregar:1,eliminar:1,consultar:1,salir:1,habilitar:1,desabilitar:1,estadoInsert:'Habilitado',cambioReglaCreadoPorusaurio:0},
  },
];
