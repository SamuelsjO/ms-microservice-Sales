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
class FindOrderService {
    constructor(aFindOrderRepository) {
        this.iFindOrderRepository = aFindOrderRepository;
    }
    execute() {
        return __awaiter(this, void 0, void 0, function* () {
            try {
                const orderFound = yield this.iFindOrderRepository.execute();
                if (!orderFound) {
                    throw new AppValidationError_1.default("No found orders");
                }
                return orderFound;
            }
            catch (error) {
                console.log(error);
                if (error instanceof AppError_1.default) {
                    throw new AppError_1.default(error.message);
                }
                throw new AppValidationError_1.default("Sorry, no found orders");
            }
        });
    }
}
exports.default = FindOrderService;
//# sourceMappingURL=FindOrderServices.js.map