import {Component, OnInit} from "@angular/core";
import {HttpService} from "../../../services/httpServices/http.service";
import {UserInformation} from "../../../model/UserInformation";

@Component({
    templateUrl: "app/components/user/edit/edit.user.component.html",
    styleUrls: ['app/components/user/edit/edit.user.component.css']
})
export class EditUserComponent implements OnInit {

    public errorHidden = true;
    public sucessHidden = true;
    public errorMessage : string;
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
        this.errorHidden = true;
        this.sucessHidden = true;
        var elError = document.getElementById('error-block');
        var elSuccess = document.getElementById('success-block');
        if(isSave) {
            this.http
                .updateData("user/information", this.userInfo)
                .catch((response) => {
                    var error = JSON.parse(response["_body"]);
                    this.errorHidden = false;
                    this.errorMessage = error.message;
                    setTimeout(function(){
                        elError.style.display = "block";
                    }, 50);
                    setTimeout(function () {
                        elError.style.display = "none";
                    }, 3000);
                    return null;
                })
                .subscribe(() => {
                    this.getUserInfo();
                    this.sucessHidden = false;
                    setTimeout(function(){
                        elSuccess.style.display = "block";
                    }, 50);
                    setTimeout(function () {
                        elSuccess.style.display = "none";
                    }, 3000);
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