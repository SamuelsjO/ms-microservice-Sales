import { Router } from 'express';

import OrderController from '../Controllers/impl/OrderController';
import authMiddeware from '../midleware/authMidleware';

const routes = Router();

/**
 * @swagger
 * /sales:
 *  get:
 *    description: User to request all Orders
 *    responses:
 *      '200':
 *        description: A sucessful response 
*/
routes.get('/api/orders', OrderController.index);

routes.post('api/order');


routes.get('/api/status', authMiddeware, async (req, res) => {
                return res.status(200).json({
                    service:"Sales-API",
                    status: "UP",
                    httpStatus: 200
            
                })
           })

export default routes;