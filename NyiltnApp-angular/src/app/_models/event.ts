import {User} from "./User";
import {Timestamp} from "rxjs";
import {Location} from "./location";

export interface Event {
  id: number;
  hostUser: string;
  eventMeta: number;
  startTime: Timestamp<any>;
  endTime: Timestamp<any>;
  maxAttendeeNr: number;
  registeredNr: number;
  link: string;
  location: Location;
}
