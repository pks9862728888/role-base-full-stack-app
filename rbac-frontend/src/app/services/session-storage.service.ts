import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SessionStorageService {

  constructor() {
  }

  save(key: string, value: string) {
    sessionStorage.setItem(key, value);
  }

  getString(key: string): string | null {
    return sessionStorage.getItem(key);
  }

  clearAll() {
    sessionStorage.clear();
  }
}
