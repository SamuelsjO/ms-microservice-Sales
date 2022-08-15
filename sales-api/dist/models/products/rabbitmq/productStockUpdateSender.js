"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.sendMessageToProductStockUpdateQueue = void 0;
const callback_api_1 = __importDefault(require("amqplib/callback_api"));
const queue_1 = require("../../../rabbitmq/queue");
const secrets_1 = require("../../../secret/secrets");
function sendMessageToProductStockUpdateQueue(message) {
    callback_api_1.default.connect(secrets_1.RABBIT_MQ_URL, (error, connection) => {
        if (error) {
            throw error;
        }
        connection.createChannel((error, channel) => {
            if (error) {
                throw error;
            }
            let jsonStringMessage = JSON.stringify(message);
            console.info(`Sending message to product update stock: ${jsonStringMessage}`);
            channel.publish(queue_1.PRODUCT_TOPIC, queue_1.PRODUCT_STOCK_ROUTING_KEY, Buffer.from(jsonStringMessage));
            console.info("Message was sent successfully!!");
        });
    });
}
exports.sendMessageToProductStockUpdateQueue = sendMessageToProductStockUpdateQueue;
//# sourceMappingURL=productStockUpdateSender.js.map