"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const BaseController_1 = __importDefault(require("../BaseController"));
class CreateOrdersController extends BaseController_1.default {
    constructor(aCreateOrderService) {
        super();
        this.iCreateOrderService = aCreateOrderService;
    }
    executeImpl(req, res) {
        try {
            const order = req.body;
            this.iCreateOrderService.execute(order);
            return this.respondCreated(res, order);
        }
        catch (error) {
            return this.respondValidationError(res, error);
        }
    }
}
exports.default = CreateOrdersController;
//# sourceMappingURL=CreateOrdersController.js.map