import {ShortComment} from "./ShortComment";
import {UserInformation} from "./UserInformation";
export class Comment extends ShortComment{
    userInfo: UserInformation;

    constructor() {
        super();
    }
}