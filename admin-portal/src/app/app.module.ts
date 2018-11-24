import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { MatToolbarModule, MatCheckboxModule, MatButtonModule } from '@angular/material';
import {MatGridListModule, MatCardModule, MatSelectModule} from '@angular/material';
import {MatListModule} from '@angular/material/list';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatDialogModule} from '@angular/material/dialog';




import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { LoginComponent } from './login/login.component';
import { LoginService } from './services/login.service';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { HttpModule } from '@angular/http';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { AddNewBookComponent } from './add-new-book/add-new-book.component';
import { AddBookService } from './services/add-book.service';
import { UploadImageService } from './services/upload-image.service';
import { DialogResultExampleDialog, BookListComponent } from './book-list/book-list.component';
import { GetBookListService } from './services/get-book-list.service';
import { ViewBookComponent } from './view-book/view-book.component';
import { GetBookService } from './services/get-book.service';
import { EditBookComponent } from './edit-book/edit-book.component';
import { EditBookService } from './services/edit-book.service';
import { RemoveBookService } from './services/remove-book.service';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent,
    AddNewBookComponent,
    BookListComponent,
    DialogResultExampleDialog,
    ViewBookComponent,
    EditBookComponent
  ],
  imports: [
    MatButtonModule,
    MatDialogModule,
    MatListModule,
    MatFormFieldModule,
    MatCheckboxModule,
    MatGridListModule,
    MatSelectModule,
    MatCardModule,
    BrowserModule,
    FormsModule,
    HttpModule,
    MDBBootstrapModule.forRoot(),
    RouterModule.forRoot([
      { path : '', component : LoginComponent},
      { path : 'home', component: BookListComponent},
      { path : 'addNewBook', component : AddNewBookComponent},
      { path :'bookList', component : BookListComponent},
      { path : 'viewBook/:id', component : ViewBookComponent},
      { path : 'editBook/:id', component : EditBookComponent}
    ]),
    MatToolbarModule,    
    BrowserAnimationsModule,
  ],
  providers: [
    LoginService,
    AddBookService,
    UploadImageService,
    GetBookListService,
    GetBookService,
    EditBookService,
    RemoveBookService
  ],
  bootstrap: [AppComponent,DialogResultExampleDialog]
})
export class AppModule { }
