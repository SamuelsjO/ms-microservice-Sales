import { RequestUsersDTO } from '../models/dto/RequestAuthDTO';
import Users from '../models/Users';

export interface IFindUsersRepository {
  findUsers({ email }: RequestUsersDTO): Promise<Users | undefined>;
}
