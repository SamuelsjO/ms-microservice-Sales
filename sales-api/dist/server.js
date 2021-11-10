"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
var app_1 = __importDefault(require("./app"));
var env = process.env;
var PORT = env.PORT || 8080;
app_1.default.listen(PORT, function () {
    console.info("Server started successfully at port " + PORT);
});
