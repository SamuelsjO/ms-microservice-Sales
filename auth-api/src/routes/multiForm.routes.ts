import { Request, Response, Router } from 'express';
import BaseController from '../controller/BaseController';
import MultiformDataController from '../controller/implementations/MultiformDataController';
import { MulterMiddleware } from '../middlewares/multerMidleware';

const multerMiddleware = new MulterMiddleware();

const multiFormRouter = Router();

const multiformDataController: BaseController = new MultiformDataController();

// multiFormRouter.post('/images', multer(uploadAvatar.getConfig).single('img'), async (req: Request, res: Response) =>
//   multiformDataController.execute(req, res),
// );

// multer(uploadAvatar.getConfig).single('img');

multiFormRouter.post(
  '/img',
  (req, res, next) => multerMiddleware.handle(req, res, next),
  (req: Request, res: Response) => multiformDataController.execute(req, res),
);

export default multiFormRouter;
