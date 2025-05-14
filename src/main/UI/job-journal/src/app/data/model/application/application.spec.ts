import { Status } from '../status/status';
import { Application } from './application';

describe('Application', () => {

  it('should create an instance', () => {
    expect(
      
      new Application(
          1,
          new Date(),
          new Date(),
          "jobTitle",
          "employer",
          new Date(),
          new Date(),
          null,
          new Status(-1, "TEST"),
          -1
        )).toBeTruthy();
  });
});
