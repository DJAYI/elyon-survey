import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SurveyTabEngineComponent } from './survey-tab-engine.component';

describe('SurveyTabEngineComponent', () => {
  let component: SurveyTabEngineComponent;
  let fixture: ComponentFixture<SurveyTabEngineComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SurveyTabEngineComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SurveyTabEngineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
