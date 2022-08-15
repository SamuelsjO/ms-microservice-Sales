import Order from "../../models/sales/Order";
import RequestOrderDTO from "../../request/RequestOrderDTO";
import IOrderRepository from "../IOrderRepository";

export default class OrderRepository implements IOrderRepository {
    static save(order: any) {
        try {
            return Order.create(order);
        } catch (error) {
            console.log(error)
        }
    }
    async execute(order: RequestOrderDTO): Promise<any> {
        try {
            return await Order.create(order);
        } catch (error) {
            console.log(error)
        }
    }
}