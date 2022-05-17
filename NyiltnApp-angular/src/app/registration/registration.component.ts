import { Component, OnInit } from '@angular/core';
import {Registration} from "../_models/registration";
import {RegistrationService} from "../_services/registration.service";
import {MessageService} from "primeng/api";

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

  registration: Registration = {} as Registration

  constructor(private registrationService: RegistrationService, private messageService: MessageService) { }

  ngOnInit(): void {
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
