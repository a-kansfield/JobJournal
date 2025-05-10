import { Status } from "../status/status";
import { User } from "../user/user";

export class Application {
    protected Application() {

    }
    
    constructor(
        public id: number,
        public dateCreated: Date,
        public dateUpdated: Date,
        public jobTitle: string,
        public employer: string,
        public datePosted: Date | null,
        public dateApplied: Date | null,
        public dateDue: Date | null,
        public status: Status,
        public userID : number,
        
    ) {}
    
    
}
