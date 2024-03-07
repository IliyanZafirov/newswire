import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Article } from '../model/article.model';

const baseUrl = 'http://localhost:8080/api/v1/articles';
@Injectable({
  providedIn: 'root'
})
export class ArticleService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<Article[]> {
    return this.http.get<Article[]>(baseUrl);
  }

  get(id: number): Observable<Article> {
    return this.http.get<Article>(`${baseUrl}/${id}`);
  }
}
