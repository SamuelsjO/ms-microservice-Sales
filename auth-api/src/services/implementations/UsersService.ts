import { authMessagesRelated } from '../../helper/message';
import AppError from '../../errors/AppError';
import IUsersRepository from '../../repositories/IUsersRepository';
import { RequestUsersDTO } from '../../models/dto/RequestAuthDTO';
import Users from '../../models/Users';
import AppValidationError from '../../errors/AppValidationError';
import IUsersService from '../IUsersServices';
import { IFindUsersRepository } from '../../repositories/IFindUsersRepository';
import { hashSync } from 'bcrypt';

export default class UsersService implements IUsersService {
  private usersRepository: IUsersRepository;
  private findUsersRepository: IFindUsersRepository;

  constructor(aUsersRepository: IUsersRepository, aFindUsersRepository: IFindUsersRepository) {
    this.usersRepository = aUsersRepository;
    this.findUsersRepository = aFindUsersRepository;
  }
  public async execute(users: RequestUsersDTO): Promise<Users> {
    try {
      if (users.password) users.password = hashSync(users.password, 10);

      const { email } = users;
      const userExist = await this.findUsersRepository.findUsers({ email });

      if (userExist) {
        throw new AppValidationError(authMessagesRelated.errorUserExists);
      }

      return await this.usersRepository.save(users);
    } catch (error: any) {
      console.log(error);
      if (error instanceof AppError) {
        throw new AppError(error.message);
      }
      throw new AppValidationError(authMessagesRelated.errorSaveUser);
    }
  }
}
