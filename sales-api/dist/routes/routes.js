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
const express_1 = require("express");
const authMidleware_1 = __importDefault(require("../midleware/authMidleware"));
const productStockUpdateSender_1 = require("../models/products/rabbitmq/productStockUpdateSender");
const _1 = require(".");
const routes = (0, express_1.Router)();
/**Rotas GET */
routes.get('/orders', (req, res) => __awaiter(void 0, void 0, void 0, function* () { return _1.findOrderController.execute(req, res); }));
routes.get('/api/status', authMidleware_1.default, (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    return res.status(200).json({
        service: "Sales-API",
        status: "UP",
        httpStatus: 200
    });
}));
/**Rotas POST */
routes.post('/api/order', (req, res) => __awaiter(void 0, void 0, void 0, function* () { return _1.createOrdersController.execute(req, res); }));
/**
 * Rota pra teste enviar mensagem na fila e atualizar - será removido
 *
 */
routes.get('/test', (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    console.log("testing..");
    try {
        (0, productStockUpdateSender_1.sendMessageToProductStockUpdateQueue)([
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
        ]);
        return res.status(200).json({ status: 200 });
    }
    catch (error) {
        console.log(error);
        return res.status(500).json({ error: true });
    }
}));
exports.default = routes;
//# sourceMappingURL=routes.js.map