import { Response } from 'express';
import AppError from '../errors/AppError';
import AppValidationError from '../errors/AppValidationError';

export default class BaseResponse {
  public async respondSuccess(res: Response, payload: Record<string, any>): Promise<Response<any, Record<string, any>>> {
    return res.status(200).json(payload);
  }

  public async respondCreated(res: Response, payload: Record<string, any>): Promise<Response<any, Record<string, any>>> {
    return res.status(201).json(payload);
  }

  public async respondForbiden(res: Response, payload: Record<string, any>): Promise<Response<any, Record<string, any>>> {
    return res.status(403).json(payload);
  }

  public async respondUnAuthrorized(res: Response, payload: Record<string, any>): Promise<Response<any, Record<string, any>>> {
    return res.status(401).json(payload);
  }

  public async respondError(res: Response, err: AppError | AppValidationError): Promise<Response<any, Record<string, any>>> {
    console.log(err);
    const status = err.statusCode ?? 500;

    if (err instanceof AppError) {
      return res.status(status).json({ success: false, message: err.message });
    }

    return res.status(status).json({ success: false, errors: err.errors });
  }

  public async respondValidationError(res: Response, err: AppValidationError): Promise<Response<any, Record<string, any>>> {
    return res.status(400).json({
      success: false,
      message: 'Validation error',
      errors: err.errors,
    });
  }
}
