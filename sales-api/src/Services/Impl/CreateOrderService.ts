import Order from "../../Models/sales/Order";


export default class CreateOrderService  {
     public async createOrderData(order: any) {
        await Order.collection.drop();
        return Order.create(order);

     }
}