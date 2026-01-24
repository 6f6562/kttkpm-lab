import { Message } from "../entities/Message";

export interface IMessageBroker {
    publish(message: Message): Promise<void>;
    subscribe(callback: (message: Message) => void): Promise<void>;
}
