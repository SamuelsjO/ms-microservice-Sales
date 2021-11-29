import { Request, Response } from 'express';
import AppValidationError from '../../errors/AppValidationError';
import IFindUsersService from '../../services/IFindAuthService';
import BaseController from '../BaseController';
import bcrypt from 'bcrypt';
import jwt from 'jsonwebtoken';
export default class GetAcessTokenController extends BaseController {
  private findUsersService: IFindUsersService;

  constructor(aFindUsersService: IFindUsersService) {
    super();
    this.findUsersService = aFindUsersService;
  }
  protected async executeImpl(req: Request, res: Response<any, Record<string, any>>): Promise<Response<any, Record<string, any>>> {
    try {
      const { email, password } = req.body;

      const users = await this.findUsersService.execute({ email });

      if (!users) {
        return res.sendStatus(401);
      }

      const isValidPassword = await bcrypt.compare(password, users.password);

      if (!isValidPassword) {
        return res.sendStatus(401);
      }

      const token = jwt.sign({ id: users.id }, 'secret', { expiresIn: '1d' });

      delete users.password;

      const json = res.json({
        users,
        token,
      });

      return json;
    } catch (error: any) {
      console.log(error);
      if (error instanceof AppValidationError) {
        return this.respondValidationError(res, error);
      }
      return this.respondError(res, error);
    }
  }
}
