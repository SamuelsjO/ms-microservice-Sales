import { RequestUsersDTO } from '../models/dto/RequestAuthDTO';
import Users from '../models/Users';

export default interface IUsersService {
  execute(users: RequestUsersDTO): Promise<Users>;
}
