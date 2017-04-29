export class City {
    id: number;
    name: string;
    country: string;
    
    constructor() {

    }

    static deserialize(data: any): City {
        var res = new City();
        res.id = data.id;
        res.name = data.name;
        res.country = data.country;

        return res;
    }
}