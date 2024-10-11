import {inject, Injectable, PLATFORM_ID} from '@angular/core';
import {HttpConstants} from "../models/http-constants";
import {isPlatformBrowser} from "@angular/common";

@Injectable({
  providedIn: 'root'
})
export class SessionStorageService {
  private readonly platformId = inject(PLATFORM_ID);

  constructor() {
  }

  save(key: string, value: string) {
    if (isPlatformBrowser(this.platformId)) {
      sessionStorage.setItem(key, value);
    } else {
      console.log(`Skipped saving value in sessionStorage having key: ` + key);
    }
  }

  getString(key: string): string | null {
    if (isPlatformBrowser(this.platformId)) {
      return sessionStorage.getItem(key);
    }
    return null;
  }

  clearAll() {
    if (isPlatformBrowser(this.platformId)) {
      sessionStorage.clear();
    }
  }

  saveAuthToken(token: string): void {
    this.save(this.getAuthTokenKey(), token);
  }

  getAuthToken(): string | null {
    return this.getString(this.getAuthTokenKey());
  }

  private getAuthTokenKey() {
    return HttpConstants.AUTHORIZATION;
  }
}
