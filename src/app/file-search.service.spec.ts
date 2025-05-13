import { TestBed } from '@angular/core/testing';

import { FileSearchService } from './file-search.service';

describe('FileSearchService', () => {
  let service: FileSearchService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FileSearchService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
