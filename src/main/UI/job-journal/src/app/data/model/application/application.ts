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
        public datePosted: string,
        public dateApplied: string,
        public dateDue: string,
        public status: string,
        public user : User
    ) {}
    
    
}
