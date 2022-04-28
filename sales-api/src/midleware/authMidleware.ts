import { Request, NextFunction, Response } from 'express';
import jwt from 'jsonwebtoken';
import { API_SECRET } from '../secret/secrets';

interface TokenPayload {
  id: string;
  iat: number;
  exp: number;
}
const authMiddeware = async (req: Request, resp: Response, next: NextFunction) => {
  const { authorization } = req.headers;

  if (!authorization) {
    return resp.sendStatus(401);
  }

  const token = authorization.replace('Bearer', '').trim();
  

  try {
    const data = jwt.verify(token, API_SECRET);

    const { id } = data as TokenPayload;

   
    req.userId = id;
    return next();
  } catch {
    return resp.sendStatus(401);
  }
};

export default authMiddeware;
