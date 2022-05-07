import amqp from "amqplib/callback_api.js";

import { 
    PRODUCT_STOCK_QUEUE, 
    PRODUCT_STOCK_ROUTING_KEY, 
    PRODUCT_TOPIC, 
    SALES_CONFIRMATION_QUEUE, 
    SALES_CONFIRMATION_ROUTING_KEY } 
    from "./queue";

import { RABBIT_MQ_URL } from "../secret/secrets";
import { listenToSalesConfirmationQueue } from "../models/sales/rabbitmq/salesConfirmationLIstener";

const TWO_SECOND = 2000;
const HALF_MINUTES = 30000;
const CONTAINER_ENV = "container";

export async function connectionRabbitMq(){
    const env = process.env.NODE_ENV;
    if(CONTAINER_ENV === env){
        console.info("Waiting for RabbitMq to start...");
        setInterval(async () => {
            await connectRabbitMqAndCreateQueues();
        }, HALF_MINUTES);
    } else {
        await connectRabbitMqAndCreateQueues();
    }
    
}

    async function connectRabbitMqAndCreateQueues(){
        amqp.connect(RABBIT_MQ_URL, (error, connection) => {
            if(error){
                throw error;
            }
            console.info("Starting RabbitMQ...")
            createQueue(
                connection,
                PRODUCT_STOCK_QUEUE,
                PRODUCT_STOCK_ROUTING_KEY,
                PRODUCT_TOPIC
             );
    
             createQueue(
                connection,
                SALES_CONFIRMATION_QUEUE,
                SALES_CONFIRMATION_ROUTING_KEY,
                PRODUCT_TOPIC
             );
             console.info("Queues and Topics were defined...")
            setTimeout(function () {
                connection.close();
            }, TWO_SECOND);
        });
        setTimeout(function () {
            listenToSalesConfirmationQueue();
        }, TWO_SECOND);
        
    }
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
