export class Message {
    constructor(
        private _name: string,
        private _message: string,
        private _createdTime: Date
    ) {}

    public get name(): string {
        return this._name;
    }

    public set name(value: string) {
        this._name = value;
    }

    public get message(): string {
        return this._message;
    }

    public set message(value: string) {
        this._message = value;
    }

    public get createdTime(): Date {
        return this._createdTime;
    }

    public set createdTime(value: Date) {
        this._createdTime = value;
    }
}
