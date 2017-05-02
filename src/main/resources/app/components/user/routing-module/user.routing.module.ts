import { UserComponent } from './../information-component/user.component';
import { RegistrationComponent } from './../registration-component/registration.component';
import { LoginComponent } from './../login-component/login.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes }  from '@angular/router';
import {EditUserComponent} from "../edit/edit.user.component";

var userRoutes: Routes = [
		{
			path: 'login',
			component: LoginComponent
		},
		{
			path: 'registration',
			component: RegistrationComponent
		},
		{
			path: "user",
			component: UserComponent,
			children: []
		},
		{
			path: 'user/information',
			component: EditUserComponent
		}
];

@NgModule({
	imports: [
		RouterModule.forRoot(userRoutes)
	],
	exports: [
		RouterModule
	] 
})
export class UserRoutingModule {

}