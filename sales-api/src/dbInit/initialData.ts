import Order from "../Models/Order";

export async function createInitialData() {
    // await Order.collection.drop();
    let fisrtOrder = Order.create({
        products: [
            {
                productId: 1001,
                quantity:2,
            },
            {
                productId: 1002,
                quantity:1,
            },
            {
                productId: 1003,
                quantity:1,
            },
        ],
        user: {
            id: "ldfjgsldfjsdlk",
            name: "User test",
            email: "usertTest@gmail.com"
        },
        status: "APPROVED",
        createdAt: new Date(),
        updatedAt: new Date()
    });

    let secondOrder = Order.create({
        products: [
            {
                productId: 1001,
                quantity:4,
            },
            {
                productId: 1003,
                quantity:2,
            }
        ],
        user: {
            id: "ldfjgsldfjssdfasdfdlk",
            name: "User test2",
            email: "usertTest2@gmail.com"
        },
        status: "REJECTED",
        createdAt: new Date(),
        updatedAt: new Date()
    });

    console.log(await fisrtOrder)
}