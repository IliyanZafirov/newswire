import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { Article} from '../model/article.model';

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

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${baseUrl}/${id}`);
  }

  post(article: Article): Observable<Article> {
    return this.http.post<Article>(`${baseUrl}`, article);
  }

  update(id: number, updatedArticle: Article) {
    return this.http.put<Article>(`${baseUrl}/${id}`, updatedArticle);
  }
}
