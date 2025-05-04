import { User } from './user';

describe('User', () => {
  it('should create an instance', () => {
    expect(new User(
      1,
      "First",
      "Last",
      "email@mail.com",
      "passw0rd"
    )).toBeTruthy();
  });
});
