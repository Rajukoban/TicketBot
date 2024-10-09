import { Component } from '@angular/core';
import { AuthService } from '../../../services/auth/auth.service';
import { ActivatedRoute } from '@angular/router';
import { Profile } from '../models/profile';
import { UserstorageService } from '../../../services/storage/userstorage.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {
  //user: Profile | undefined;
  user!:Profile;

  constructor(
    private userService: AuthService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    //const userId = Number(this.route.snapshot.paramMap.get('id'));
    const userId=parseInt(UserstorageService.getUserId());
    this.userService.getByUserId(userId).subscribe((data) => {
      this.user = data;
    });
  }

}
