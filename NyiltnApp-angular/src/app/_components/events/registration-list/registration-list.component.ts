import { Component, OnInit } from '@angular/core';
import {RegistrationFull} from "../../../_models/registration-full";
import {RegistrationTable} from "../../../_models/registration-table";
import {RegistrationService} from "../../../_services/registration.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-registration-list',
  templateUrl: './registration-list.component.html',
  styleUrls: ['./registration-list.component.scss']
})
export class RegistrationListComponent implements OnInit {
  registrationList: RegistrationFull[] = []
  registrations: RegistrationTable[] = []
  cols: any[] = [];
  eventId: number = 0;

  constructor(private registrationService: RegistrationService,
              private activatedRoute: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(params => {
      // @ts-ignore
      this.eventId = +params.get('id');
    });

    this.registrationService.getRegistrations(this.eventId).subscribe( (data: any) => {
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

  backToList() {
    this.router.navigate(['/event-list'])
  }
}
