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
const AppValidationError_1 = __importDefault(require("../../exception/AppValidationError"));
const BaseController_1 = __importDefault(require("../BaseController"));
class FindOrderController extends BaseController_1.default {
    constructor(aFindOrderService) {
        super();
        this.iFindOrderService = aFindOrderService;
    }
    executeImpl(req, res) {
        return __awaiter(this, void 0, void 0, function* () {
            try {
                const orders = yield this.iFindOrderService.execute();
                if (!isEmptyObject(orders)) {
                    throw new AppValidationError_1.default("No found orders");
                }
                return this.respondSuccess(res, orders);
            }
            catch (error) {
                return this.respondValidationError(res, error);
            }
        });
    }
}
exports.default = FindOrderController;
function isEmptyObject(orders) {
    return !!Object.keys(orders).length;
}
//# sourceMappingURL=FindOrderController.js.map