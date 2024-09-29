import {Component} from '@angular/core';
import {NgIconComponent, provideIcons} from '@ng-icons/core';
import {tablerLogin, tablerLogout} from '@ng-icons/tabler-icons';
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [NgIconComponent],
  providers: [provideIcons({tablerLogin, tablerLogout})],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent {

  constructor(private authService: AuthService,
              private router: Router) {
  }

  loginOrLogout() {
    if (this.loggedIn()) {
      this.authService.logout();
    }
    return this.router.navigateByUrl("login");
  }

  loggedIn(): boolean {
    return this.authService.authTokenFound();
  }
}
