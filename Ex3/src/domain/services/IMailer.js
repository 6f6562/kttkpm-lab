class IMailer {
    async sendMail(to, content) {
        throw new Error("Method 'sendMail(to, content)' must be implemented.");
    }
}

module.exports = IMailer;
