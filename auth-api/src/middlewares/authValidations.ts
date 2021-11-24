import { NextFunction, Request, Response } from 'express';
import * as Yup from 'yup';
import AppValidationError from '../errors/AppValidationError';

const usersValidations = async (req: Request, resp: Response, next: NextFunction) => {
  try {
    const data = req.body;

    const schema = Yup.object().shape({
      name: Yup.string().required(),
      email: Yup.string().required().email(),
      password: Yup.string().required(),
    });

    const res = await schema.validate(data, {
      strict: true,
      abortEarly: false,
    });
    await Promise.resolve(res);
    next();

    return;
  } catch (error: any) {
    throw new AppValidationError(error.errors);
  }
};

export default usersValidations;
