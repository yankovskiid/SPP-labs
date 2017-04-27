import { UserComponent } from './../information-component/user.component';
import { RegistrationComponent } from './../registration-component/registration.component';
import { LoginComponent } from './../login-component/login.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes }  from '@angular/router';

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