import { TestBed } from '@angular/core/testing';

import { BatchRunnerService } from './batch-runner.service';

describe('BatchRunnerService', () => {
  let service: BatchRunnerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BatchRunnerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
