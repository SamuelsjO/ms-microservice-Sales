import { getRepository } from 'typeorm';
import { RequestUsersDTO } from '../../models/dto/RequestAuthDTO';
import Users from '../../models/Users';
import IUsersRepository from '../IUsersRepository';

export default class UsersRepository implements IUsersRepository {
  async save(users: RequestUsersDTO): Promise<Users> {
    const rep = getRepository(Users);
    return rep.save(users);
  }
}
