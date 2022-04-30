import swaggerJsDoc from "swagger-jsdoc";

const swaggerOptions = {
    swaggerDefinition: {
        info: {
            version: "1.0.0",
            title: "Sales API",
            description: "Sales API information",
            contact: {
                name: "Samuel Oliveira"
            },
            server: ["http://localhost:8083"]
        }
    },
    apis: ["app.ts"]
};
export const swaggerDocs = swaggerJsDoc(swaggerOptions);
