import {LOCALE_ID, NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {MSAL_INSTANCE, MsalModule, MsalService} from "@azure/msal-angular";
import {IPublicClientApplication, PublicClientApplication} from "@azure/msal-browser";
import { MantenedorReglasComponent } from './mantenedor-reglas/mantenedor-reglas.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { DetalleRestriccionComponent } from './detalle-restriccion/detalle-restriccion.component';
import {FormsModule} from "@angular/forms";
import { HttpClientModule,HTTP_INTERCEPTORS } from "@angular/common/http";
import {environment} from "../environments/environment";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgxSpinnerModule } from 'ngx-spinner';
import {CommonModule, registerLocaleData, CurrencyPipe} from "@angular/common";
import localeEs from '@angular/common/locales/es';
import {TokenInterceptor} from "./Interceptors/TokenInterceptor";

export function MSALInstanceFactory():IPublicClientApplication {
  return new PublicClientApplication({
    auth:{
      clientId: environment.azureConfig.clientId,
      authority: 'https://login.microsoftonline.com/'+environment.azureConfig.tenantId,
      redirectUri:environment.azureConfig.redirectUri
    }
  });
}

registerLocaleData(localeEs);

// @ts-ignore
// @ts-ignore
// @ts-ignore
@NgModule({
  declarations: [
    AppComponent,
    MantenedorReglasComponent,
    LoginComponent,
    HomeComponent,
    DetalleRestriccionComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        MsalModule,
        FormsModule,
        HttpClientModule,
      BrowserAnimationsModule,
      NgxSpinnerModule.forRoot({ type: 'ball-scale-multiple' }),
      CommonModule
    ],
  providers: [

    {
      provide: LOCALE_ID, useValue: 'es'
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass:TokenInterceptor,
      multi:true
    },
    {
      provide: MSAL_INSTANCE,
      useFactory:MSALInstanceFactory,
    },
    MsalService,
    CurrencyPipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
