import {IGenericResponseDto} from "./i-generic-response-dto";

export interface ILoginResponseDto extends IGenericResponseDto {
  bearerToken: string;
}
