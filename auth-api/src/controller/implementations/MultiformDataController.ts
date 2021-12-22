import { Request, Response } from 'express';
import AppValidationError from '../../errors/AppValidationError';
import BaseController from '../BaseController';

export default class MultiformDataController extends BaseController {
  protected async executeImpl(req: Request, res: Response<any, Record<string, any>>): Promise<Response<any, Record<string, any>>> {
    try {
      if (req.files) {
        const clinicalEvolution = 'ClinicalEvolution: ' + req.body.clinicalEvolution;
        const labReportResult = 'LabResportResult: ' + req.body.labReportResult;
        const imageResult = 'ImageResult: ' + req.body.imageResult;

        return this.respondCreated(res, { message: [clinicalEvolution, labReportResult, imageResult] });
      }
      return this.respondCreated(res, { message: 'Not exist files' });
    } catch (error: any) {
      if (error instanceof AppValidationError) {
        return this.respondValidationError(res, error);
      }
      return this.respondError(res, error);
    }
  }
}
