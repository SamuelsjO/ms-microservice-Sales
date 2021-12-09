import { Request, Response, Router } from 'express';
import BaseController from '../controller/BaseController';
import MultiformDataController from '../controller/implementations/MultiformDataController';

const multiFormRouter = Router();

const multiformDataController: BaseController = new MultiformDataController();

multiFormRouter.post('/', async (req: Request, res: Response) => multiformDataController.execute(req, res));

export default multiFormRouter;
