import { Injectable, ErrorHandler, Injector } from '@angular/core';
import { RouterTestingModule } from '@angular/router/testing';
import { TestBed, inject } from '@angular/core/testing';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { CustomErrorHandlerService } from './custom-error-handler.service';

describe('CustomErrorHandlerService', () => {

    const httpErrorResponse = new HttpErrorResponse({
        error: '404 error',
        status: 404, statusText: 'Not Found'
    });

    const otherError: Error = new Error('ERROR');

    let router: Router;

    beforeEach(() => {
        TestBed.configureTestingModule({
            imports: [
                RouterTestingModule.withRoutes([]),
            ],
            providers: [
                CustomErrorHandlerService,
                Injector,
            ]
        }).compileComponents();
        router = TestBed.get(Router);
    });

    it('should be created', inject([CustomErrorHandlerService], (service: CustomErrorHandlerService) => {
        expect(service).toBeDefined();
    }));

    it('should handle HTTP error', inject([CustomErrorHandlerService], (service: CustomErrorHandlerService) => {
        const spyConsole = spyOn(console, 'error');
        const spyRouter = spyOn(router, 'navigate');
        service.handleError(httpErrorResponse);
        expect(spyConsole).toHaveBeenCalledWith('Error status code: ', httpErrorResponse.status);
        expect(spyConsole).toHaveBeenCalledWith(httpErrorResponse.message);
        expect(spyRouter).toHaveBeenCalledWith(['/error']);
    }));

    it('should handle other error', inject([CustomErrorHandlerService], (service: CustomErrorHandlerService) => {
        const spyConsole = spyOn(console, 'error');
        const spyRouter = spyOn(router, 'navigate');
        service.handleError(otherError);
        expect(spyConsole).toHaveBeenCalledWith(otherError.message);
        expect(spyRouter).toHaveBeenCalledWith(['/error']);
    }));

});
