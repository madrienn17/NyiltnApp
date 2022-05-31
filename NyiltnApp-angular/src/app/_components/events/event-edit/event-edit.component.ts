import { Component, OnInit } from '@angular/core';
import {EventService} from "../../../_services/event.service";
import {ActivatedRoute, Router} from "@angular/router";
import {ConfirmationService, MessageService} from "primeng/api";
import {ComponentCanDeactivate} from "../../../_models/component-can-deactivate";

@Component({
  selector: 'app-event-edit',
  templateUrl: './event-edit.component.html',
  styleUrls: ['./event-edit.component.scss']
})
export class EventEditComponent implements OnInit, ComponentCanDeactivate {
  currentEvent: any;
  eventId: number = 0;
  isDirty: boolean = false;

  constructor(private eventService: EventService,
              private activatedRoute:ActivatedRoute,
              private router: Router,
              private messageService: MessageService,
              private confirmationService: ConfirmationService) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(params => {
      // @ts-ignore
      this.eventId = +params.get('id');
      console.log(this.eventId)
    });

    this.eventService.getById(this.eventId).subscribe((res: any) => {
      if (res.success) {
        this.currentEvent = res.data;
        this.currentEvent.startTime = new Date(res.data.startTime)
        this.currentEvent.endTime = new Date(res.data.endTime)
      }
    })
  }

  updateEventButton() {
    this.eventService.updateEvent(this.eventId, this.currentEvent).subscribe((res: any) => {
      console.log(res)
      if (res.success) {
        this.currentEvent = res.data;
        this.currentEvent.startTime = new Date(res.data.startTime)
        this.currentEvent.endTime = new Date(res.data.endTime)
        this.isDirty = false;
        this.messageService.add({severity: 'success', summary: 'Success', detail: "Event successfully updated!"})
      } else {
        this.messageService.add({severity: 'error', summary: 'Error!', detail: "Couldn't update event!"})
      }
    },error => {
      this.messageService.add({severity: 'error', summary: 'Error!', detail: error.message})
    });
  }

  backToList() {
    this.router.navigate(['/event-list'])
  }

  canDeactivate(): boolean {
    if (this.isDirty) {
      this.confirmationService.confirm({
        message: 'Are you sure that you want to leave without saving the changes?',
        header: 'Confirmation',
        icon: 'pi pi-exclamation-triangle',
        accept: () => {
          this.isDirty = false;
          this.backToList();
        },
        reject: () => {
        }
      });
    }
    return !this.isDirty;
  }

}
