import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProductListComponent } from './product-list/product-list.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {CommonModule} from "@angular/common";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {AuthInterceptor} from "./services/auth.interceptor";
import { RegisterComponent } from './auth/register/register.component';
import {FormsModule} from "@angular/forms";
import { LoginComponent } from './auth/login/login.component';
import { EmailsListComponent } from './product/emails-list/emails-list.component';
import { AnalyticsListComponent } from './product/analytics-list/analytics-list.component';

@NgModule({
  declarations: [
    AppComponent,
    ProductListComponent,
    RegisterComponent,
    LoginComponent,
    EmailsListComponent,
    AnalyticsListComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    CommonModule,
    AppRoutingModule,
    FormsModule,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
