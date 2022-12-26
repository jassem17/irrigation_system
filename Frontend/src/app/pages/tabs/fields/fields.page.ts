import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FieldServiceService } from 'src/app/services/field-service.service';
import { ParcelPage } from '../parcel/parcel.page';

@Component({
  selector: 'app-fields',
  templateUrl: './fields.page.html',
  styleUrls: ['./fields.page.scss'],
})
export class FieldsPage implements OnInit {

  public fields : any;
  private urlField = "http://localhost:8080/smart_irrigation-1.0-SNAPSHOT/api/field/";
  private urlParcel = "http://localhost:8080/smart_irrigation-1.0-SNAPSHOT/api/field/parcels";

  constructor(private fieldService:FieldServiceService ,private http:HttpClient,private router:Router) {
    this.getFieldsList();
   }

  ngOnInit() {
  }

  getFieldsList(){
    return this.http.get(this.urlField).subscribe(
      data=>{
        this.fields=data;
        console.log(this.fields)
      
      },e=>{
        console.log(e);
      }
    );
  }
  getParcelById(id:string){
    return this.http.get(`${this.urlParcel}/${id}`).subscribe(
     data=>{
      console.log(data);
      this.router.navigate(['/','tabs','parcel']);
     },err=>{
      console.log(err);
     });
}

}
