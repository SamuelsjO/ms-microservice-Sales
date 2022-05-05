import { Request, Response } from 'express';

import Order from '../../Models/sales/Order';
import BaseController from '../BaseController';


export default class FindOrderController extends BaseController {
    protected async executeImpl(req: Request, res: Response<any, Record<string, any>>): Promise<Response<any, Record<string, any>>> {
        try {
            const orders = await Order.find();
            console.log("return: ", orders)
            return this.respondSuccess(res, orders);
        } catch (error: any) {
            return this.respondValidationError(res, error);
        }
    }

}