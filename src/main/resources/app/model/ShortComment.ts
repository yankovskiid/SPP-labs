import {ShortUserInformation} from "./ShortUserInformation";
import {UserInformation} from "./UserInformation";
import {isUndefined} from "util";
export class ShortComment {
    text: string;

    constructor() {}

    static deserialize(input: any):ShortComment {
        var res = new ShortComment();
        res.text = input.text;
        return res;
    }
}