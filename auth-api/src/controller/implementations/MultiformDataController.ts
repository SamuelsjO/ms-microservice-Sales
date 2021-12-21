import { Request, Response } from 'express';
import AppValidationError from '../../errors/AppValidationError';
import BaseController from '../BaseController';

export default class MultiformDataController extends BaseController {
  protected async executeImpl(req: Request, res: Response<any, Record<string, any>>): Promise<Response<any, Record<string, any>>> {
    try {
      return this.respondCreated(res, { message: [
        req.body.clinicalEvolution, 
        req.body.clinicalEvolution, 
        req.body.imageResult] });
    } catch (error: any) {
      if (error instanceof AppValidationError) {
        return this.respondValidationError(res, error);
      }
      return this.respondError(res, error);
    }
  }
}
