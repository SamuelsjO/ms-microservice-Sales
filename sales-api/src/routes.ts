import { Router } from 'express';

import OrderController from './controllers/order/OrderController';

const routes = Router();

routes.get('/api/orders', OrderController.index);


routes.get('/api/status', async (req, res) => {
                return res.status(200).json({
                    service:"Sales-API",
                    status: "UP",
                    httpStatus: 200
            
                })
           })

export default routes;