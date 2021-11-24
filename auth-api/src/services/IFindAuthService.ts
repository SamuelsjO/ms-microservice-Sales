import { RequestUsersDTO } from '../models/dto/RequestAuthDTO';
import Users from '../models/Users';

export default interface IFindUsersService {
  execute({ email }: RequestUsersDTO): Promise<Users>;
}
