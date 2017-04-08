import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MyApp } from './app.component';


@NgModule({
    imports: [
    	BrowserModule
    ],
    declarations: [
    	MyApp
    ],
    bootstrap: [MyApp]
})

export class MyAppModule { }