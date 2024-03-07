import { Component, Input } from '@angular/core';
import { Article } from '../model/article.model';
import {MatCardModule} from '@angular/material/card'; 
import { RouterLink } from '@angular/router';
import { NgOptimizedImage } from '@angular/common';

@Component({
  selector: 'app-article-card',
  standalone: true,
  imports: [MatCardModule, RouterLink, NgOptimizedImage],
  templateUrl: './article-card.component.html',
  styleUrl: './article-card.component.css'
})
export class ArticleCardComponent {
  @Input() article: Article | undefined;
}
