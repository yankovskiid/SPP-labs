import { Component } from '@angular/core';
import { RegistrationService } from './registration.service.component';
import { RegistrationUser } from './RegistrationUser';
@Component  ({
	templateUrl: '/resources/static/app/user/registration/registration_form.html',
	styleUrls: [ 'resources/static/app/user/registration/registration_form.css'],
	providers: [
		RegistrationService
	]
})
export class RegistrationComponent {
	public registrationResponse;
	public hidden = true;
	public registrationUser = new RegistrationUser();
	constructor (private registrationService : RegistrationService) {
	}

	public onSubmit() {
		if (this.registrationUser.password !== this.registrationUser.repeatPassword) {
			this.hidden = false;
		} else {
			this.registrationService.sendData(this.registrationUser)
				.catch((errorMessage) => alert(errorMessage.json().message))
				.subscribe((response) => this.registrationResponse = response);
		}
		return false;
	}

}
