import { Component, OnInit } from '@angular/core';
import {RegistrationService} from "../_services/registration.service";
import {RegistrationFull} from "../_models/registration-full";
import {RegistrationTable} from "../_models/registration-table";
import {Timestamp} from "rxjs";

@Component({
  selector: 'app-registration-list',
  templateUrl: './registration-list.component.html',
  styleUrls: ['./registration-list.component.scss']
})
export class RegistrationListComponent implements OnInit {
  registrationList: RegistrationFull[] = []
  registrations: RegistrationTable[] = []
  cols: any[] = [];

  constructor(private registrationService: RegistrationService) { }

  ngOnInit(): void {
    this.registrationService.getRegistrations().subscribe( (data: any) => {
      this.registrationList = data.data
      console.log(this.registrationList)
      this.registrations = this.registrationList.map(item => RegistrationListComponent.convertRegistration(item));
      console.log(this.registrations)
    });


    this.cols = [
      { field: 'name', header: 'Name'},
      { field: 'mobileNumber', header: 'Mobile Number'},
      { field: 'email', header: 'Email'},
      { field: 'date', header: 'Date'},
      { field: 'schoolName', header: 'Provenience'}
      ];

  }

  private static convertRegistration(registrationFull: RegistrationFull) {
    return {
      name: registrationFull.registeredUser.firstName + ' ' + registrationFull.registeredUser.lastName,
      mobileNumber: registrationFull.registeredUser.mobileNumber,
      email: registrationFull.registeredUser.email,
      date: registrationFull.registrationDate,
      schoolName: registrationFull.school.schoolName
    } as RegistrationTable
  }

}
