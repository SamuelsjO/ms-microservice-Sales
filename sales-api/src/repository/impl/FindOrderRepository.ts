import Order from "../../models/sales/Order";
import IFindOrderRepository from "../IFindOrderRepository";


export default class FindOrderRepository implements IFindOrderRepository {

    async execute(): Promise<any> {
        try {
            return await Order.find();
        } catch (error) {
            console.log(error)
        }
        
    }

    public static async findId(id: string): Promise<any> {
        try {
            return await Order.findById(id);
        } catch (error) {
            console.log(error)
        }
        
    }


}