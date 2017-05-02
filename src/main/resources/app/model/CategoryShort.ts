export class CategoryShort {
    id: number;
    name: string;

    constructor() {}

    static deserialize(input: any):CategoryShort {
        var res = new CategoryShort();
        res.id = input.id;
        res.name = input.name;
        return res;
    }
}