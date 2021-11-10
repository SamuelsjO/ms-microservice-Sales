import express from "express";

const app = express();


app.get('/api/status', (req, res) => {
    return res.status(200).json({
        service: 'Auth-api',
        status: 'up',
        httpStatus: 200,
    })
})

export default app;