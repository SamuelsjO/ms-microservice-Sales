"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
class AppValidationError {
    constructor(errors, statusCode = 400) {
        this.errors = errors;
        this.statusCode = statusCode;
    }
}
exports.default = AppValidationError;
//# sourceMappingURL=AppValidationError.js.map