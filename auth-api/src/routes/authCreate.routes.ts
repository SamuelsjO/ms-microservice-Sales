import { Request, Response, Router } from 'express';
import BaseController from '../controller/BaseController';
import UsersRepository from '../repositories/implementations/UsersRepository';
import UsersService from '../services/implementations/UsersService';
import UsersController from '../controller/implementations/UsersController';
import usersValidations from '../middlewares/authValidations';

const usersRouter = Router();
const repo = new UsersRepository();
const service = new UsersService(repo);
const usersController: BaseController = new UsersController(service);

usersRouter.post('/', usersValidations, async (req: Request, res: Response) => usersController.execute(req, res));

export default usersRouter;
