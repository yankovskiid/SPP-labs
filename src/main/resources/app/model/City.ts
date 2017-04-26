export class City {
    private id: number;
    protected name: string;
    private country: string;


    constructor(id: number, name: string, country: string) {
        this.id = id;
        this.name = name;
        this.country = country;
    }
}