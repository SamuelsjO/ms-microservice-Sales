"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const secrets_1 = require("./secret/secrets");
const initialData_1 = require("./dbInit/initialData");
const express_1 = __importDefault(require("express"));
const mongoose_1 = __importDefault(require("mongoose"));
const cors_1 = __importDefault(require("cors"));
const routes_1 = __importDefault(require("./routes/routes"));
const rabbitConfig_1 = require("./rabbitmq/rabbitConfig");
class App {
    constructor() {
        this.express = (0, express_1.default)();
        this.middleware();
        this.database();
        this.routes();
        this.createData();
        this.rabbitMq();
        this.versionApi();
    }
    middleware() {
        this.express.use(express_1.default.json());
        this.express.use((0, cors_1.default)());
    }
    database() {
        try {
            mongoose_1.default.connect(secrets_1.MONGO_DB_URL, {
                useNewUrlParser: true,
                useUnifiedTopology: true
            });
        }
        catch (error) {
            mongoose_1.default.Error;
        }
    }
    rabbitMq() {
        (0, rabbitConfig_1.connectionRabbitMq)();
    }
    routes() {
        this.express.use(routes_1.default);
    }
    createData() {
        (0, initialData_1.createInitialData)();
    }
    versionApi() {
        this.express.use('/api/v1', routes_1.default);
    }
}
exports.default = new App().express;
//# sourceMappingURL=app.js.map