import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./_components/home/home.component";
import {LoginComponent} from "./_components/login/login.component";
import {RegisterComponent} from "./_components/register/register.component";
import {EventListComponent} from "./_components/events/event-list/event-list.component";
import {EventNewComponent} from "./_components/events/event-new/event-new.component";
import {RegistrationComponent} from "./_components/events/registration/registration.component";
import {RegistrationListComponent} from "./_components/events/registration-list/registration-list.component";
import {EventDetailsComponent} from "./_components/events/event-details/event-details.component";


const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'event-list', component: EventListComponent},
  { path: 'event-new/:id', component: EventNewComponent },
  { path: 'event-details/:id', component: EventDetailsComponent },
  { path: 'registration/:id', component: RegistrationComponent },
  { path: 'registrationlist', component: RegistrationListComponent},
  { path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
