import AppError from "../../exception/AppError";
import AppValidationError from "../../exception/AppValidationError";
import IOrderRepository from "../../repository/IOrderRepository";
import RequestOrderDTO from "../../request/RequestOrderDTO";
import { ICreateOrderService } from "../ICreateOrderService";

export default class CreateOrderService implements ICreateOrderService {

   private iOrderRepository: IOrderRepository;
   constructor(aOrderRepository: IOrderRepository) {
     this.iOrderRepository = aOrderRepository;
   }
   public async execute(order: RequestOrderDTO): Promise<any> {
     try {
      
      this.iOrderRepository.execute(order);

     } catch (error: any) {
        console.log(error);
        if(error instanceof AppError){
          throw new AppError(error.message);
        }
        throw new AppValidationError("não é possivel salvar");
     }
   }

}