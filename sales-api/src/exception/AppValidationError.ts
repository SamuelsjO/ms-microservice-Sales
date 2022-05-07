class AppValidationError {
    public readonly errors: string;
  
    public readonly statusCode: number;
  
    constructor(errors: string, statusCode = 400) {
      this.errors = errors;
      this.statusCode = statusCode;
    }
  }
  
  export default AppValidationError;