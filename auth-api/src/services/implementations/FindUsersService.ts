import { RequestUsersDTO } from '../../models/dto/RequestAuthDTO';
import { IFindUsersRepository } from '../../repositories/IFindUsersRepository';
import IFindUsersService from '../IFindAuthService';
import Users from '../../models/Users';
import { authMessagesRelated } from '../../helper/message';
import AppValidationError from '../../errors/AppValidationError';

export default class FindUsersService implements IFindUsersService {
  private findaUsersRepositoy: IFindUsersRepository;

  constructor(aFindUsersRepository: IFindUsersRepository) {
    this.findaUsersRepositoy = aFindUsersRepository;
  }
  public async execute({ email, password }: RequestUsersDTO): Promise<Users> {
    const users = await this.findaUsersRepositoy.findUsers({ email });
    if (!users) {
      throw new AppValidationError(authMessagesRelated.errorFindUser);
    }
    return users;
  }
}
