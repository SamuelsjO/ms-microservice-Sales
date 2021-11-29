import { Router } from 'express';
import usersRouter from './authCreate.routes';
import findRoute from './authFind.routes';
import authRoute from './authToken.routes';

const routes = Router();

routes.use('/', usersRouter);
routes.use('/user', findRoute);
routes.use('/auth', authRoute);

export default routes;
