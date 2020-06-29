import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-menu-form',
  templateUrl: './menu-form.component.html',
  styleUrls: ['./menu-form.component.css']
})
export class MenuFormComponent {

  constructor(
    private route: ActivatedRoute, 
    private router: Router) {
  }
 
  onSubmit() {
	this.displayCurrentBuyingExchangeRates();
  }
 
  displayCurrentBuyingExchangeRates() {
    this.router.navigate(['/buy/current']);
  }

}
