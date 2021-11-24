import { authMessagesRelated } from '../../helper/message';
import AppError from '../../errors/AppError';
import IUsersRepository from '../../repositories/IUsersRepository';
import { RequestUsersDTO } from '../../models/dto/RequestAuthDTO';
import Users from '../../models/Users';
import AppValidationError from '../../errors/AppValidationError';
import IUsersService from '../IUsersServices';

export default class UsersService implements IUsersService {
  private usersRepository: IUsersRepository;

  constructor(aUsersRepository: IUsersRepository) {
    this.usersRepository = aUsersRepository;
  }
  public async execute(users: RequestUsersDTO): Promise<Users> {
    try {
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
