import Order from "../../models/sales/Order";
import RequestOrderDTO from "../../request/RequestOrderDTO";
import IOrderRepository from "../IOrderRepository";

export default class OrderRepository implements IOrderRepository {
    async execute(order: RequestOrderDTO): Promise<any> {
            return Order.create(order);
    }
}