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
const AppError_1 = __importDefault(require("../exception/AppError"));
class BaseResponse {
    respondSuccess(res, payload) {
        return __awaiter(this, void 0, void 0, function* () {
            return res.status(200).json(payload);
        });
    }
    respondCreated(res, payload) {
        return __awaiter(this, void 0, void 0, function* () {
            return res.status(201).json(payload);
        });
    }
    respondError(res, err) {
        var _a;
        return __awaiter(this, void 0, void 0, function* () {
            console.log(err);
            const status = (_a = err.statusCode) !== null && _a !== void 0 ? _a : 500;
            if (err instanceof AppError_1.default) {
                return res.status(status).json({ success: false, message: err.message });
            }
            return res.status(status).json({ success: false, errors: err.errors });
        });
    }
    respondValidationError(res, err) {
        return __awaiter(this, void 0, void 0, function* () {
            return res.status(400).json({
                success: false,
                message: 'Validation error',
                errors: err.errors,
            });
        });
    }
}
exports.default = BaseResponse;
//# sourceMappingURL=BaseResponse.js.map