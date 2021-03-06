import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./_components/home/home.component";
import {LoginComponent} from "./_components/login/login.component";
import {RegisterComponent} from "./_components/register/register.component";
import {ResetPasswordComponent} from "./_components/reset-password/reset-password.component";
import {EventListComponent} from "./_components/events/event-list/event-list.component";
import {EventNewComponent} from "./_components/events/event-new/event-new.component";
import {RegistrationComponent} from "./_components/events/registration/registration.component";
import {RegistrationListComponent} from "./_components/events/registration-list/registration-list.component";
import {EventDetailsComponent} from "./_components/events/event-details/event-details.component";
import {EventEditComponent} from "./_components/events/event-edit/event-edit.component";
import {DirtyCheckGuard} from "./_helpers/dirty-check.guard";
import {StatisticsComponent} from "./_components/statistics/statistics.component";


const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'event-list', component: EventListComponent },
  { path: 'event-edit/:id', component: EventEditComponent, canDeactivate: [DirtyCheckGuard]},
  { path: 'event-new/:id', component: EventNewComponent, canDeactivate: [DirtyCheckGuard]},
  { path: 'event-details/:id', component: EventDetailsComponent },
  { path: 'registration/:id', component: RegistrationComponent },
  { path: 'registrationlist/:id', component: RegistrationListComponent },
  { path: 'changePassword', component: ResetPasswordComponent },
  { path: 'statistics', component: StatisticsComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
