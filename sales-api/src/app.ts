import { MONGO_DB_URL } from './secret/secrets';
import { createInitialData } from './dbInit/initialData';
import express from "express";
import mongoose from "mongoose";
import cors from 'cors';
import routes from './routes/routes';
import { connectionRabbitMq } from "./rabbitmq/rabbitConfig";

class App {
    public express: express.Application

    public constructor(){
        this.express = express();
        this.middleware();
        this.database();
        this.routes();
        this.createData();
        this.rabbitMq();
        this.versionApi();
    }

    private middleware(): void {
        this.express.use(express.json())
        this.express.use(cors())
    }
    private database(): void {
           try {
            mongoose.connect(MONGO_DB_URL, {
                useNewUrlParser: true,
                useUnifiedTopology: true
            });
           } catch (error) {
               mongoose.Error
           }
        
    }

    private rabbitMq(): void {
        connectionRabbitMq();
    }

    private routes(): void {
        this.express.use(routes); 
    }
    private createData(): void {
        createInitialData();
    }

    private versionApi(): void {
        this.express.use('/api/v1', routes);
    }

}



export default new App().express
