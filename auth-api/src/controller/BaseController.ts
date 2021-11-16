import { Request, Response } from 'express';
import BaseResponse from '../BaseResponse/BaseResponse';

export default abstract class BaseController extends BaseResponse {
  protected abstract executeImpl(req: Request, res: Response): Promise<Response<any, Record<string, any>>>;

  public async execute(req: Request, res: Response): Promise<Response<any, Record<string, any>>> {
    try {
      return this.executeImpl(req, res);
    } catch (error: any) {
      console.log(`[BaseController]: Uncaught controller error`);
      return this.respondError(res, error);
    }
  }
}
