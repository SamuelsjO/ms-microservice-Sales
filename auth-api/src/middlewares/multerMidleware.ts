import { NextFunction, Request, Response } from 'express';
import mime from 'mime';
import multer, { Options } from 'multer';
import base64topdf from 'pdf-to-base64';

export class MulterMiddleware {
  async handle(req: Request, res: Response, next: NextFunction): Promise<void | Response> {
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

      const upload = multer(multerConfig).single('img');
      upload(req, res, async err => {
        if (err) {
          return res.status(500).send({ code: 500, message: err });
        }

        if (err) {
          let errCode = err.code;
          let errMessage = err.message || err;

          if (err.message?.includes('messages.file.multerInvalidDirectory')) {
            errCode = 400;
            errMessage = 'messages.file.invalidDirectory';
          }

          if (err.message == 'messages.file.multerMaxLength') {
            errCode = 400;
            errMessage = 'messages.file.maxLength';
          }

          return res.status(400).send({ code: 400, message: 'Erro valid directory' });
        }
        return next();
      });
    } catch (error: any) {
      return res.status(500).send({ code: 500, message: 'Erro valid directory' });
    }
  }
}
