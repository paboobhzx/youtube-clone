import { COMMA, ENTER } from '@angular/cdk/keycodes';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatChipEditedEvent, MatChipInputEvent } from '@angular/material/chips';
import { ActivatedRoute } from '@angular/router';
import { VideoService } from '../video.service';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-save-video-details',
  templateUrl: './save-video-details.component.html',
  styleUrls: ['./save-video-details.component.scss']
})

export class SaveVideoDetailsComponent implements OnInit{


  saveVideoDetailsForm: FormGroup;
  title: FormControl = new FormControl('')
  description: FormControl = new FormControl('')
  videoStatus: FormControl = new FormControl('')
  addOnBlur = true;
  readonly separatorKeysCodes = [ENTER, COMMA] as const;
  tags: string[] = [];
  selectedFile!: File;
  selectedFileName = '';
  videoId = '';
  fileSelected: boolean = false;

  constructor(private activatedRoute: ActivatedRoute,
     private videoService: VideoService,
     private matSnackBar: MatSnackBar) {
    this.videoId = this.activatedRoute.snapshot.params['videoId']; 
    this.saveVideoDetailsForm = new FormGroup ({
      title: this.title,
      description: this.description,
      status: this.videoStatus

    })
  }
  ngOnInit(): void { }

  add(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();

    // Add our fruit
    if (value) {
      this.tags.push(value);
    }

    // Clear the input value
    event.chipInput!.clear();
  }

  remove(value: string): void {
    const index = this.tags.indexOf(value);

    if (index >= 0) {
      this.tags.splice(index, 1);
    }
  }

  edit(currTag: string, event: MatChipEditedEvent) {
    
    const value = event.value.trim();    

    // Remove tag if it no longer has a name
    if (value.length < 1) {
      this.remove(currTag);
      return;
    }
    console.log("currTag" + currTag)

    // Edit existing tag

    const index = this.tags.indexOf(currTag)
    if(index > 0){
      this.tags[index] = value;
    }
      
    
  }  
  onFileSelected($event: Event) {
    // @ts-ignore
    this.selectedFile = $event.target.files[0];
    this.selectedFileName = this.selectedFile.name
    this.fileSelected = true;
    }
    onUpload(){
    this.videoService.uploadThumbnail(this.selectedFile, this.videoId)
    .subscribe(data => {
      
      //show notification
      this.matSnackBar.open("Thumbnail Uploaded Successfully","OK")
    })
    }
}