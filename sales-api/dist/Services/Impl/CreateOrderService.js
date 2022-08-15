"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const AppError_1 = __importDefault(require("../../exception/AppError"));
const AppValidationError_1 = __importDefault(require("../../exception/AppValidationError"));
const productStockUpdateSender_js_1 = require("../../models/products/rabbitmq/productStockUpdateSender.js");
class CreateOrderService {
    constructor(aOrderRepository) {
        this.iOrderRepository = aOrderRepository;
    }
    execute(order) {
        return __awaiter(this, void 0, void 0, function* () {
            try {
                this.iOrderRepository.execute(order);
                (0, productStockUpdateSender_js_1.sendMessageToProductStockUpdateQueue)(order);
            }
            catch (error) {
                console.log(error);
                if (error instanceof AppError_1.default) {
                    throw new AppError_1.default(error.message);
                }
                throw new AppValidationError_1.default("não é possivel salvar");
            }
        });
    }
}
exports.default = CreateOrderService;
//# sourceMappingURL=CreateOrderService.js.map