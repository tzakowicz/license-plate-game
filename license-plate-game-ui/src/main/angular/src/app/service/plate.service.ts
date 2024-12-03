import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { firstValueFrom, retry, RetryConfig } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PlateService {

  constructor(private client: HttpClient) { }

  private retryConfig: RetryConfig = {count: 5, delay: 500};

  getMethod<O extends Object>(url: string, options: object = {}): Promise<O> {
    return firstValueFrom(this.client.get<O>(url, options).pipe(retry(this.retryConfig)));
  }

  postMethod<O extends Object>(url: string, body: any, options: object = {}): Promise<O> {
    return firstValueFrom(this.client.post<O>(url, body, options).pipe(retry(this.retryConfig)));
  }
}