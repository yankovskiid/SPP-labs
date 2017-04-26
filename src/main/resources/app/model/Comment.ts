import {ShortComment} from "./ShortComment";
import {UserInformation} from "./UserInformation";
export class Comment extends ShortComment{
    private userInfo: UserInformation;

    constructor(text: string, userInfo: UserInformation) {
        super(text);
        this.userInfo = userInfo;
    }
}