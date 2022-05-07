import amqp from "amqplib/callback_api";
import { PRODUCT_TOPIC, PRODUCT_STOCK_ROUTING_KEY } from "../../../rabbitmq/queue";

import { RABBIT_MQ_URL } from "../../../secret/secrets";

export function sendMessageToProductStockUpdateQueue(message: Object){
    amqp.connect(RABBIT_MQ_URL, (error, connection) => {
        if(error){
            throw error;
        }
        connection.createChannel((error, channel) => {
            if(error) {
                throw error;
            }
            let jsonStringMessage = JSON.stringify(message);
            console.info(`Sending message to product update stock: ${jsonStringMessage}`);
            channel.publish(
                PRODUCT_TOPIC,
                PRODUCT_STOCK_ROUTING_KEY,
                Buffer.from(jsonStringMessage)
            );
            console.info("Message was sent successfully!!")
        })
    });
}