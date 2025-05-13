import { TestBed } from '@angular/core/testing';

import { FileDetailsService } from './file-details.service';

describe('FileDetailsService', () => {
  let service: FileDetailsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FileDetailsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
