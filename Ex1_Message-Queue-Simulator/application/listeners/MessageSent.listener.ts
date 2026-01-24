import { IMessageRepository } from "../../domain/interfaces/IMessageRepository";
import { MessageSentEvent } from "../../domain/events/MessageSent";

export class MessageSentListener {
    constructor(private readonly repository: IMessageRepository) {}

    async handle(event: MessageSentEvent): Promise<void> {
        // Thực tế: Có thể update trạng thái tin nhắn trong DB dựa vào ID
        console.log(`[MessageSentListener] Message ${event.messageId} has been sent at ${event.sentAt}`);
        
        // Demo: Lưu tin nhắn giả định hoặc xử lý logic khác
        // await this.repository.save(...); 
    }
}
