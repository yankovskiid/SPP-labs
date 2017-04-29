export class Category {
    private name: String;

    static deserialize(input: any): Category {
        var res = new Category();
        res.name = input.name;
        return res;
    }
}