import { Item } from "./item.model";

export interface Order {
    personCode: string;
    orderItems: Item[];
}