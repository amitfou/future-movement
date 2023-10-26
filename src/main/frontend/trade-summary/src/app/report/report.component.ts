import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent implements OnInit  {

  csvRows: any[] = [];

  constructor(private http:HttpClient) {

  }

  ngOnInit(): void {
    this.http.get("http://localhost:8080/summaryReport", {responseType: 'text'})
    .subscribe(data => {
     console.log(data)
     const list = data.split('\n');
      list.forEach(row => {
        const rowFields = row.split(',')
        this.csvRows.push({client: rowFields[0], product:rowFields[1], quantity:rowFields[2]});
      });
    })
  }


}
