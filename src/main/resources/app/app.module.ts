import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MyApp } from './app.component';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './routing/app.routing';
import { UserComponent } from './user/user.component';
import { MainComponent } from './main/main.component';
import { RegistrationComponent } from './user/registration/registration.component';
import { LoginComponent } from './user/login/login.component';



@NgModule({
    imports: [
    	BrowserModule,
        HttpModule,
        FormsModule,
        AppRoutingModule
    ],
    declarations: [
    	MyApp,
        UserComponent,
        MainComponent,
        RegistrationComponent,
        LoginComponent
    ],
    bootstrap: [MyApp]
})

export class MyAppModule { 
    constructor() {
    }
}