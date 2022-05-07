import BaseController from '../controllers/BaseController';
import CreateOrdersController from '../controllers/impl/CreateOrdersController';
import FindOrderController from '../controllers/impl/FindOrderController';
import FindOrderRepository from '../repository/impl/FindOrderRepository';
import OrderRepository from '../repository/impl/OrderRepository';
import CreateOrderService from '../Services/Impl/CreateOrderService';
import FindOrderService from '../Services/Impl/FindOrderServices';

const orderRep = new OrderRepository();
const service = new CreateOrderService(orderRep);
export const createOrdersController: BaseController = new CreateOrdersController(service);

const findRep = new FindOrderRepository();
const findService = new FindOrderService(findRep);
export const findOrderController: BaseController = new FindOrderController(findService);
