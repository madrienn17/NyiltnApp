import { Component, OnInit } from '@angular/core';
import {RegistrationService} from "../../_services/registration.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.scss']
})
export class StatisticsComponent implements OnInit {

  registrationByCounty: any[] = [];

  registrationBySchool: any[] = [];

  topEvents: any[] = [];

  data1: any;
  data2: any;
  data3: any;

  bgColor = [
    "#42A5F5",
    "#66BB6A",
    "#FFA726"
  ]

  hoverBgColor = [
    "#64B5F6",
    "#81C784",
    "#FFB74D"
  ]

  chartOptions = {
    responsive: false,
    maintainAspectRatio: false
  };

  subscription: Subscription = {} as Subscription;

  constructor(private registrationService: RegistrationService) { }

  ngOnInit(): void {
    this.registrationService.getNrByCounty().subscribe((data: any) => {
      this.registrationByCounty = data.data;
        console.log(this.registrationByCounty)
      this.data1 = {
        labels: this.registrationByCounty.map(a => a.label),
        datasets: [
          {
            data: this.registrationByCounty.map(b => b.value),
            backgroundColor: this.bgColor,
            hoverBackgroundColor: this.hoverBgColor
          }
        ]
      };
    });


    this.registrationService.getNrBySchool().subscribe((res: any) => {
      this.registrationBySchool = res.data;
      console.log(this.registrationBySchool)
      this.data2 = {
        labels: this.registrationBySchool.map(a => a.label),
        datasets: [
          {
            data: this.registrationBySchool.map(b => b.value),
            label: 'Jelentkezés iskolánként',
            backgroundColor: this.bgColor,
          }
        ]
      }
    })

    this.registrationService.getTopEvents().subscribe((res: any) => {
      this.topEvents = res.data.slice(1, 6);
      console.log(this.topEvents)
      this.data3 = {
        labels: this.topEvents.map(a => a.label),
        datasets: [
          {
            data: this.topEvents.map(b => b.value),
            backgroundColor: this.bgColor,
            hoverBackgroundColor: this.hoverBgColor
          }
        ]
      }
    })


  }
}
