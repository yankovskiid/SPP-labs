import { Injectable } from '@angular/core';
import { Http, Headers} from '@angular/http';
import { TokenService } from "./../../global.settings";
import { Observable } from 'rxjs/Rx';

 import 'rxjs/add/operator/map';
import {Cookies} from "../../cookies";


@Injectable()
export class HttpService {

	private api : string = "/api/";

	constructor(private http: Http) {

	}

	public getData(url: string) {
		var headers = this.getHeaders();
		return this
			.http
			.get(this.api + url, { headers: headers })
			.map((data) => data.json());
	}

	public sendData(url: string,  data: any) {
		var headers = this.getSendHeaders();
		return this
				.http
				.post(this.api + url, JSON.stringify(data), { headers: headers })
				.map((response) => response.json());
	}

	public sendDataNoResponse(url : string,  data: any) {
		var headers = this.getSendHeaders();
		return this
				.http
				.post(this.api + url, JSON.stringify(data), { headers: headers });
	}

	private getSendHeaders() {
		var result = new Headers();
		result.append('Content-Type', 'application/json');
		if (this.getToken() !== "") {
			result.append('token', this.getToken());
		}
		return result;
	}

	public deleteData(url: string) {
		var headers = this.getSendHeaders();
		return this
			.http
			.delete(this.api + url, { headers: headers });
	}

	public updateData(url: string,  data: any) {
		var headers = this.getSendHeaders();
		return this
			.http
			.put(this.api + url, JSON.stringify(data), { headers: headers });
	}

	private getHeaders() {
		var result = new Headers();
		if (this.getToken() !== "") {
			result.append('token', this.getToken());
		}
		return result;
	}

	public getToken() {
		if (TokenService.token !== "") {
			return TokenService.token;
		} else {
			let cookies: Cookies = new Cookies();
			return cookies.getCookie("token");
		}

	}

	public setToken(temp : string) {
		TokenService.token = temp;
		let cookies: Cookies = new Cookies();
		cookies.setCookie("token", temp, 3600 * 24, "/");
	}

	public deleteToken() {
		TokenService.token = "";
		let cookies: Cookies = new Cookies();
		cookies.deleteCookie("token");
	}

}