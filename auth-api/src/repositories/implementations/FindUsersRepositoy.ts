import { getRepository } from 'typeorm';
import { RequestUsersDTO } from '../../models/dto/RequestAuthDTO';
import Users from '../../models/Users';
import { IFindUsersRepository } from '../IFindUsersRepository';


export default class FindUsersRepositoy implements IFindUsersRepository {
  public async findUsers({ email }: RequestUsersDTO): Promise<Users | undefined> {
    const rep = getRepository(Users);
    const found = await rep.findOne({ where: { email: email } });
    return found;
  }
}
