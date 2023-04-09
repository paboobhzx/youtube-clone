import { DOCUMENT } from '@angular/common';
import { HttpHeaders } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { AuthService } from '@auth0/auth0-angular';


import { AuthButtonComponent } from '../auth-button/auth-button.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit{
  isAuthenticated: boolean = false;

  constructor(public auth: AuthService,
    @Inject(DOCUMENT) private doc: Document
  ) {}

  ngOnInit(): void {
  
  }

  loginWithRedirect() {
    this.auth.loginWithRedirect();
    console.log("Login Method. Did the user logged in" + this.isAuthenticated)
    
  }
  logout() {
    this.auth.logout({ logoutParams: { returnTo: this.doc.location.origin } });
    console.log("Logout Method. Is user still logged?" + this.isAuthenticated)
  }

}

