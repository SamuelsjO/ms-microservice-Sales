import { Router } from 'express';

import OrderController from '../Controllers/impl/OrderController';
import authMiddeware from '../midleware/authMidleware';
import { sendMessageToProductStockUpdateQueue } from '../Models/products/rabbitmq/productStockUpdateSender' 

const routes = Router();


routes.get('/api/orders', authMiddeware, OrderController.index);


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