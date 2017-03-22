import { NgModule }              from '@angular/core';
import { RouterModule, Routes }  from '@angular/router';
import { UserComponent } from './user/user.component.ts'
import { MainComponent } from "./main/main.component";

const appRoutes: Routes = [
	{
		path: "user/:id",
		component: UserComponent
	},
	{
		path: "",
		component: MainComponent
	}
];
@NgModule({
	imports: [
		RouterModule.forRoot(appRoutes)
	],
	exports: [
		RouterModule
	]
})
export class AppRoutingModule {}
