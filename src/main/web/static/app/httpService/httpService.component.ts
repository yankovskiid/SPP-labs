import { Injectable } from '@angular/core';
import { Http, Headers} from '@angular/http';
import {token} from "../global.settings";


@Injectable()
export class HttpService {

	constructor(private http: Http) {

	}

	public getData(url: string) {
		var headers = this.getHeaders();
		return this
			.http
			.get(url, { headers: headers })
			.map((data) => data.json());
	}

	public getData(url: string,  tail: string) {
		var headers = this.getHeaders();
		return this
				.http
				.get(url + tail, { headers: headers })
				.map((data) => data.json());
	}

	public sendData(url: string,  data: any) {
		var headers = this.getSendHeaders();
		return this
				.http
				.post(url, JSON.stringify(data), { headers: headers })
				.map((response) => response.json());
	}

	private getSendHeaders() {
		var result = new Headers();
		result.append('Content-Type', 'application/json');
		if (this.getToken() !== "") {
			result.append('token', this.getToken());
		}
		return result;
	}

	private getHeaders() {
		var result = new Headers();
		if (this.getToken() !== "") {
			result.append('token', this.getToken());
		}
		return result;
	}

	public getToken() {
		if (token !== "") {
			return token;
		} else {
			//TODO : get from cookie
		}

	}

	public setToken(temp : string) {
		token = temp;
		// TODO : set to cookie
	}

	public deleteToken() {
		token = "";
		// TODO : delete from cookie
	}

}