import {Component, OnInit} from "@angular/core";
import {HttpService} from "../../../services/httpServices/http.service";
import {UserInformation} from "../../../model/UserInformation";

@Component({
    templateUrl: "app/components/user/edit/edit.user.component.html",
    styleUrls: []
})
export class EditUserComponent implements OnInit {

    private userInfo: UserInformation = null;

    constructor(private http: HttpService) {}

    ngOnInit() {
        this.getUserInfo();
    }

    addUserInfo() {
        if(this.userInfo == null) {
            this.userInfo = new UserInformation();
        } else {
            alert("End editing!");
        }
    }

    editUserInfo(isSave: boolean) {
        console.log(this.userInfo);
        if(isSave) {
            this.http
                .updateData("user/information", this.userInfo)
                .subscribe(() => {
                    this.getUserInfo();
                });
        } else {
            this.getUserInfo();
        }
    }

    private getUserInfo() {
        this.http
            .getData("user/information")
            .subscribe(data => {
                this.userInfo = data;
            });
    }

}