import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { DemoAngularMaterial } from './DemoAngularMaterial';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ForgotpasswordComponent } from './passwordchange/forgotpassword/forgotpassword.component';
import { ResetPasswordComponent } from './passwordchange/reset-password/reset-password.component';
import { BaseChartDirective, provideCharts } from 'ng2-charts';
import { NgxChartsModule } from '@swimlane/ngx-charts';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    ForgotpasswordComponent,
    ResetPasswordComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    DemoAngularMaterial,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BaseChartDirective,
    NgxChartsModule
  ],
  providers: [
    provideClientHydration(),
    provideAnimationsAsync(),
    provideCharts()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
