import { Message } from "../entities/Message";

export class MessageCreatedEvent {
    constructor(public message: Message) {}
}
