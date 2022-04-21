import { MONGO_DB_URL } from './secret/secrets';
import express from "express";
import mongoose from "mongoose";
import cors from 'cors';
import Order from './schemas/Order'
import routes from './routes'

class App {
    public express: express.Application

    public constructor(){
        this.express = express();
        this.middleware();
        this.database();
        this.routes();
    }

    private middleware(): void {
        this.express.use(express.json())
        this.express.use(cors())
    }
    private database(): void {
        try {
            mongoose.connect(MONGO_DB_URL);
            console.info("Connected dataBase mongo!")
        } catch (error) {
            console.error("Not Connected dataBase!!!, ", error)
        }
    }

    private routes(): void {

        this.express.use(routes); 
    }

}

export default new App().express
