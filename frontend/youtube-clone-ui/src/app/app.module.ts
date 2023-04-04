import { NgModule } from '@angular/core';
import { FormsModule , ReactiveFormsModule} from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule} from "@angular/common/http";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'

import { RouterModule } from '@angular/router';
import { Component } from '@angular/core';
import { UploadVideoComponent } from './upload-video/upload-video.component';
import { NgxFileDropModule } from 'ngx-file-drop';
import { HeaderComponent } from './header/header.component';
import { MatToolbarModule } from '@angular/material/toolbar';
//Angular Material imports
import { SaveVideoDetailsComponent } from './save-video-details/save-video-details.component';
import {MatSelectModule} from '@angular/material/select'
import {MatFormFieldModule} from '@angular/material/form-field'
import {MatChipsModule} from '@angular/material/chips';
import {MatButtonModule} from '@angular/material/button'
import {MatIcon, MatIconModule} from '@angular/material/icon'
import { MatInputModule } from '@angular/material/input';
import {VgCoreModule} from '@videogular/ngx-videogular/core';
import {VgControlsModule} from '@videogular/ngx-videogular/controls';
import {VgOverlayPlayModule} from '@videogular/ngx-videogular/overlay-play';
import {VgBufferingModule} from '@videogular/ngx-videogular/buffering';




@NgModule({
  declarations: [
    AppComponent,
    UploadVideoComponent,
    HeaderComponent,
    SaveVideoDetailsComponent,            
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
    VgBufferingModule
      ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
