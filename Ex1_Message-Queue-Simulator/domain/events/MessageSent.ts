export class MessageSentEvent {
    constructor(
        public messageId: string,
        public sentAt: Date
    ) {}
}
