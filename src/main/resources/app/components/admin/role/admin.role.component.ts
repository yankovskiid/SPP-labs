import {Component, OnInit} from '@angular/core';
import {HttpService} from "../../../services/httpServices/http.service";
import {ShortRole} from "../../../model/ShortRole";
import {Role} from "../../../model/Role";

@Component({
	templateUrl: 'app/components/admin/role/admin.role.component.html',
	styleUrls: ['app/components/admin/role/admin.role.component.css']
})
export class AdminRoleComponent implements OnInit {

	private editingRole: ShortRole = null;
	private roles: ShortRole[] = [];

	ngOnInit(): void {
		this.getRoles();
	}

	constructor(private http: HttpService) {
	}

	editRole(role: ShortRole): void {
		if (this.editingRole === null) {
			this.editingRole = ShortRole.deserialize(role);
		} else {
			alert('End editing');
		}
	}

	deleteRole(role: ShortRole): void {
		if (this.editingRole === null) {
			this.http
				.deleteData("/role/" + role.id)
				.subscribe(() => {
					this.getRoles();

				});
		} else {
			alert('End editing!');
		}
	}

	addRole() {
		if (this.editingRole === null) {
			this.editingRole = new ShortRole();
		} else {
			alert('End editing!');
		}
	}

	saveRole(temp : boolean) {
		if (temp) {
			if (this.editingRole.id) {
				this.http
					.sendDataNoResponse("/role/" + this.editingRole.id, Role.deserialize(this.editingRole))
					.subscribe(() => {
						this.getRoles();
						this.editingRole = null;
					});
			} else {
				this.http
					.sendDataNoResponse("/role", Role.deserialize(this.editingRole))
					.subscribe(() => {
						this.getRoles();
						this.editingRole = null;
					});
			}
		} else {
			this.editingRole = null;
		}
	}

	cancelEditing() {
		this.editingRole = null;
	}

	private getRoles() {
		this.http
			.getData("/roles")
			.subscribe(data => {
				var temp = data.roles;
				var roleArray: ShortRole[] = [];
				for (var i = 0; i < temp.length; i++) {
					roleArray.push(ShortRole.deserialize(temp[i]));
				}
				this.roles = roleArray;
			});
	}

}