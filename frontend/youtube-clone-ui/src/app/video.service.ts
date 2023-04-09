import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FileSystemFileEntry } from 'ngx-file-drop';
import { Observable } from 'rxjs';
import { Globalcomponent } from '../global/global-component'
import { IVideoDto } from './IVideoDto';
import { IUploadVideoResponse } from './upload-video/IUploadVideoResponse';

@Injectable({
  providedIn: 'root'
})
export class VideoService {

  constructor(private httpClient: HttpClient) { }

  uploadVideo(fileEntry: File) : Observable<IUploadVideoResponse>{
    const formData = new FormData();
    formData.append('file', fileEntry, fileEntry.name);

    return this.httpClient.post<IUploadVideoResponse>(Globalcomponent.apiUrl + "/api/videos",formData);
        

  }

  uploadThumbnail(fileEntry: File, videoId: string) : Observable<string>{
    const formData = new FormData();
    formData.append('file', fileEntry, fileEntry.name);
    formData.append('videoId', videoId);



    return this.httpClient.post(Globalcomponent.apiUrl + "/api/videos/thumbnail",formData, {
      responseType: 'text'
    });
        
  }

  getVideo(videoId: string): Observable<IVideoDto> {

    return this.httpClient.get<IVideoDto>(Globalcomponent.apiUrl + "/api/videos/" + videoId);
  }

  saveVideo(videoMetaData: IVideoDto) : Observable<IVideoDto>{
    console.log(videoMetaData.videoStatus)
    return this.httpClient.put<IVideoDto>(Globalcomponent.apiUrl + "/api/videos",videoMetaData);
  }
}
