import {UpdateUser} from "./UpdateUser";
export class NewUpdateUser {
    id: number;
    email: string;
    blocked: boolean;
    roles: Array<string> = [];
    isAdmin: boolean;

    constructor() {

    }

    isAdminFunc(): boolean {
        for(var i = 0; i < this.roles.length; i++) {
            if(this.roles[i] === "ADMIN") {
                return true;
            }
        }

        return false;
    }

    static deserializeForUpdate(data: any): UpdateUser {
        var res = new UpdateUser();
        res.id = data.id;
        res.blocked = data.blocked;
        res.email = data.email;

        res.roles.push("USER");
        if(data.isAdmin) {
            res.roles.push("ADMIN");
        }

        return res;
    }

    static deserialize(data: any): NewUpdateUser {
        var res = new NewUpdateUser();
        res.id = data.id;
        res.blocked = data.blocked;
        res.email = data.email;

        for(var i = 0; i < data.roles.length; i++) {
            res.roles.push(data.roles[i]);
        }
        res.isAdmin = res.isAdminFunc();

        return res;
    }
}