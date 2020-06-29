import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { Currency } from '../model/currency';
import { ExchangeRateService } from '../service/exchange-rate.service';
import { CurrencyService } from '../service/currency.service';

@Component({
  selector: 'app-menu-reactive-form',
  templateUrl: './menu-reactive-form.component.html',
  styleUrls: ['./menu-reactive-form.component.css']
})
export class MenuReactiveFormComponent {
	
  menuForm: FormGroup;
  allCurrencies: Currency[];
  candidateComparisonCurrencies: Currency[];
  private masterSubscription: Subscription = new Subscription();
  
  constructor(private fb: FormBuilder, private route: ActivatedRoute, private router: Router, 
      private exchangeRateService: ExchangeRateService, private currencyService: CurrencyService) 
  { 
    console.log('DEBUG: Constructing MenuReactiveFormComponent');
	this.buildForm();
  }
  
  ngOnInit() {
	const defaultAllCurrenciesSubscription = this.currencyService.fetchAllCurrencies().subscribe(data => {
	  this.exchangeRateService.setAllCurrencies(data);
	  this.buildForm();
    }); 
	this.masterSubscription.add(defaultAllCurrenciesSubscription);
	if ((this.exchangeRateService.getSubjectCurrency() === undefined) || (this.exchangeRateService.getSubjectCurrency().id === undefined)) {
	  const defaultSubjectCurrencySubscription = this.currencyService.fetchDefaultSubjectCurrency().subscribe(data => {
	    this.exchangeRateService.setSubjectCurrency(data);
	    this.buildForm();
      }); 
	  this.masterSubscription.add(defaultSubjectCurrencySubscription);
	} 
	if ((this.exchangeRateService.getComparisonCurrencies() === undefined) || (this.exchangeRateService.getComparisonCurrencies().length === 0)) {	
	  const defaultComparisonCurrenciesSubscription = this.currencyService.fetchDefaultComparisonCurrencies().subscribe(data => {
 	    this.exchangeRateService.setComparisonCurrencies(data);
	    this.buildForm();
      });
	  this.masterSubscription.add(defaultComparisonCurrenciesSubscription);
    }
  }
  
  buildForm() 
  { 
 	this.menuForm = this.fb.group({
	  subjectCurrency: [this.exchangeRateService.getSubjectCurrency(), Validators.required],
	  comparisonCurrencies: this.fb.array([Validators.required])
	});
	this.comparisonCurrencies.removeAt(0);
	for (let i = 0; i < this.exchangeRateService.getComparisonCurrencies().length; i++) {
	   this.comparisonCurrencies.push(this.fb.control(this.exchangeRateService.getComparisonCurrencies()[i]));
    }
	this.addComparisonCurrency();
	this.allCurrencies = this.exchangeRateService.getAllCurrencies();
	this.candidateComparisonCurrencies = this.exchangeRateService.getCandidateComparisonCurrencies();
	const formChangesSubscription = this.menuForm.valueChanges.subscribe(data => { 
	  this.exchangeRateService.setSubjectCurrency(this.menuForm.controls.subjectCurrency.value);
	  this.candidateComparisonCurrencies = this.exchangeRateService.getCandidateComparisonCurrencies();
	  let comparisonCurrencyToDeleteIndex = -1;
	  let hasEmptyComparisonCurrency = false;
	  for (let i = 0; i < data.comparisonCurrencies.length; i++) {
		if (data.comparisonCurrencies[i].id === undefined){
          hasEmptyComparisonCurrency = true;
        }			
		if (data.comparisonCurrencies[i].id === data.subjectCurrency.id){
		  this.deleteComparisonCurrency(i);
		}
      }
      if (hasEmptyComparisonCurrency === false){
        this.addComparisonCurrency();
      }		  
    });
	this.masterSubscription.add(formChangesSubscription);
  }
  
  onSubmit() {
	let comparisonCurrencies: Currency[] = [];
	let validForm = false;
	for (let i = 0; i < (<FormArray>this.menuForm.get('comparisonCurrencies')).length; i++) {
	  if ((<FormArray>this.menuForm.get('comparisonCurrencies')).at(i).value != ''){	
	    validForm = true;	
	    comparisonCurrencies.push((<FormArray>this.menuForm.get('comparisonCurrencies')).at(i).value);
	  }
    }
	if (validForm){
	  this.exchangeRateService.setSubjectCurrency(this.menuForm.controls.subjectCurrency.value);
	  this.exchangeRateService.setComparisonCurrencies(comparisonCurrencies);
	  this.displayCurrentBuyingExchangeRates();
	} else {
	  this.comparisonCurrencies.setErrors({
        required: true
      });
    } 
  }
  
  get comparisonCurrencies() {
    return this.menuForm.get('comparisonCurrencies') as FormArray;
  }
  
  addComparisonCurrency() {
    this.comparisonCurrencies.push(this.fb.control(''));
  }
  
  deleteComparisonCurrency(index: number) {
    this.comparisonCurrencies.removeAt(index);
	if ((<FormArray>this.menuForm.get('comparisonCurrencies')).length === 0){
	   this.addComparisonCurrency();
	}	  	
  }
  
  displayCurrentBuyingExchangeRates() {
    this.router.navigate(['/buy/current']);
  }
  
  compareCurrency(currency1: any, currency2:any): boolean {
     return currency1 && currency2 ? currency1.id === currency2.id : currency1 === currency2; 
  }
 
}