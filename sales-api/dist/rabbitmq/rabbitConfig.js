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
exports.connectionRabbitMq = void 0;
const callback_api_js_1 = __importDefault(require("amqplib/callback_api.js"));
const queue_1 = require("./queue");
const secrets_1 = require("../secret/secrets");
const salesConfirmationLIstener_1 = require("../models/sales/rabbitmq/salesConfirmationLIstener");
const TWO_SECOND = 2000;
const HALF_MINUTES = 30000;
const CONTAINER_ENV = "container";
function connectionRabbitMq() {
    return __awaiter(this, void 0, void 0, function* () {
        const env = process.env.NODE_ENV;
        if (CONTAINER_ENV === env) {
            console.info("Waiting for RabbitMq to start...");
            setInterval(() => __awaiter(this, void 0, void 0, function* () {
                yield connectRabbitMqAndCreateQueues();
            }), HALF_MINUTES);
        }
        else {
            yield connectRabbitMqAndCreateQueues();
        }
    });
}
exports.connectionRabbitMq = connectionRabbitMq;
function connectRabbitMqAndCreateQueues() {
    return __awaiter(this, void 0, void 0, function* () {
        callback_api_js_1.default.connect(secrets_1.RABBIT_MQ_URL, (error, connection) => {
            if (error) {
                throw error;
            }
            console.info("Starting RabbitMQ...");
            createQueue(connection, queue_1.PRODUCT_STOCK_QUEUE, queue_1.PRODUCT_STOCK_ROUTING_KEY, queue_1.PRODUCT_TOPIC);
            createQueue(connection, queue_1.SALES_CONFIRMATION_QUEUE, queue_1.SALES_CONFIRMATION_ROUTING_KEY, queue_1.PRODUCT_TOPIC);
            console.info("Queues and Topics were defined...");
            setTimeout(function () {
                connection.close();
            }, TWO_SECOND);
        });
        setTimeout(function () {
            (0, salesConfirmationLIstener_1.listenToSalesConfirmationQueue)();
        }, TWO_SECOND);
    });
}
function createQueue(connection, queue, routingKey, topic) {
    connection.createChannel((error, channel) => {
        if (error) {
            throw error;
        }
        channel.assertExchange(topic, "topic", { durable: true });
        channel.assertQueue(queue, { durable: true });
        channel.bindQueue(queue, topic, routingKey);
    });
}
//# sourceMappingURL=rabbitConfig.js.map