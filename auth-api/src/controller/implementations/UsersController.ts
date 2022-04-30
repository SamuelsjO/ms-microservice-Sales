import { Request, Response } from 'express';
import AppValidationError from '../../errors/AppValidationError';
import { RequestUsersDTO } from '../../models/dto/RequestAuthDTO';
import IUsersService from '../../services/IUsersServices';
import BaseController from '../BaseController';

export default class UsersController extends BaseController {
  private usersService: IUsersService;

  constructor(aUsersService: IUsersService) {
    super();
    this.usersService = aUsersService;
  }
  protected async executeImpl(req: Request, res: Response<any, Record<string, any>>): Promise<Response<any, Record<string, any>>> {
    try {
      const users: RequestUsersDTO = req.body;
      console.log(users)
      const usersCreate = await this.usersService.execute(users);
      return this.respondCreated(res, usersCreate);
    } catch (error: any) {
      if (error instanceof AppValidationError) {
        return this.respondValidationError(res, error);
      }
      return this.respondError(res, error);
    }
  }
}
