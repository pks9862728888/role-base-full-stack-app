import {Routes} from '@angular/router';
import {HomepageComponent} from "./components/homepage/homepage.component";
import {LoginComponent} from "./components/auth/login/login.component";
import {UserRegistrationComponent} from "./components/user-registration/user-registration.component";

export const routes: Routes = [
  {path: '', component: HomepageComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register/user', component: UserRegistrationComponent},
  {path: 'dashboard', component: HomepageComponent}
];
