import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PetitionListComponent } from "../list/petiton.list.component";
import { PetitionAddComponent } from "../add/petition.add.component";
import { PetitionMainComponent } from "../main/petition.main.component";

var petitionRoutes: Routes = [
		{
			path: 'petitions',
			component: PetitionListComponent
		},
		{
			path: "petition/add",
			component: PetitionAddComponent
		},
		{
			path: 'petition/:id',
			component: PetitionMainComponent
		}

];

@NgModule({
	imports: [
		RouterModule.forRoot(petitionRoutes)
	],
	exports: [
		RouterModule
	] 
})
export class PetitionRoutingModule {

}