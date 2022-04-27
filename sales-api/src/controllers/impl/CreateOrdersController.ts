import { Request, Response } from "express";
import RequestOrderDTO from "../../RequestDto/RequestOrderDTO";
import CreateOrderService from "../../services/impl/CreateOrderService";
import BaseController from "../BaseController";


export default class CreateOrdersController extends BaseController {

  private createOrderService: CreateOrderService;

  constructor(aCreateOrderService: CreateOrderService) {
    super();
    this.createOrderService = aCreateOrderService;
  }
    protected executeImpl(req: Request, res: Response<any, Record<string, any>>): Promise<Response<any, Record<string, any>>> {
        try {
            const order = req.body;
            this.createOrderService.createOrderData(order);
            return this.respondCreated(res, order);
        } catch (error: any) {
            return this.respondValidationError(res, error);
        }
    }
    
}