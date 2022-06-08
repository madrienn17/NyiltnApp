import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

import {MessageService} from "primeng/api";
import {EventMeta} from "../../../_models/event-meta";
import {EventService} from "../../../_services/event.service";
import {RegistrationService} from "../../../_services/registration.service";
import {TokenStorageService} from "../../../_services/token-storage.service";

@Component({
  selector: 'app-event-list',
  templateUrl: './event-list.component.html',
  styleUrls: ['./event-list.component.scss']
})
export class EventListComponent implements OnInit {
  events: EventMeta[] = [];
  isAdmin: boolean = false;

  eventMetaCreate: boolean = false;
  eventMetaDelete: boolean = false;
  metaIdToDelete: number = 0;
  eventMetaEdit: boolean = false;

  editMetaForm: any = {
    id: 0,
    name: '',
    description: ''
  };

  addEventMetaForm: any = {
    id: null,
    name: '',
    description: ''
  };

  userRegisteredEvents: number[] = [];
  eventDeleteVisible: boolean = false;

  eventIdToDelete: number = 0;

  unsubscribeDialog: boolean = false;
  eventIdToUnsub: number = 0;

  constructor(private eventService: EventService,
              private registrationService: RegistrationService,
              private tokenStorageService: TokenStorageService,
              private router: Router,
              private messageService: MessageService) { }

  ngOnInit(): void {
    this.isAdmin = this.tokenStorageService.getUser().role != undefined ?
      this.tokenStorageService.getUser().role.toUpperCase() === "ADMIN" : false;

    this.load();
  }

  load() {
    this.eventService.getAll().subscribe((e:any) => {
      this.events = e.data;
    })

    this.registrationService.getEventRegisteredByUsername(this.tokenStorageService.getUser().username).subscribe(
      (resp:any)=> {
        if (resp.success) {
          this.userRegisteredEvents = resp.data;
        } else {
          this.messageService.add({severity: 'error', summary: 'Error!', detail: resp.message})
        }
      }, error => {
        this.messageService.add({severity: 'error', summary: 'Error!', detail: error.message})
      })
  }

  handlePlus(eventId: number) {
    this.router.navigate(['/registration', eventId])
  }

  handleMinus() {
    this.registrationService.removeRegistration(this.eventIdToUnsub, this.tokenStorageService.getUsername())
      .subscribe((response:any) => {
        if (response.success) {
          this.messageService.add({severity: 'success', summary: 'Success', detail: "Un-registration successful!"})
          this.closeUnsubDialog();
        } else {
          this.messageService.add({severity: 'error', summary: 'Error', detail: "Un-registration failed!"})
        }
      }, error => {
        this.messageService.add({severity: 'error', summary: 'Error!', detail: error.message})
      });
  }

  getRegisteredBoolean(eventId: number) {
    return this.userRegisteredEvents.includes(eventId)
  }

  addEventMetaButton() {
    console.log(this.addEventMetaForm)
    this.eventService.saveMeta(this.addEventMetaForm).subscribe((resp:any) => {
      console.log(resp)
      if (resp.success) {
        this.messageService.add({severity: 'success', summary: 'Success!', detail: "Event-meta successfully added"})
        this.closeAddEventMeta()
      }
    }, error => {
      this.messageService.add({severity: 'error', summary: 'Error!', detail: error.message})
    })
  }

  onDeleteEventButton() {
    this.eventService.deleteEvent(this.eventIdToDelete).subscribe((response: any) => {
      if (response.success) {
        this.messageService.add({severity: 'success', summary: 'Success', detail: "Event successfully deleted!"})
        this.closeDeleteEvent();
      } else {
        this.messageService.add({severity: 'error', summary: 'Error', detail: "Event was not deleted!"})
      }
    }, error => {
      this.messageService.add({severity: 'error', summary: 'Error!', detail: error.message})
    });
  }

  onDeleteMetaButton() {
    this.eventService.deleteMetaById(this.metaIdToDelete).subscribe((response: any) => {
      if (response.success) {
        this.messageService.add({severity: 'success', summary: 'Success', detail: "Event-meta successfully deleted!"})
        this.closeDeleteMeta();
      } else {
        this.messageService.add({severity: 'error', summary: 'Error', detail: "Event-meta was not deleted!"})
      }
    }, error => {
      this.messageService.add({severity: 'error', summary: 'Error!', detail: error.message})
    });
  }

  onUpdateEventMetaButton() {
    const meta = {
      id: this.editMetaForm.id,
      description: this.editMetaForm.description,
      name: this.editMetaForm.name
    }

    console.log(meta)
    this.eventService.updateMeta(meta.id, meta).subscribe((response: any) => {
      if (response.success) {
        this.messageService.add({severity: 'success', summary: 'Success', detail: "Event-meta successfully updated!"})
        this.closeEditEventMeta();
      } else {
        this.messageService.add({severity: 'error', summary: 'Error', detail: "Event-meta was not updated!"})
      }
    }, error => {
      this.messageService.add({severity: 'error', summary: 'Error!', detail: error.message})
    });
  }

  showAddEventMetaDialog() {
    this.eventMetaCreate = true;
  }

  closeAddEventMeta() {
    this.eventMetaCreate = false
    this.load()
  }

  closeDeleteEvent() {
    this.eventDeleteVisible = false;
    this.load()
  }

  showDeleteEventDialog(id: number) {
    this.eventIdToDelete = id;
    this.eventDeleteVisible = true;
  }

  showDeleteMetaDialog(id: number) {
    this.metaIdToDelete = id;
    this.eventMetaDelete = true;
  }

  closeDeleteMeta() {
    this.eventMetaDelete = false;
    this.load()
  }

  closeEditEventMeta() {
    this.eventMetaEdit = false;
    this.load()
  }


  showEditEventMetaDialog(id: number) {
    this.editMetaForm = this.events.find(x => x.id === id)
    this.eventMetaEdit = true;
  }

  showUnsubDialog(id: number) {
    this.eventIdToUnsub = id;
    this.unsubscribeDialog = true;
  }

  closeUnsubDialog() {
    this.unsubscribeDialog = false;
    this.load()
  }

}
