"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.findOrderController = exports.createOrdersController = void 0;
const CreateOrdersController_1 = __importDefault(require("../controllers/impl/CreateOrdersController"));
const FindOrderController_1 = __importDefault(require("../controllers/impl/FindOrderController"));
const FindOrderRepository_1 = __importDefault(require("../repository/impl/FindOrderRepository"));
const OrderRepository_1 = __importDefault(require("../repository/impl/OrderRepository"));
const CreateOrderService_1 = __importDefault(require("../Services/Impl/CreateOrderService"));
const FindOrderServices_1 = __importDefault(require("../Services/Impl/FindOrderServices"));
const orderRep = new OrderRepository_1.default();
const service = new CreateOrderService_1.default(orderRep);
exports.createOrdersController = new CreateOrdersController_1.default(service);
const findRep = new FindOrderRepository_1.default();
const findService = new FindOrderServices_1.default(findRep);
exports.findOrderController = new FindOrderController_1.default(findService);
//# sourceMappingURL=index.js.map