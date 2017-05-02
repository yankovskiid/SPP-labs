import { UserRoutingModule } from './routing-module/user.routing.module';
import { UserComponent } from './information-component/user.component';
import { RegistrationComponent } from './registration-component/registration.component';
import { LoginComponent } from './login-component/login.component';
import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import {EditUserComponent} from "./edit/edit.user.component";

@NgModule({
    imports: [
    	BrowserModule,
        HttpModule,
        FormsModule,
        RouterModule,

        UserRoutingModule
    ],
    declarations: [
        LoginComponent,
        RegistrationComponent,
        UserComponent,
        EditUserComponent
    ],
    exports: [
        LoginComponent,
        RegistrationComponent,
        UserComponent
    ]
})

export class UserModule { 
    constructor() {
    }
}