import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {HomeComponent} from './home/home.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {authInterceptorProviders} from "./_helpers/auth.interceptor";
import {RegistrationComponent} from './registration/registration.component';
import {DropdownModule} from "primeng/dropdown";
import {VirtualScrollerModule} from "primeng/virtualscroller";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {TreeTableModule} from "primeng/treetable";
import {MultiSelectModule} from "primeng/multiselect";
import {TableModule} from "primeng/table";
import {InputTextModule} from "primeng/inputtext";
import {ButtonModule} from "primeng/button";
import {DialogModule} from "primeng/dialog";
import {RippleModule} from "primeng/ripple";
import {CalendarModule} from "primeng/calendar";
import {ToolbarModule} from "primeng/toolbar";
import {AutoCompleteModule} from "primeng/autocomplete";
import {ToastModule} from "primeng/toast";
import {InputTextareaModule} from "primeng/inputtextarea";
import {FileUploadModule} from "primeng/fileupload";
import {MessagesModule} from "primeng/messages";
import {MessageModule} from "primeng/message";
import {TooltipModule} from "primeng/tooltip";
import {ConfirmDialogModule} from "primeng/confirmdialog";
import {MenuModule} from "primeng/menu";
import {PanelModule} from "primeng/panel";
import {MessageService} from "primeng/api";
import { RegistrationListComponent } from './registration-list/registration-list.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    RegistrationComponent,
    RegistrationListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    DropdownModule,
    VirtualScrollerModule,
    BrowserAnimationsModule,
    TreeTableModule,
    InputTextModule,
    TableModule,
    MultiSelectModule,
    FormsModule,
    DropdownModule,
    ButtonModule,
    RippleModule,
    DialogModule,
    ToolbarModule,
    AutoCompleteModule,
    CalendarModule,
    ToastModule,
    ReactiveFormsModule,
    InputTextareaModule,
    MessageModule,
    MessagesModule,
    FileUploadModule,
    TooltipModule,
    ConfirmDialogModule,
    MenuModule,
    PanelModule,
  ],
  providers: [
    authInterceptorProviders,
    MessageService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
