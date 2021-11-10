import app from './app';


const env = process.env;
const PORT = env.PORT || 8080;

app.listen(PORT, () => {
    console.info(`Server started successfully at port ${PORT}`)
})
