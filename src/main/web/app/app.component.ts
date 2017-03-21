import { Component } from '@angular/core';
import { Http } from '@angular/http';
import 'rxjs/Rx';

@Component({
    selector: 'my-app',
    template: `<h1>{{user | json}}</h1>`
})

export class MyApp {
    private user;

    constructor(http: Http) {
        http.get('http://localhost:8080/user/1')
            .map((data) => data.json())
            .subscribe((data) => {
                this.user = data;
            });
        }
}
