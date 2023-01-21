import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ModalController } from '@ionic/angular';

@Component({
  selector: 'app-add-field',
  templateUrl: './add-field.page.html',
  styleUrls: ['./add-field.page.scss'],
})
export class AddFieldPage implements OnInit {

  public addfield:FormGroup;
  private urlField="https://smart-irrigation.me:8443/smart_irrigation-1.0-SNAPSHOT/api/field"
  constructor(public modalCtrl: ModalController,private http:HttpClient,private fb:FormBuilder ) {
    this.addfield=this.fb.group({
      name:['',Validators.required]
    })
   }

  ngOnInit() {
  }

  addField(){
    let body = this.addfield.value;
    this.http.post(this.urlField,body).subscribe(
      data=>{
        console.log(data);
        
        alert('Field added successfully!');
        this.dismiss();
        
      },err=>{
        console.log(err);
        alert('Field not added,Please Try Again!');
      }

    )
  }
  async dismiss() {
    return await this.modalCtrl.dismiss();
  }

}
