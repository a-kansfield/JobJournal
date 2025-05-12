import { Status } from "../status/status";
import { User } from "../user/user";

export class Application {
    private defaultDate = new Date();
    protected Application() {

    }
    
    constructor(
        public id: number = -1,
        public dateCreated: Date = this.defaultDate,
        public dateUpdated: Date = this.defaultDate,
        public jobTitle: string = '',
        public employer: string = '',
        public datePosted: Date | null = null,
        public dateApplied: Date | null = null,
        public dateDue: Date | null = null,
        public status: Status = new Status(-1, 'TEST'),
        public userID : number = -1,
        
    ) {}
    
    
}
