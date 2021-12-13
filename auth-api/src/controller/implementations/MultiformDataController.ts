import { Request, Response } from 'express';
import AppValidationError from '../../errors/AppValidationError';
import BaseController from '../BaseController';
import base64topdf from 'pdf-to-base64';

export default class MultiformDataController extends BaseController {
  protected async executeImpl(req: Request, res: Response<any, Record<string, any>>): Promise<Response<any, Record<string, any>>> {
    try {
      if (req.file) {
        const arq = req.file.buffer;

        const hash = base64topdf(arq);

        console.log('hash :>> ', await hash);
        return this.respondCreated(res, hash);
      }
    } catch (error: any) {
      if (error instanceof AppValidationError) {
        return this.respondValidationError(res, error);
      }
      return this.respondError(res, error);
    }
  }
}
