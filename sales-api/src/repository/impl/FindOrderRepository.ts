import Order from "../../models/sales/Order";
import IFindOrderRepository from "../IFindOrderRepository";


export default class FindOrderRepository implements IFindOrderRepository {
    async execute(): Promise<any> {
        return await Order.find();
        
    }

}