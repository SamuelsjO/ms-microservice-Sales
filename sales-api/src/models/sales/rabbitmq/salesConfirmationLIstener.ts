import amqp from "amqplib/callback_api";
import { SALES_CONFIRMATION_QUEUE } from "../../../rabbitmq/queue";

import { RABBIT_MQ_URL } from "../../../secret/secrets";

export function listenToSalesConfirmationQueue(){
    amqp.connect(RABBIT_MQ_URL, (error, connection) => {
            if(error){
                throw error;
            }
            console.info("Listening to Sales Confirmation Queue...")
            connection.createChannel((error, channel) => {
                if(error) {
                    throw error;
                }
                channel.consume(
                    SALES_CONFIRMATION_QUEUE,
                    (message) => {
                        console.info(
                            `Reciving message from queue: ${message?.content.toString()}`
                        );
                    },
                    {
                        noAck: true,
                    }
                )
            })
        });
}