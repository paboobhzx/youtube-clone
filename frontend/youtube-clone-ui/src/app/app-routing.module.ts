import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UploadVideoComponent } from './upload-video/upload-video.component';
import {SaveVideoDetailsComponent} from './save-video-details/save-video-details.component'
import { VideoDetailComponent } from './video-detail/video-detail.component';


const routes: Routes = [
  {
    path: 'upload-video', component: UploadVideoComponent
  },
  {
    path: 'video-details/:videoId', component: VideoDetailComponent
  },
  {
    path: 'save-video-details/:videoId', component: SaveVideoDetailsComponent
  }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }