import { Message } from "../entities/Message";

export class MessageReceivedEvent {
    constructor(public message: Message) {}
}
