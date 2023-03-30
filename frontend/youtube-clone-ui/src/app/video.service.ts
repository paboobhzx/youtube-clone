import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FileSystemFileEntry } from 'ngx-file-drop';
import { Observable } from 'rxjs';
import { Globalcomponent } from './global/global-components';

@Injectable({
  providedIn: 'root'
})
export class VideoService {

  constructor(private httpClient: HttpClient) { }

  uploadVideo(fileEntry: File) : Observable<any>{
    const formData = new FormData();
    formData.append('file', fileEntry, fileEntry.name);

    return this.httpClient.post(Globalcomponent.apiUrl + "/api/videos", formData)
        

  }
}


