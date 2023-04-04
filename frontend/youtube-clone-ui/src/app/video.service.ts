import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FileSystemFileEntry } from 'ngx-file-drop';
import { Observable } from 'rxjs';
import { Globalcomponent } from '../global/global-component'
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
}
