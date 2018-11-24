import { Component, OnInit } from '@angular/core';
import { Book } from '../models/book';
import { AppConst } from '../constants/app-constant';
import { BookService } from '../services/book.service';
import { Params, Router, ActivatedRoute } from '@angular/router';
import { Http } from '@angular/http';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {

	public filterQuery = "";
	public rowsOnPage = 5;

	private selectedBook: Book;
	private bookList: Book[];
	private servePath = AppConst.servePath;

	constructor(
		private bookService:BookService,
		private router:Router,
		private http:Http,
		private route:ActivatedRoute
		) { }

	onSelect(book: Book) {
		this.selectedBook = book;
		this.router.navigate(['/bookDetail', this.selectedBook.id]);
	}

	ngOnInit() {
		this.route.queryParams.subscribe(params => {
			if(params['bookList']) {
				console.log("filtered book list");
				this.bookList = JSON.parse(params['bookList']);
			} else {
				this.bookService.getBookList().subscribe(
					res => {
						console.log(res.json());
						this.bookList = res.json();
					},
					err => {
						console.log(err);
					}
					);
			}
		});

	}
}


