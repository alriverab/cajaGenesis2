export const environment = {
  production: false,
  config:{
    apiUrl:'https://lablnx582:6004/cajagenesis/api/v1.0/service'
  },
  azureConfig:{
    //---- DEV
    clientId:'28b44ea5-cb93-44a9-9918-c5dad01d3c68',
    tenantId:'11841e97-4eec-4c4f-9a94-dffb32729af6',

    redirectUri:'https://lablnx582:6004/cajagenesis/api/index.html',
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



