export class LoginRequest{
  private usernameOrEmail: string;
  private password: string;

  constructor(username,passd){
    this.usernameOrEmail = username ;
    this.password = passd ;
  }
}