"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const app_1 = __importDefault(require("./app"));
const env = process.env;
const PORT = env.PORT || 8080;
app_1.default.listen(PORT, () => {
    console.info(`Server started successfully at port ${PORT}`);
});
//# sourceMappingURL=server.js.map