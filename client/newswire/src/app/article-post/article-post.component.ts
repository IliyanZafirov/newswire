import { Component } from '@angular/core';
import { ArticleService } from '../article-service/article.service';
import { Router } from '@angular/router';
import { Article} from '../model/article.model';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-article-post',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './article-post.component.html',
  styleUrl: './article-post.component.css'
})
export class ArticlePostComponent {
  article: Article=<Article>{};
  
  constructor(private articleService: ArticleService, private router: Router) {}

  articleForm = new FormGroup ({
    title: new FormControl('',  {nonNullable: true}),
    content: new FormControl('',  {nonNullable: true}),
    category: new FormControl('',  {nonNullable: true}),
    publicationDate: new FormControl('',  {nonNullable: true}),
    imageURL: new FormControl('',  {nonNullable: true})
  });

  onSubmit() {
    this.article = this.articleForm.value as Article;
    this.articleService.post(this.article).subscribe({
      next: () => {
        this.router.navigate(["/"]);
      },
      error: (error) => {
        console.error(error);
      }
    });
  }
  
}
