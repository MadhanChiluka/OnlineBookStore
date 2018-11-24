import { Component, OnInit } from '@angular/core';
import { Params, Router, ActivatedRoute } from '@angular/router';
import { EditBookService } from '../services/edit-book.service';
import { Book } from '../models/book';
import { UploadImageService } from '../services/upload-image.service';
import { GetBookService } from '../services/get-book.service';

@Component({
  selector: 'app-edit-book',
  templateUrl: './edit-book.component.html',
  styleUrls: ['./edit-book.component.scss']
})
export class EditBookComponent implements OnInit {
  private bookId : number;
  private book : Book = new Book();
  private bookUpdated : boolean;
  constructor(
    private uploadImageService : UploadImageService,
    private getBookService : GetBookService,
    private route : ActivatedRoute,
    private router : Router,
    private editBookService : EditBookService
  ) { }
  onEdit() {
    this.editBookService.sendBook(this.book).subscribe( data => {
      this.uploadImageService.modify(JSON.parse(JSON.parse(JSON.stringify(data))));
      this.bookUpdated = true;
    },
    error => {
      console.log(error);
    })
  }
  ngOnInit() {
    this.route.params.forEach((params : Params) => {
      this.bookId = Number.parseInt(params['id']);
    });

    this.getBookService.getBook(this.bookId).subscribe( res=> {
      this.book = res.json();
      console.log(this.book)
    },
    error => {
      console.log(error)
    })
  }

}
