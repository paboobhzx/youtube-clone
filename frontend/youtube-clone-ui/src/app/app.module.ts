import { NgModule } from '@angular/core';
import { FormsModule , ReactiveFormsModule} from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule, HTTP_INTERCEPTORS} from "@angular/common/http";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'

import { RouterModule } from '@angular/router';
import { Component } from '@angular/core';
import { UploadVideoComponent } from './upload-video/upload-video.component';
import { NgxFileDropModule } from 'ngx-file-drop';
import { SaveVideoDetailsComponent } from './save-video-details/save-video-details.component';
import { HeaderComponent } from './header/header.component';

//Angular Material imports
import { MatToolbarModule } from '@angular/material/toolbar';
import {MatSelectModule} from '@angular/material/select'
import {MatFormFieldModule} from '@angular/material/form-field'
import {MatChipsModule} from '@angular/material/chips';
import {MatButtonModule} from '@angular/material/button'
import {MatIcon, MatIconModule} from '@angular/material/icon'
import { MatInputModule } from '@angular/material/input';
//End Material Imports
import {VgCoreModule} from '@videogular/ngx-videogular/core';
import {VgControlsModule} from '@videogular/ngx-videogular/controls';
import {VgOverlayPlayModule} from '@videogular/ngx-videogular/overlay-play';
import {VgBufferingModule} from '@videogular/ngx-videogular/buffering';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { VideoPlayerComponent } from './video-player/video-player.component';
import { TokenInterceptor } from './tokeninterceptort.service';
import { CallbackComponent } from './callback/callback.component';
import { AuthHttpInterceptor, AuthModule } from '@auth0/auth0-angular';
import { UserProfileComponent } from './userprofile/userprofile.component';





@NgModule({
  declarations: [
    AppComponent,
    UploadVideoComponent,
    HeaderComponent,
    SaveVideoDetailsComponent,
    VideoPlayerComponent,
    CallbackComponent,      
    UserProfileComponent      
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,    
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    RouterModule,
    NgxFileDropModule,
    MatSelectModule,
    MatFormFieldModule,
    MatSelectModule,
    MatChipsModule,
    ReactiveFormsModule,
    MatInputModule,
    VgCoreModule,
    VgControlsModule,
    VgOverlayPlayModule,
    VgBufferingModule,
    MatSnackBarModule,   
    AuthModule.forRoot({
      domain: 'dev-i63rcznjs255jxgf.us.auth0.com',
      clientId: 'NJbox1ptzDdadd1Zo9mlBvtkElgge59v',
      authorizationParams: {
        redirect_uri: window.location.origin
      }
    }),
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthHttpInterceptor,
      multi: true,
    }],
  bootstrap: [AppComponent]
})
export class AppModule { }
