import { Request, Response } from 'express';
import AppValidationError from '../../exception/AppValidationError';
import { IFindOrderService } from '../../Services/IFindOrderServices';
import BaseController from '../BaseController';


export default class FindOrderController extends BaseController {

    private iFindOrderService: IFindOrderService;
    
    constructor(aFindOrderService: IFindOrderService){
        super();
        this.iFindOrderService = aFindOrderService;
    }

    protected async executeImpl(req: Request, res: Response<any, Record<string, any>>): Promise<Response<any, Record<string, any>>> {
        try {
            const orders = await this.iFindOrderService.execute();
            if(!isEmptyObject(orders)){
                throw new AppValidationError("No found orders");
            }
            return this.respondSuccess(res, orders);
        } catch (error: any) {
            return this.respondValidationError(res, error);
        }
    }

}

function isEmptyObject(orders: any) {
    return !!Object.keys(orders).length;
}
