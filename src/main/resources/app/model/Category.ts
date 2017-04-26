import {CategoryShort} from "./CategoryShort";

export class Category extends CategoryShort {
    private id: number;


    constructor(name: string, id: number) {
        super(name);
        this.id = id;
    }
}