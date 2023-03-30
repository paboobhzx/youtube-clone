import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { UploadVideoComponent } from './upload-video/upload-video.component';
import { NgxFileDropModule } from 'ngx-file-drop';
import { FormsModule } from '@angular/forms';
import {HttpClientModule} from "@angular/common/http"
import {MatButtonModule} from "@angular/material/button"

@NgModule({
  declarations: [
    AppComponent,
    UploadVideoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    BrowserModule,
    FormsModule,
    HttpClientModule,
    NgxFileDropModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
