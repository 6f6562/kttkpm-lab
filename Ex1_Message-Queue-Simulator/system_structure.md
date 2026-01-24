src/
├── domain/                      # WHAT: Nghiệp vụ & Bản thiết kế
│   ├── entities/                # Message.entity.ts (Định nghĩa Message object)
│   ├── events/                  # MessageCreated.event.ts
│   └── interfaces/              # IMessageBroker.ts (Hợp đồng cho RabbitMQ)
│
├── application/                 # HOW: Kịch bản điều phối
│   ├── use-cases/               # SendMessage.use-case.ts
│   └── listeners/               # MessageReceived.listener.ts (Consumer)
│
├── infrastructure/              # DETAIL: Công cụ kỹ thuật
│   ├── broker/                  # RabbitMq.broker.ts (Cài đặt thực tế)
│   └── shared/                  # Cấu hình kết nối RabbitMQ
│
├── presentation/                # ENTRY: Giao diện & Tiếp nhận
│   ├── controllers/             # Messenger.controller.ts
│   └── views/                   # index.ejs (Giao diện nhập & xem tin)
│
├── app.module.ts                # Gắn kết các lớp bằng Dependency Injection
└── main.ts                      # Khởi chạy NestJS & EJS