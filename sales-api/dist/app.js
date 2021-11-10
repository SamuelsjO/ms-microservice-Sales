"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
var express_1 = __importDefault(require("express"));
var app = (0, express_1.default)();
app.get('/api/status', function (req, res) {
    return res.status(200).json({
        service: "Sales-API",
        status: "UP",
        httpStatus: 200
    });
});
exports.default = app;
