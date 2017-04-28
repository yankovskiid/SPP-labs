import {Component, OnInit} from '@angular/core';
import {HttpService} from "../../../../services/httpServices/http.service";

@Component({
	selector: '[role-edit]',
	templateUrl: 'app/components/admin/role/edit/role.edit.component.html',
	styleUrls: ['app/components/admin/role/edit/role.edit.component.css']
})
export class RoleEditComponent implements OnInit {

	ngOnInit(): void {
	}

	constructor(private http: HttpService) {

	}

}