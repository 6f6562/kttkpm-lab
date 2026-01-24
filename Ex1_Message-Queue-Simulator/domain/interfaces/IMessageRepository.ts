import { Message } from "../entities/Message";

export interface IMessageRepository {
    save(message: Message): Promise<void>;
    findAll(): Promise<Message[]>;
}
