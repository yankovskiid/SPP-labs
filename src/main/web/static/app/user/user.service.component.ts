import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

@Injectable()
export class UserService {

	constructor(private http: Http) {

	}

	public getData(id: string) {
		return this.http.get('http://localhost:8080/user/' + id)
			.map((data) => data.json());
	}

}
/*
http.get('http://localhost:8080/user/' + id)
	.map((data) => data.json())
	.subscribe((data) => {
		this.user = data;
	});
*/
