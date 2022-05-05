import {Timestamp} from "rxjs";

export interface RegistrationTable {
  name: string,
  mobileNumber: string,
  email: string,
  date: Timestamp<any>,
  schoolName: string
}
