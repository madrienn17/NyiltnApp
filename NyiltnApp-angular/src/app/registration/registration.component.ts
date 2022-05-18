import { Component, OnInit } from '@angular/core';
import {Registration} from "../_models/registration";
import {RegistrationService} from "../_services/registration.service";
import {MessageService} from "primeng/api";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {
  schools: any;
  form: any = {
    user: null,
    schoolName: null
  };

  eventId: number | null = {} as number;

  registration: Registration = {} as Registration

  constructor(private registrationService: RegistrationService,
              private messageService: MessageService,
              private activatedRoute:ActivatedRoute) { }

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
    // this.schools = [
    //   {label: "Petru Maior Elmeleti Liceum", value: "PMEL"},
    //   {label: "Bolyai Farkas Elmeleti Liceum", value: "BFEL"},
    //   {label: "Lucian Blaga Technologiai Liceum", value: "LBTL"},
    // ];
  }

  onSubmit() {
    this.form.schoolName = this.form.schoolName.label
    const user = {
      firstName: this.form.firstName,
      lastName: this.form.lastName,
      mobileNumber: this.form.mobileNumber,
      email: "string",
      username: this.form.user,
      password: "string",
      role: "string",
      token: "string"
    }

    this.registration = {
      user: user,
      schoolName: this.form.schoolName
    }

    console.log(this.registration)

    this.registrationService.addRegistration(this.registration).subscribe((data: any) => {
      console.log(data)
      if (!data.success) {
        this.messageService.add({severity: 'error', summary: 'Error!', detail: data.message})
      } else {
        this.messageService.add({severity: 'success', summary: 'Success!', detail: "Registration successfully sent"})
      }
    })

  }
}
