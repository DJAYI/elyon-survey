import { NgClass } from '@angular/common';
import { Component } from '@angular/core';
import { SurveyTabEngineComponent } from './survey-tab-engine/survey-tab-engine.component';
import { SurveyTableComponent } from './survey-table/survey-table.component';

@Component({
    selector: 'app-surveys',
    imports: [SurveyTabEngineComponent, SurveyTableComponent, NgClass],
    templateUrl: './surveys.component.html',
    styleUrl: './surveys.component.css'
})
export class SurveysComponent {
    showTabEngine: boolean;

    constructor() {
        this.showTabEngine = false;
    }

    handleCreateSurvey() {
        this.showTabEngine = !this.showTabEngine;
    }

}
