import { Request, Response, Router } from 'express';
import BaseController from '../Controllers/BaseController';
import CreateOrdersController from '../Controllers/impl/CreateOrdersController';
import FindOrderController from '../Controllers/impl/FindOrderController';

import authMiddeware from '../midleware/authMidleware';
import { sendMessageToProductStockUpdateQueue } from '../Models/products/rabbitmq/productStockUpdateSender' 
import OrderRepository from '../repository/impl/OrderRepository';
import CreateOrderService from '../Services/Impl/CreateOrderService';

const routes = Router();

const orderRep = new OrderRepository();
const service = new CreateOrderService(orderRep);
const createOrdersController: BaseController = new CreateOrdersController(service);

const findOrderController: BaseController = new FindOrderController();

routes.get('/orders', async (req: Request, res: Response) => findOrderController.execute(req, res));


routes.post('/order', async (req: Request, res: Response) => createOrdersController.execute(req, res));
















/**
 * Rota pra teste enviar mensagem na fila e atualizar - será removido
 * 
 */
routes.get('/test', async (req, res) => {
    console.log("testing..")
    try {
        sendMessageToProductStockUpdateQueue([
            {
                productId: 1001,
                quantity: 3
            },
            {
                productId: 1002,
                quantity: 2
            },
            {
                productId: 1003,
                quantity: 1
            },
        ])
        return res.status(200).json({status:200})
    } catch (error) {
        console.log(error)
        return res.status(500).json({error: true})
    }
});


routes.get('/api/status', authMiddeware, async (req, res) => {
                return res.status(200).json({
                    service:"Sales-API",
                    status: "UP",
                    httpStatus: 200
            
                })
           })

export default routes;