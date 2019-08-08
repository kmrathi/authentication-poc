import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../service/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username = 'kmrathi';
  password = 'roshni';
  invalidLogin = false;

  ngOnInit() {
  }

  constructor(private router: Router,
    private loginservice: AuthenticationService) { }

    checkLogin() {
      console.log('authnticating');
      (this.loginservice.authenticate(this.username, this.password).subscribe(
        data => {
          console.log('routing to users');
          this.router.navigate(['users'])
          this.invalidLogin = false;
        },
        error => {
          console.log('invalid log on');
          this.invalidLogin = true;
  
        }
      )
      );
    }

}
