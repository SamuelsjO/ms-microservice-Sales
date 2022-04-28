const env = process.env;

export const MONGO_DB_URL = env.MONGO_DB_URL 
? env.MONGO_DB_URL : "mongodb://admin:123456@localhost:27017/sales"

export const API_SECRET = env.API_SECRET 
? env.API_SECRET : "2bb80d537b1da3e38bd30361aa855686bde0eacd7162fef6a25fe97bf527a25b"