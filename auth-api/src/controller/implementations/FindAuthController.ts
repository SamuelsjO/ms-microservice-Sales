import { Request, Response } from 'express';
import BaseController from '../BaseController';
import AppValidationError from '../../errors/AppValidationError';
import IFindUsersService from '../../services/IFindAuthService';

export default class FindUsersController extends BaseController {
  private findUsersService: IFindUsersService;

  constructor(aFindUsersService: IFindUsersService) {
    super();
    this.findUsersService = aFindUsersService;
  }
  protected async executeImpl(req: Request, res: Response<any, Record<string, any>>): Promise<Response<any, Record<string, any>>> {
    try {
      const { email } = req.body;
      console.log(email);
      const users = await this.findUsersService.execute({ email });

      return this.respondSuccess(res, users);
    } catch (error: any) {
      console.log(error);
      if (error instanceof AppValidationError) {
        return this.respondValidationError(res, error);
      }
      return this.respondError(res, error);
    }
  }
}
