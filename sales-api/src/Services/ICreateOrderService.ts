import RequestOrderDTO from "../request/RequestOrderDTO";


export interface ICreateOrderService {
    execute(order: RequestOrderDTO): Promise<any>;
}