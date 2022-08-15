"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.createInitialData = void 0;
function createInitialData() {
    return __awaiter(this, void 0, void 0, function* () {
        // // await Order.collection.drop();
        // let fisrtOrder = Order.create({
        //     products: [
        //         {
        //             productId: 1001,
        //             quantity:2,
        //         },
        //         {
        //             productId: 1002,
        //             quantity:1,
        //         },
        //         {
        //             productId: 1003,
        //             quantity:1,
        //         },
        //     ],
        //     user: {
        //         id: "12b3nns65**i",
        //         name: "dbIniti",
        //         email: "dbInit@gmail.com"
        //     },
        //     status: "APPROVED",
        //     createdAt: new Date(),
        //     updatedAt: new Date()
        // });
        // let secondOrder = Order.create({
        //     products: [
        //         {
        //             productId: 1001,
        //             quantity:4,
        //         },
        //         {
        //             productId: 1003,
        //             quantity:2,
        //         }
        //     ],
        //     user: {
        //         id: "12dij1873j*(kk",
        //         name: "dbIniti",
        //         email: "dbInit@gmail.com"
        //     },
        //     status: "REJECTED",
        //     createdAt: new Date(),
        //     updatedAt: new Date()
        // });
        // console.log(await fisrtOrder)
    });
}
exports.createInitialData = createInitialData;
//# sourceMappingURL=initialData.js.map