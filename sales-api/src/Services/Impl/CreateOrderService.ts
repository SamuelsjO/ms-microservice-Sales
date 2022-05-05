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
        console.log("Nao é possivel salvar");
     }
   }

}