const IMailer = require('../../domain/services/IMailer');

class SmtpMailer extends IMailer {
    async sendMail(to, content) {
        console.log(`[SmtpMailer] Sending email to ${to}...`);

        // Simulate delay of 3 seconds
        await new Promise(resolve => setTimeout(resolve, 3000));

        console.log(`[SmtpMailer] Email sent to ${to} with content: ${content}`);
    }
}

module.exports = SmtpMailer;
