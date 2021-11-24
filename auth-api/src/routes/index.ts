import { Router } from 'express';
import usersRouter from './authCreate.routes';
import findRoute from './authFind.routes';

const routes = Router();

routes.use('/', usersRouter);
routes.use('/user', findRoute);

export default routes;
