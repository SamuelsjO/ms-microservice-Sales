import { Router } from 'express';
import usersRouter from './authCreate.routes';
import findRoute from './authFind.routes';
import authRoute from './authToken.routes';
import multiFormRouter from './multiForm.routes';

const routes = Router();

routes.use('/', usersRouter);
routes.use('/user', findRoute);
routes.use('/authToken', authRoute);
routes.use('/auth', usersRouter);
routes.use('/multi', multiFormRouter);

export default routes;
