import { Request, Response } from 'express';
import AppValidationError from '../../errors/AppValidationError';
import { multiformaDTO } from '../../models/dto/multiformaDTO';
import BaseController from '../BaseController';
import base64topdf from 'pdf-to-base64';

export default class MultiformDataController extends BaseController {
  // private usersService: IUsersService;

  // constructor(aUsersService: IUsersService) {
  //   super();
  //   this.usersService = aUsersService;
  // }
  protected async executeImpl(req: Request, res: Response<any, Record<string, any>>): Promise<Response<any, Record<string, any>>> {
    try {
      const arquive: multiformaDTO = req.params;

      console.log('arquive :>> ', arquive);

      const hash = base64topdf(arquive.archive);

      // const hash = base64topdf('sample.pdf');

      console.log('hash :>> ', await hash);

      return this.respondCreated(res, hash);
    } catch (error: any) {
      if (error instanceof AppValidationError) {
        return this.respondValidationError(res, error);
      }
      return this.respondError(res, error);
    }
  }
}
