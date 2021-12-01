import { Request, Response, Router } from 'express';
import BaseController from '../controller/BaseController';
import FindUsersController from '../controller/implementations/FindAuthController';
import FindUsersService from '../services/implementations/FindUsersService';
import FindUsersRepositoy from '../repositories/implementations/FindUsersRepositoy';
import authMiddeware from '../middlewares/authMiddleware';

const findRoute = Router();
const repo = new FindUsersRepositoy();
const service = new FindUsersService(repo);
const findUsersController: BaseController = new FindUsersController(service);

findRoute.get('/', authMiddeware, async (req: Request, res: Response) => findUsersController.execute(req, res));

export default findRoute;
