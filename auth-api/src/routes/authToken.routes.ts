import { Request, Response, Router } from 'express';
import BaseController from '../controller/BaseController';
import GetAcessTokenController from '../controller/implementations/GetAcessTokenController';
import FindUsersRepositoy from '../repositories/implementations/FindUsersRepositoy';
import FindUsersService from '../services/implementations/FindUsersService';

const authRoute = Router();
const repo = new FindUsersRepositoy();
const service = new FindUsersService(repo);
const getAcessTokenController: BaseController = new GetAcessTokenController(service);

authRoute.post('/', async (req: Request, res: Response) => getAcessTokenController.execute(req, res));

export default authRoute;
