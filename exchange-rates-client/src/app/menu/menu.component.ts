import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { Currency } from '../model/currency';
import { ExchangeRateService } from '../service/exchange-rate.service';
import { CurrencyService } from '../service/currency.service';

@Component({
    selector: 'app-menu',
    templateUrl: './menu.component.html',
    styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

    menuForm: FormGroup;
    allCurrencies: Currency[];
    candidateComparisonCurrencies: Currency[];
    private masterSubscription: Subscription = new Subscription();

    constructor(private fb: FormBuilder,
        private exchangeRateService: ExchangeRateService, private currencyService: CurrencyService) {
        console.log('DEBUG: Constructing MenuComponent');
        this.buildForm();
    }

    ngOnInit() {
        const defaultAllCurrenciesSubscription = this.currencyService.fetchAllCurrencies().subscribe(data => {
            this.exchangeRateService.setAllCurrencies(data);
            this.buildForm();
        });
        this.masterSubscription.add(defaultAllCurrenciesSubscription);
        const defaultSubjectCurrencySubscription = this.currencyService.fetchDefaultSubjectCurrency().subscribe(data => {
            this.exchangeRateService.setSubjectCurrency(data);
            this.buildForm();
        });
        this.masterSubscription.add(defaultSubjectCurrencySubscription);
        const defaultComparisonCurrenciesSubscription = this.currencyService.fetchDefaultComparisonCurrencies().subscribe(data => {
            this.exchangeRateService.setComparisonCurrencies(data);
            this.buildForm();
        });
        this.masterSubscription.add(defaultComparisonCurrenciesSubscription);
    }

    buildForm() {
        this.menuForm = this.fb.group({
            subjectCurrency: [this.exchangeRateService.getSubjectCurrency(), Validators.required],
            comparisonCurrencies: this.fb.array([Validators.required])
        });
        this.comparisonCurrencies.removeAt(0);
        for (let i = 0; i < this.exchangeRateService.getComparisonCurrencies().length; i++) {
            this.comparisonCurrencies.push(this.fb.control(this.exchangeRateService.getComparisonCurrencies()[i]));
        }
        this.allCurrencies = this.exchangeRateService.getAllCurrencies();
        this.candidateComparisonCurrencies = this.exchangeRateService.getCandidateComparisonCurrencies();
        this.addComparisonCurrencySelectIfRequired();
        const formChangesSubscription = this.menuForm.valueChanges.subscribe(data => {
            this.exchangeRateService.setSubjectCurrency(this.menuForm.controls.subjectCurrency.value);
            for (let i = 0; i < data.comparisonCurrencies.length; i++) {
                if (data.comparisonCurrencies[i].id === data.subjectCurrency.id) {
                    this.deleteComparisonCurrency(i);
                }
            }
            const comparisonCurrencies: Currency[] = [];
            for (let i = 0; i < (<FormArray>this.menuForm.get('comparisonCurrencies')).length; i++) {
                if ((<FormArray>this.menuForm.get('comparisonCurrencies')).at(i).value !== '') {
                    comparisonCurrencies.push((<FormArray>this.menuForm.get('comparisonCurrencies')).at(i).value);
                }
            }
            this.exchangeRateService.setComparisonCurrencies(comparisonCurrencies);
            this.exchangeRateService.setCandidateComparisonCurrencies();
            this.candidateComparisonCurrencies = this.exchangeRateService.getCandidateComparisonCurrencies();
            this.addComparisonCurrencySelectIfRequired();
        });
        this.masterSubscription.add(formChangesSubscription);
    }

    get comparisonCurrencies() {
        return this.menuForm.get('comparisonCurrencies') as FormArray;
    }

    get subjectCurrency() {
        return this.menuForm.get('subjectCurrency') as FormControl;
    }

    addComparisonCurrencySelectIfRequired() {
        for (let i = 0; i < (<FormArray>this.menuForm.get('comparisonCurrencies')).length; i++) {
            if ((<FormArray>this.menuForm.get('comparisonCurrencies')).at(i).value === '') {
                return;
            }
        }
        if ((this.candidateComparisonCurrencies === undefined) || (this.candidateComparisonCurrencies === null)
            || (this.candidateComparisonCurrencies.length === 0)) {
            return;
        }
        this.comparisonCurrencies.push(this.fb.control(''));
    }

    deleteComparisonCurrency(index: number) {
        this.comparisonCurrencies.removeAt(index);
        const comparisonCurrencies: Currency[] = [];
        for (let i = 0; i < (<FormArray>this.menuForm.get('comparisonCurrencies')).length; i++) {
            if ((<FormArray>this.menuForm.get('comparisonCurrencies')).at(i).value !== '') {
                comparisonCurrencies.push((<FormArray>this.menuForm.get('comparisonCurrencies')).at(i).value);
            }
        }
        this.exchangeRateService.setComparisonCurrencies(comparisonCurrencies);
        this.exchangeRateService.setCandidateComparisonCurrencies();
        this.candidateComparisonCurrencies = this.exchangeRateService.getCandidateComparisonCurrencies();
    }

    compareCurrency(currency1: any, currency2: any): boolean {
        return currency1 && currency2 ? currency1.id === currency2.id : currency1 === currency2;
    }

}
