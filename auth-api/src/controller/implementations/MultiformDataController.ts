import { Request, Response } from 'express';
import AppValidationError from '../../errors/AppValidationError';
import BaseController from '../BaseController';

export default class MultiformDataController extends BaseController {
  protected async executeImpl(req: Request, res: Response<any, Record<string, any>>): Promise<Response<any, Record<string, any>>> {
    try {
      if (req.file) {
        const buffer = Buffer.from(req.file.buffer).toString('base64');

        return this.respondCreated(res, { buffer });
      }
      return this.respondCreated(res, { message: 'not have archive' });
    } catch (error: any) {
      if (error instanceof AppValidationError) {
        return this.respondValidationError(res, error);
      }
      return this.respondError(res, error);
    }
  }
}
