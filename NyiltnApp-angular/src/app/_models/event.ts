import {User} from "./User";
import {Timestamp} from "rxjs";

export interface Event {
  id: number;
  hostUser: User;
  startTime: Timestamp<any>;
  endTime: Timestamp<any>;
  maxAttendeeNr: number;
  link: string;
  presentators: User[];
  location: Location;
}
