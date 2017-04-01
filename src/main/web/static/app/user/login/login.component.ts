import { Component } from '@angular/core';
import { LoginService } from "./login.service.component";

@Component ({
	templateUrl: '/resources/static/app/user/login/login_form.html',
	styleUrls: [ 'resources/static/app/user/login/login_form.css'],
	providers: [
		LoginService
	]
})
export class LoginComponent {
	constructor (loginService : LoginService) {

	}
}