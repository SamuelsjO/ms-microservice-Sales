import { Request, Response, Router } from 'express';
import multer from 'multer';
import BaseController from '../controller/BaseController';
import MultiformDataController from '../controller/implementations/MultiformDataController';
import { MulterMiddleware } from '../middlewares/multerMidleware';

const multerMiddleware = new MulterMiddleware();

const multiFormRouter = Router();

const multiformDataController: BaseController = new MultiformDataController();


multiFormRouter.post(
  '/img',
  (req, res, next) => multerMiddleware.execute(req, res, next),
  (req: Request, res: Response) => multiformDataController.execute(req, res),
);

export default multiFormRouter;
