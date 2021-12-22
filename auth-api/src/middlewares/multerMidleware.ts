import { NextFunction, Request, Response } from 'express';
import mime from 'mime';
import multer, { Options } from 'multer';
import BaseResponse from '../BaseResponse/BaseResponse';
import AppValidationError from '../errors/AppValidationError';
import { multerFields } from '../helper/multerFields';

export class MulterMiddleware extends BaseResponse {
  async execute(req: Request, res: Response, next: NextFunction): Promise<void | Response> {
    try {
      const multerConfig: Options = {
        limits: {
          fileSize: 5 * 1024 * 1024, // 5 MB,
        },
        fileFilter: (req, file, cb) => {
          const type = mime.extension(file.mimetype);
          const allowFormats = ['pdf', 'PDF'];

          if (allowFormats.includes(`${type}`)) {
            cb(null, true);
          } else {
            cb(new Error('error de type'));
          }
        },
      };

      const multerArch = multer(multerConfig).fields([
        {
          name: multerFields.clinicalEvolution,
          maxCount: 1,
        },
        {
          name: multerFields.labReportResult,
          maxCount: 1,
        },
        {
          name: multerFields.reportImageResult,
          maxCount: 1,
        },
      ]);
      multerArch(req, res, err => {
        if (req.files) {
          const fileKeys: string[] = Object.keys(req.files);
          const filesReq: any = req.files;

          fileKeys.forEach((key: string) => {
            const file = filesReq[key][0];
            const buffer = Buffer.from(file.buffer).toString('base64');

            if (file.fieldname === multerFields.clinicalEvolution) req.body.clinicalEvolution = buffer;
            if (file.fieldname === multerFields.labReportResult) req.body.labReportResult = buffer;
            if (file.fieldname === multerFields.reportImageResult) req.body.reportImageResult = buffer;
          });
        }

        if (err) {
          let errCode = err.code;
          let errMessage = err.message || err;

          console.log('err :>> ', err);

          if (err.message?.includes('messages.file.multerInvalidDirectory')) {
            errCode = 400;
            errMessage = 'messages.file.invalidDirectory';
          }

          if (err.Error == 'error de type') {
            errCode = 400;
            errMessage = 'messages.file.maxLength';
          }

          return res.status(400).send({ code: 400, message: 'Erro valid type' });
        }
        return next();
      });
    } catch (error: any) {
      throw new AppValidationError(error.errors);
    }
  }
}
