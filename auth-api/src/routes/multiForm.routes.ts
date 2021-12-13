import { Request, Response, Router } from 'express';
import multer from 'multer';
import BaseController from '../controller/BaseController';
import MultiformDataController from '../controller/implementations/MultiformDataController';
import { uploadAvatar } from '../middlewares/multerMiddleware';

const multiFormRouter = Router();

const multiformDataController: BaseController = new MultiformDataController();

multiFormRouter.post('/img', multer(uploadAvatar.getConfig).single('img'), async (req: Request, res: Response) =>
  multiformDataController.execute(req, res),
);

multer(uploadAvatar.getConfig).single('img');
export default multiFormRouter;
