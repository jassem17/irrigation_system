import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ModalController } from '@ionic/angular';

@Component({
  selector: 'app-add-parcel',
  templateUrl: './add-parcel.page.html',
  styleUrls: ['./add-parcel.page.scss'],
})
export class AddParcelPage implements OnInit {

  public addparcel:FormGroup;
  private urlParcel="https://smart-irrigation.me:8443/smart_irrigation-1.0-SNAPSHOT/api/parcel";
  private urlField="https://smart-irrigation.me:8443/smart_irrigation-1.0-SNAPSHOT/api/field"

  public fields:any

  constructor(public modalCtrl: ModalController,private http:HttpClient,private fb:FormBuilder) { 
    this.addparcel=this.fb.group({
      idField:['',Validators.required]
    })
  }

  ngOnInit() {
    this.getFieldsList()
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

  addParcel(){
    let body = this.addparcel.value;
    this.http.post(this.urlParcel,body).subscribe(
      data=>{
        console.log(data);
        
        alert('Parcel added successfully!');
        this.dismiss();
        
      },err=>{
        console.log(err);
        alert('Parcel not added,Please Try Again!');
      }

    )
  }

  async dismiss() {
    return await this.modalCtrl.dismiss();
  }

}
