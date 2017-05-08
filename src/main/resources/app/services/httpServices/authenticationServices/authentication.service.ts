import {Injectable} from "@angular/core";
import {HttpService} from "../http.service";
import {Cookies} from "../../../cookies";

@Injectable()
export class AuthenticationService {

    public static temp: String = "test";

    constructor(private http: HttpService) {}

    // public isAdmin(): boolean {
    //     var result = false;
    //     this.http
    //         .getData("/isAdmin")
    //         .subscribe(data => {
    //             return data;
    //         });
    //     return result;
    // }

    public isAuth(): boolean {
        var token = this.http.getToken();
        if (token === "") {
            let cookies: Cookies = new Cookies();
            token = cookies.getCookie("token");
        }
        if(token === "")
            return false;
        else
            return true;
    }

}