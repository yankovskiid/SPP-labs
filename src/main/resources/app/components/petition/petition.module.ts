import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { PetitionListComponent } from "./list/petiton.list.component";
import { PetitionRoutingModule } from "./routing/petition.routing.module";
import { PetitionMainComponent } from "./main/petition.main.component";
import { PetitionAddComponent } from "./add/petition.add.component";
import {CommentListComponent} from "./list/comment/comment.list.component";
import {CommentEditComponent} from "./list/comment/edit/comment.edit.component";
// import {HttpService} from "./../../services/httpServices/http.service";

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
        PetitionAddComponent,
        CommentListComponent,
        CommentEditComponent
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