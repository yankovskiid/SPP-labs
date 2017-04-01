import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { LoginUser } from './LoginUser';

@Injectable()
export class LoginService {
	constructor(http: Http) {

	}

	public sendData(loginUser : LoginUser) {
		//TODO: Send data
	}
}