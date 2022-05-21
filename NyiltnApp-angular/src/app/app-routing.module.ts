import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {RegisterComponent} from "./register/register.component";
import {LoginComponent} from "./login/login.component";
import {HomeComponent} from "./home/home.component";
import {RegistrationComponent} from "./registration/registration.component";
import {RegistrationListComponent} from "./registration-list/registration-list.component";
import {EventListComponent} from "./event-list/event-list.component";
import {EventNewComponent} from "./event-new/event-new.component";

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'event-list', component: EventListComponent},
  { path: 'event-new', component: EventNewComponent },
  { path: 'registration/:id', component: RegistrationComponent },
  { path: 'registrationlist', component: RegistrationListComponent},
  { path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
