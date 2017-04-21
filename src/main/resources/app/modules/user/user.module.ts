import { RouterModule } from '@angular/router';
import { UserComponent } from './../../components/user/information/user.component';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { LoginComponent } from './../../components/user/login/login.component';
import { RegistrationComponent } from './../../components/user/registration/registration.component';
import { UserRoutingModule } from "./routing/user.routing.module";

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
        UserComponent
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