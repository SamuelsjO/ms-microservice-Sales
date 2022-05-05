import RequestOrderDTO from "../request/RequestOrderDTO";

export default interface IOrderRepository {
  execute(order: RequestOrderDTO): Promise<any>;
  
}