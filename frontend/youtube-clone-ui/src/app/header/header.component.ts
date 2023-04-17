import { DOCUMENT } from '@angular/common';
import { HttpHeaders } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { OidcSecurityService } from 'angular-auth-oidc-client';




@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit{
  isAuthenticated: boolean = false;

  constructor(private oidcSecurityService: OidcSecurityService) {}

  ngOnInit(): void {
  
  }
    login(){
      this.oidcSecurityService.authorize();

    }
    logout(){
      //this.oidcSecurityService.logoffAndRevokeTokens();
      this.oidcSecurityService.logoff().subscribe((result) => console.log(result));
    }
    
  }




