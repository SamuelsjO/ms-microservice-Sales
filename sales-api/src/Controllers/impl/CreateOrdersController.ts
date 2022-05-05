import { Request, Response } from "express";
import RequestOrderDTO from "../../request/RequestOrderDTO";
import { ICreateOrderService } from "../../Services/ICreateOrderService";
import BaseController from "../BaseController";


export default class CreateOrdersController extends BaseController {

  private iCreateOrderService: ICreateOrderService;

  constructor(aCreateOrderService: ICreateOrderService) {
    super();
    this.iCreateOrderService = aCreateOrderService;
  }
    protected executeImpl(req: Request, res: Response<any, Record<string, any>>): Promise<Response<any, Record<string, any>>> {
        try {
            const order: RequestOrderDTO = req.body;
            this.iCreateOrderService.execute(order);
            return this.respondCreated(res, order);
        } catch (error: any) {
            return this.respondValidationError(res, error);
        }
    }
    
}