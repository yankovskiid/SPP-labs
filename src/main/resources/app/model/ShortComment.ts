export class ShortComment {
     text: string;

    constructor() {
    }

    static deserialize(input: any):ShortComment {
        var res = new ShortComment();
        res.text = input.text;
        return res;
    }
}