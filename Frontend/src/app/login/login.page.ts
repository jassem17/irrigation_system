import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ModalController, NavController } from '@ionic/angular';

import { BehaviorSubject } from 'rxjs';
import { ForgetPage } from '../forget/forget.page';
import { AdminPage } from '../admin/admin.page';
import { FieldsPage } from '../fields/fields.page';
import { TabsPage } from '../pages/tabs/tabs.page';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {


  public token:any;
  choice:any;
  @Input() mySubject: BehaviorSubject<string>;
  choices = [
    
  ];
  public login : FormGroup
  private urlLogin="https://smart-irrigation.me:8443/smart_irrigation-1.0-SNAPSHOT/api/oauth2/token";
  private urlUser="https://smart-irrigation.me:8443/smart_irrigation-1.0-SNAPSHOT/api/person/single"

  private user:any
  constructor(
    public modalCtrl: ModalController,private http:HttpClient,private fb:FormBuilder,private router:Router,public navCtrl:NavController,private route:ActivatedRoute) {
    this.login = this.fb.group(
      {
      username : ['',Validators.required],
      password: ['',Validators.required],
      grandType:"PASSWORD"
    }

    );
   }

  ngOnInit() {
    
    
  }
  goToFieldsPage(){
    this.navCtrl.navigateForward('fields')
  }
  goToAdminPage(){
    this.navCtrl.navigateForward('admin')
  }

  handleRefresh(event) {
    setTimeout(() => {
      // Any calls to load data go here
      
      this.SignIn();
      event.target.complete();
    }, 2000);
  };

  SignIn(){
    let preselect = this.mySubject?.value;    
    console.log("preselect : ",preselect);
    this.choice=preselect;
    console.log(this.choice)
    let body=this.login.value;
    console.log(body)
    /*this.http.get(`${this.urlUser}/${this.login.value.username}`).subscribe(
      dataa=>{
        console.log("hello from user ",dataa);
        this.user=dataa;
      },err=>{
        console.log(err)
      }
    )*/
    let direction :any;
    const headers= new HttpHeaders()
  .set("Authorization", this.token)
  ;
    this.http.post(this.urlLogin,body).subscribe(
      data=>{
         console.log(data)
         let url="/fields"
         localStorage.setItem('currentUser', JSON.stringify(body));
         localStorage.setItem('token', JSON.stringify(data));
         console.log(localStorage)
         console.log(this.choice)
         if(this.choice=="USER"){
          console.log('hi from user');
          //this.router.navigateByUrl(url)
          direction = 0;

         }
         else if(this.choice=="ADMIN"){
          console.log('hello from admin')
          direction = 1;
          //this.router.navigate(['admin'])
         }
         else{
          alert("You are not authorized to sign in")
        }
        }
      ,err=>{
        console.log(err)
        alert('Invalid username or password')
      }

    )

    setTimeout(async ()=>{
      console.log(direction);
      if(direction==0){
        //this.goToFieldsPage();
        const modal = await this.modalCtrl.create({
          component: FieldsPage,
          animated: true,
          mode: 'ios',
          backdropDismiss: false,
          cssClass: 'forget-modal',
        })
        return await modal.present();
        this.dismiss();
      }
      if(direction==1){
        //this.goToAdminPage();
        const modal = await this.modalCtrl.create({
          component: AdminPage,
          animated: true,
          mode: 'ios',
          backdropDismiss: false,
          cssClass: 'forget-modal',
        })
        return await modal.present();
        this.dismiss();
      }
    },2000) 
  }
  isLoggedIn() {
    if (localStorage.getItem('currentUser')) {
      return true;
    }
    return false;
  }
  logout() {
    localStorage.removeItem('currentUser');
  }

  async forget() {
    const modal = await this.modalCtrl.create({
      component: ForgetPage,
      animated: true,
      mode: 'ios',
      backdropDismiss: false,
      cssClass: 'forget-modal',
    })
    return await modal.present();
  }
  async home() {
    const modal = await this.modalCtrl.create({
      component: FieldsPage,
      animated: true,
      mode: 'ios',
      backdropDismiss: false,
      cssClass: 'home-modal',
    })
    return await modal.present();
  }
  async dismiss() {
    await this.modalCtrl.dismiss();
  }
}
