import AppError from "../../exception/AppError";
import AppValidationError from "../../exception/AppValidationError";
import IFindOrderRepository from "../../repository/IFindOrderRepository";
import { IFindOrderService } from "../IFindOrderServices";


export default class FindOrderService implements IFindOrderService {

    private iFindOrderRepository: IFindOrderRepository;
    constructor(aFindOrderRepository: IFindOrderRepository){
        this.iFindOrderRepository = aFindOrderRepository;
    }

    public async execute(): Promise<any> {
        try {

            const orderFound = await this.iFindOrderRepository.execute();

            if(!orderFound){
                throw new AppValidationError("No found orders");
            }
            return orderFound;
        } catch (error: any) {
        console.log(error);
         if (error instanceof AppError) {
            throw new AppError(error.message);
          }
           throw new AppValidationError("Sorry, no found orders");
        }
    }
    
}