"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const mongoose_1 = require("mongoose");
const OrderSchema = new mongoose_1.Schema({
    products: {
        type: Array,
        required: true,
    },
    user: {
        type: Object,
        required: true,
    },
    status: {
        type: String,
        required: true,
    },
    createdAt: {
        type: Date,
        required: true,
    },
    updatedAt: {
        type: Date,
        required: true,
    }
});
exports.default = (0, mongoose_1.model)("Order", OrderSchema);
//# sourceMappingURL=Order.js.map