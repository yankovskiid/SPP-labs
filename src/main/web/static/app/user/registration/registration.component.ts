import { Component } from '@angular/core';
import { RegistrationService } from './registration.service.component';

@Component  ({
	templateUrl: '/resources/static/app/user/registration/registration_form.html',
	styleUrls: [ 'resources/static/app/user/registration/registration_form.css'],
	providers: [
		RegistrationService
	]
})
export class RegistrationComponent {
	constructor (registartionService : RegistrationService) {

	}


}
