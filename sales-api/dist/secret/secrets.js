"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.RABBIT_MQ_URL = exports.API_SECRET = exports.MONGO_DB_URL = void 0;
const env = process.env;
exports.MONGO_DB_URL = env.MONGO_DB_URL
    ? env.MONGO_DB_URL : "mongodb://admin:123456@localhost:27017/sales";
exports.API_SECRET = env.API_SECRET
    ? env.API_SECRET : "2bb80d537b1da3e38bd30361aa855686bde0eacd7162fef6a25fe97bf527a25b";
exports.RABBIT_MQ_URL = env.RABBIT_MQ_URL
    ? env.RABBIT_MQ_URL : "amqp://localhost:5672";
//# sourceMappingURL=secrets.js.map