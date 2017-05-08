import {Injectable} from "@angular/core";
import {HttpService} from "../http.service";

@Injectable()
export class AuthenticationService {

    public static temp: String = "test";

    constructor(private http: HttpService) {}

    public getUserNick(): string {
        var token = this.http.getToken();
        if(token != "") {
            return this.decodingToken(token)["NICK"];
        }

        return "голубец";
    }

    public isAdmin(): boolean {
        var token = this.http.getToken();
        if(token != "") {
            return this.decodingToken(token)["IS_ADMIN"];
        }
        return false;
    }

    public isAuth(): boolean {
        var token = this.http.getToken();
        if(token === "")
            return false;
        else
            return true;
    }

    private decodingToken(token: String): any {
        var encodeString = token.split(".")[1];
        var decodeObject = JSON.parse(atob(encodeString));
        return decodeObject;
    }

}