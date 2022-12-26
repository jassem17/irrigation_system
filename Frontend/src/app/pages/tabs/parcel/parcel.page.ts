import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-parcel',
  templateUrl: './parcel.page.html',
  styleUrls: ['./parcel.page.scss'],
})
export class ParcelPage implements OnInit {

  public parcels : any;
  public paa:any;
  private url = "http://localhost:8080/smart_irrigation-1.0-SNAPSHOT/api/parcel/";
  private urlSensor = "http://localhost:8080/smart_irrigation-1.0-SNAPSHOT/api/parcel/sensor";

  constructor(private http:HttpClient,private router:Router) {
    this.getParcelList();
   }

  ngOnInit() {
  }

  getParcelList(){
    return this.http.get(this.url).subscribe(
      data=>{
        this.parcels=data;
        console.log(this.parcels)
      
      },e=>{
        console.log(e);
      }
    );
  }

  getSensorById(id:string){
    return this.http.get(`${this.urlSensor}/${id}`).subscribe(
     data=>{
      console.log(data);
      this.router.navigate(['/','tabs','home']);
     },err=>{
      console.log(err);
     });
}
}
