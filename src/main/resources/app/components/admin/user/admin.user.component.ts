import {Component, OnInit} from "@angular/core";
import {HttpService} from "../../../services/httpServices/http.service";
import {UpdateUser} from "../../../model/UpdateUser";

@Component({
    templateUrl: "app/components/admin/user/admin.user.component.html",
    styleUrls: ["app/components/admin/user/admin.user.component.css"]
})
export class AdminUserComponent implements OnInit {

    private users: UpdateUser[] = [];
    private editingUser: UpdateUser = null;

    constructor(private http: HttpService) {}

    ngOnInit() {
        this.getUsers();
    }

    editUser(user: UpdateUser) {
        if(this.editingUser == null) {
            this.editingUser = UpdateUser.deserialize(user);
        } else {
            alert('End editing');
        }
    }

    deleteUser(user: any) {
        var isDelete = confirm("Delete?");
        if(isDelete) {
            if (this.editingUser === null) {
                this.http
                    .deleteData("/user/" + user.id)
                    .subscribe(() => {
                        this.getUsers();
                    });
            } else {
                alert('End deleting!');
            }
        }
    }

    saveUser(temp : boolean) {
        if (temp) {
            this.http
                .sendDataNoResponse("/user/" + this.editingUser.id, UpdateUser.deserialize(this.editingUser))
                .subscribe(() => {
                    this.getUsers();
                    this.editingUser = null;
                });
        } else {
            this.editingUser = null;
        }
    }

    private getUsers() {
        this.http
            .getData("users")
            .subscribe(data => {
                var temp = data.users;
                var users: UpdateUser[] = [];
                for (var i = 0; i < temp.length; i += 1) {
                    users.push(UpdateUser.deserialize(temp[i]));
                }
                this.users = users;
            });
    }

    cancelEditing() {
        this.editingUser = null;
    }

}