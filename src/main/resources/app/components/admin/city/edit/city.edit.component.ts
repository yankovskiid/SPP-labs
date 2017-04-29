import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import {HttpService} from "../../../../services/httpServices/http.service";
import {ShortRole} from "../../../../model/ShortRole";
import {Role} from "../../../../model/Role";

@Component({
    selector: '[city-edit]',
    templateUrl: 'app/components/admin/city/edit/city.edit.component.html',
    styleUrls: ['app/components/admin/city/edit/city.edit.component.css']
})
export class CityEditComponent implements OnInit {

    @Input() city : any;
    @Output() endEdit = new EventEmitter<boolean>();

    ngOnInit(): void {

    }

    constructor() {

    }

    save(temp : boolean) {
        this.endEdit.emit(temp);
    }

}