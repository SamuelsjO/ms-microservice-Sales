import amqp from "amqplib/callback_api.js";

import { 
    PRODUCT_STOCK_QUEUE, 
    PROUCT_STOCK_ROUTING_KEY, 
    PRODUCT_TOPIC, 
    SALES_CONFIRMATION_QUEUE, 
    SALES_CONFIRMATION_ROUTING_KEY } 
    from "./queue";

import { RABBIT_MQ_URL } from "../secret/secrets";

const HALF_SECOND = 500;

export async function connectionRabbitMq(){
    amqp.connect(RABBIT_MQ_URL, (error, connection) => {
        if(error){
            throw error;
        }

        createQueue(
            connection,
            PRODUCT_STOCK_QUEUE,
            PROUCT_STOCK_ROUTING_KEY,
            PRODUCT_TOPIC
         );

         createQueue(
            connection,
            SALES_CONFIRMATION_QUEUE,
            SALES_CONFIRMATION_ROUTING_KEY,
            PRODUCT_TOPIC
         );

        setTimeout(function () {
            connection.close();
        }, HALF_SECOND);
    });

    function createQueue(connection: any, queue: any, routingKey: any, topic: any) {
        connection.createChannel((error: any, channel: any) => {
            if(error) {
                throw error;
            }
            channel.assertExchange(topic, "topic", { durable: true });
            channel.assertQueue(queue, { durable: true});
            channel.bindQueue(queue, topic, routingKey)
        });
    }
}