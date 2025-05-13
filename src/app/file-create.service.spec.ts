import { TestBed } from '@angular/core/testing';

import { FileCreateService } from './file-create.service';

describe('FileCreateService', () => {
  let service: FileCreateService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FileCreateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
