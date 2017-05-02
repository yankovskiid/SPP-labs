import {NewUpdateUser} from "./NewUpdateUser";
export class UpdateUser {
     id: number;
     email: string;
     blocked: boolean;
     roles: Array<string> = [];

    constructor() {
        
    }

    static deserializeForUpdate(data: any): UpdateUser {
        var res = new UpdateUser();
        res.id = data.id;
        res.blocked = data.blocked;
        res.email = data.email;

        for(var i = 0; i < data.roles.length; i++) {
            res.roles.push(data.roles[i]);
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