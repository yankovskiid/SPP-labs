import {ShortUserInformation} from "./ShortUserInformation";
export class UserList {
    private users: Array<ShortUserInformation> = [];

    constructor(users: Array<ShortUserInformation>) {
        this.users = users;
    }
}