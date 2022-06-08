import { Component, OnInit } from '@angular/core';
import {MessageService} from "primeng/api";
import {ActivatedRoute, Router} from "@angular/router";
import {Registration} from "../../../_models/registration";
import {RegistrationService} from "../../../_services/registration.service";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {
  schools: any;
  form: any = {
    schoolName: null
  };

  eventId: number = {} as number;

  registration: Registration = {} as Registration

  constructor(private registrationService: RegistrationService,
              private messageService: MessageService,
              private activatedRoute:ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(params => {
      // @ts-ignore
      this.eventId = +params.get('id');
      console.log(this.eventId)
    });
    this.registrationService.getSchoolNames().subscribe((response: any) => {
      console.log(response)
      this.schools = response.data;
    });
  }

  onSubmit() {
    this.form.schoolName = this.form.schoolName.label
    const user:string = window.sessionStorage.getItem('auth-user') as unknown as string

    this.registration = {
      user: JSON.parse(user),
      schoolName: this.form.schoolName,
      eventId: this.eventId
    }

    console.log(this.registration)

    this.registrationService.addRegistration(this.registration).subscribe((response:any) => {
      console.log(response)
      if (!response.success) {
        this.messageService.add({severity: 'error', summary: 'Error!', detail: response.message})
      } else {
        this.messageService.add({severity: 'success', summary: 'Success!', detail: "Registration successfully sent"})
        this.router.navigate(['event-list'])
      }
    }, error => {
      this.messageService.add({severity: 'error', summary: 'Error!', detail: error.message})
    })

  }
}
