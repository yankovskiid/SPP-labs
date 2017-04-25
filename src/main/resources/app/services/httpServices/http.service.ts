import { Injectable } from '@angular/core';
import { Http, Headers} from '@angular/http';
import { TokenService } from "./../../global.settings";
import { Observable } from 'rxjs/Rx';

 import 'rxjs/add/operator/map';


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

	public getDataWithParametr(url: string,  tail: string) {
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
		if (TokenService.token !== "") {
			return TokenService.token;
		} else {
			//TODO : get from cookie
		}

	}

	public setToken(temp : string) {
		TokenService.token = temp;
		// TODO : set to cookie
	}

	public deleteToken() {
		TokenService.token = "";
		// TODO : delete from cookie
	}

}