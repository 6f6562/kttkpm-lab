import { Request, Response } from 'express';
import { SendMessageUseCase } from '../../application/use-cases/SendMessageUseCase';
import { IMessageRepository } from '../../domain/interfaces/IMessageRepository';

export class MessengerController {
    constructor(
        private readonly sendMessageUseCase: SendMessageUseCase,
        private readonly messageRepo: IMessageRepository // Optional: to load history
    ) {}

    async index(req: Request, res: Response): Promise<void> {
        try {
            // Optional: Load history from Repo
            // const messages = await this.messageRepo.findAll();
            const messages: any[] = []; // Default empty for simulation
            res.render('index', { messages });
        } catch (error) {
            console.error(error);
            res.render('index', { messages: [], error: 'Failed to load messages' });
        }
    }

    async send(req: Request, res: Response): Promise<void> {
        try {
            const { name, message } = req.body;
            await this.sendMessageUseCase.execute(name, message);
            res.redirect('/');
        } catch (error) {
            console.error(error);
            // In a real app, you might re-render with error
            res.redirect('/?error=SendFailed');
        }
    }
}
