export class Person {
    constructor(
        public personCode: string,
        public password: string,
        public firstName: string,
        public lastName: string,
        public phoneNumber?: string,
        public email?: string
    ) { }
}