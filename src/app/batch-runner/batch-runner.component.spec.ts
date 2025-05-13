import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BatchRunnerComponent } from './batch-runner.component';

describe('BatchRunnerComponent', () => {
  let component: BatchRunnerComponent;
  let fixture: ComponentFixture<BatchRunnerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BatchRunnerComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BatchRunnerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
