import AppError from "../../exception/AppError";
import AppValidationError from "../../exception/AppValidationError";
import IOrderRepository from "../../repository/IOrderRepository";
import RequestOrderDTO from "../../request/RequestOrderDTO";
import { ICreateOrderService } from "../ICreateOrderService";
import { sendMessageToProductStockUpdateQueue } from "../../models/products/rabbitmq/productStockUpdateSender.js"
import FindOrderRepository from "../../repository/impl/FindOrderRepository";
import OrderRepository from "../../repository/impl/OrderRepository";

export default class CreateOrderService implements ICreateOrderService {


   private iOrderRepository: IOrderRepository;
   constructor(aOrderRepository: IOrderRepository) {
     this.iOrderRepository = aOrderRepository;
   }
   public async execute(order: RequestOrderDTO): Promise<any> {
     try {
      
      this.iOrderRepository.execute(order);
      sendMessageToProductStockUpdateQueue(order);
     } catch (error: any) {
        console.log(error);
        if(error instanceof AppError){
          throw new AppError(error.message);
        }
        throw new AppValidationError("não é possivel salvar");
     }
   }

   public static async updatedOrder(orderMessage: any){
    try {
      const order = JSON.parse(orderMessage);

      let existOrder = await FindOrderRepository.findId(order.salesId);
      if(order.status && order.status !== existOrder.status){
        existOrder.status = order.status;
        await OrderRepository.save(existOrder);
      }
      
    } catch (error: any) {
      console.error("Could not parse order message from queue. ");
      console.log(error.message);
    }
   }

}