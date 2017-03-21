import { BrowserModule }  from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { MyApp } from './app.component.ts';

@NgModule({
    imports: [BrowserModule, HttpModule],
    declarations: [MyApp],
    bootstrap: [MyApp]
})

export class MyAppModule { }