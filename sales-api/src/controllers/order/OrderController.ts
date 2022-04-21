import { Request, Response } from 'express';

import Order from '../../schemas/Order';

class OrderController {
    public async index (req: Request, res: Response): Promise<Response> {
        try {
        console.log("Entrou no antes")
        const orders = await Order.find();
        console.log("Entrou no try")
        return res.json(orders);
        } catch (error) {
            console.log("Entrou no catch")
            return res.json(error)
        }

    }
}

export default new OrderController();