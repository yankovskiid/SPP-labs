import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { RegistrationUser } from "./RegistrationUser";

@Injectable()
export class RegistrationService {

	constructor(protected http: Http) {}

	private headers = new Headers({
		'Content-Type': 'application/json'
	});

	public sendData(data: RegistrationUser) {
		var headers = new Headers();
		headers.append('Content-Type', 'application/json');
		return this.http.post('/registration',
						JSON.stringify(data),
						{ headers: this.headers })
			.map((response) => response.json());
	}
}
