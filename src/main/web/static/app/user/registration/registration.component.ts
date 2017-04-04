import { Component } from '@angular/core';
import { RegistrationUser } from './RegistrationUser';
import { Router } from '@angular/router';
import { HttpService } from "../../httpService/httpService.component";

@Component  ({
	templateUrl: '/resources/static/app/user/registration/registration_form.html',
	styleUrls: [ 'resources/static/app/user/registration/registration_form.css'],
	providers: [
		HttpService
	]
})
export class RegistrationComponent {
	public registrationResponse;
	public hidden = true;
	public registrationUser = new RegistrationUser();
	constructor (private httpService : HttpService,
	             private router : Router,) {
	}

	public onSubmit() {
		if (this.registrationUser.password !== this.registrationUser.repeatPassword) {
			this.hidden = false;
		} else {
			this.httpService.sendData('/registration', this.registrationUser)
				.catch((errorMessage) => alert(errorMessage.json().message))
				.subscribe((response) => {
					this.registrationResponse = response;
					this.httpService.setToken(this.registrationReponse.token);
					this.router.navigate(['/user', 1]);
				});
		}
		return false;
	}

}
