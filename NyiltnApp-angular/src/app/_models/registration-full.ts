import {User} from "./User";
import {Timestamp} from "rxjs";
import {School} from "./school";

export interface RegistrationFull {
  events: any[];
  registeredUser: User;
  registrationDate: Timestamp<any>;
  school: School
}
