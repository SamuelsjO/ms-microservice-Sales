import path from 'path';
import multer from 'multer';
import mime from 'mime';
import { Request } from 'express';

class UploadAvatar {
  private URL: string = path.basename('upload');

  constructor() {}

  private storage(): multer.StorageEngine {
    return multer.diskStorage({
      filename: (req, file, cb) => {
        const type = mime.extension(file.mimetype);

        cb(null, `${new Date().getTime()}.${type}`);
      },
    });
  }

  private fileFilter() {
    return (req: Request, file: Express.Multer.File, cb: multer.FileFilterCallback) => {
      const type = mime.extension(file.mimetype);

      const conditions = ['pdf', 'PDF'];

      if (conditions.includes(`${type}`)) {
        cb(null, true);
      }
      cb(null, false);
    };
  }

  get getConfig(): multer.Options {
    return {
      storage: this.storage(),
      fileFilter: this.fileFilter(),
    };
  }
}

export const uploadAvatar = new UploadAvatar();
