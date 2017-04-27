import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PetitionListComponent } from "./list/petiton.list.component";
import { PetitionRoutingModule } from "./routing/petition.routing.module";
import { PetitionMainComponent } from "./main/petition.main.component";
import { PetitionAddComponent } from "./add/petition.add.component";

@NgModule({
    imports: [
        BrowserModule,
        HttpModule,
        FormsModule,
        RouterModule,

        PetitionRoutingModule
    ],
    declarations: [
        PetitionListComponent,
        PetitionMainComponent,
        PetitionAddComponent
    ],
    exports: [
        PetitionListComponent,
        PetitionMainComponent,
        PetitionAddComponent
    ],
    providers: [
        
    ]
})
export class PetitionModule {

}