import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import {HttpService} from "../../../../services/httpServices/http.service";
import {ShortRole} from "../../../../model/ShortRole";
import {Role} from "../../../../model/Role";

@Component({
	selector: '[role-edit]',
	templateUrl: 'app/components/admin/role/edit/role.edit.component.html',
	styleUrls: ['app/components/admin/role/edit/role.edit.component.css']
})
export class RoleEditComponent implements OnInit {

	@Input() role : any;
	@Output() endEdit = new EventEmitter<boolean>();

	ngOnInit(): void {

	}

	constructor() {

	}

	save(temp : boolean) {
		this.endEdit.emit(temp);
	}

}