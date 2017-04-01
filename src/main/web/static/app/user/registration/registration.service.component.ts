import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { RegistrationUser } from "./RegistrationUser";

@Injectable()
export class RegistrationService {

	constructor (http : Http) {

	}

	public sendData(data : RegistrationUser) {
		//TODO : Send post data
	}
}
