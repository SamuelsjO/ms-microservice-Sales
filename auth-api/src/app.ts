import cors from 'cors';
import { config } from 'dotenv';
import 'express-async-errors';
import express, { NextFunction, Request, RequestHandler, Response } from 'express';
import 'reflect-metadata';
import AppError from './errors/AppError';
import AppValidationError from './errors/AppValidationError';
import CustomError from './errors/CustomError';

import routes from './routes';

config();

const app = express();

app.use(cors());
app.use(express.json() as RequestHandler);
app.use(express.urlencoded({ extended: true }));

app.use('/api/v1', routes);
app.use((err: CustomError, request: Request, response: Response, _: NextFunction) => {
  if (err instanceof AppValidationError) {
    return response.status(err.statusCode).json({
      status: 'validation error',
      errors: err.errors,
    });
  }

  if (err instanceof AppError) {
    return response.status(err.statusCode).json({
      status: 'error',
      message: err.message,
    });
  }

  console.error(err);

  const statusCode = err.statusCode ?? 500;
  const message = err.message ?? 'Interval server error';

  return response.status(statusCode).json({
    err,
    status: 'error',
    message,
  });
});
export default app;
