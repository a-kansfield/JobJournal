export class User {
    protected User() {

    }
  
    constructor(
      public id: number,
      public firstName: string,
      public lastName: string,
      public email: string,
      public password: string
    ) {}

}