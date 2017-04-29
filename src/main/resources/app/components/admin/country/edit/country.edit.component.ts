import {Component, OnInit, EventEmitter, Input, Output} from '@angular/core';

@Component({
    selector: '[country-edit]',
    templateUrl: 'app/components/admin/country/edit/country.edit.component.html',
    styleUrls: ['app/components/admin/country/edit/country.edit.component.css']
})
export class CountryEditComponent implements OnInit {

    @Input() country : any;
    @Output() endEdit = new EventEmitter<boolean>();

    ngOnInit(): void {
    }

    constructor() {
    }

    save(temp : boolean) {
        this.endEdit.emit(temp);
    }

}