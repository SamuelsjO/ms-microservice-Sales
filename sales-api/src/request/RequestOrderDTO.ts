export default interface RequestOrderDTO {
        products: [
                {
                    productId: number,
                    quantity: number,
                }
            ],

            user: {
                id: String,
                name: String,
                email: String
            },

            status: String,
            createdAt: Date,
            updatedAt: Date
        
}
    
    