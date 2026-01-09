export * from './product.service';
import { ProductService } from './product.service';
export * from './product.serviceInterface';
export * from './user.service';
import { UserService } from './user.service';
export * from './user.serviceInterface';
export const APIS = [ProductService, UserService];
