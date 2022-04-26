import { Request, Response } from 'express';

import Order from '../../schemas/Order';

class OrderController {
    public async index (req: Request, res: Response): Promise<Response> {
        try {
        const orders = await Order.find();
        console.log("orderskkk ", orders)
        return res.json(orders);
        } catch (error) {
            return res.json(error)
        }

    }
}

export default new OrderController();