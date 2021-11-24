import { RequestUsersDTO } from '../models/dto/RequestAuthDTO';
import Users from '../models/Users';

export default interface IUsersRepository {
  save(users: RequestUsersDTO): Promise<Users>;
}
