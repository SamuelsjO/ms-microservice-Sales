"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.listenToSalesConfirmationQueue = void 0;
const callback_api_1 = __importDefault(require("amqplib/callback_api"));
const queue_1 = require("../../../rabbitmq/queue");
const secrets_1 = require("../../../secret/secrets");
function listenToSalesConfirmationQueue() {
    callback_api_1.default.connect(secrets_1.RABBIT_MQ_URL, (error, connection) => {
        if (error) {
            throw error;
        }
        console.info("Listening to Sales Confirmation Queue...");
        connection.createChannel((error, channel) => {
            if (error) {
                throw error;
            }
            channel.consume(queue_1.SALES_CONFIRMATION_QUEUE, (message) => {
                console.info(`Reciving message from queue: ${message === null || message === void 0 ? void 0 : message.content.toString()}`);
            }, {
                noAck: true,
            });
        });
    });
}
exports.listenToSalesConfirmationQueue = listenToSalesConfirmationQueue;
//# sourceMappingURL=salesConfirmationLIstener.js.map