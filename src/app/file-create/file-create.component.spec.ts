import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FileCreateComponent } from './file-create.component';

describe('FileCreateComponent', () => {
  let component: FileCreateComponent;
  let fixture: ComponentFixture<FileCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FileCreateComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FileCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
