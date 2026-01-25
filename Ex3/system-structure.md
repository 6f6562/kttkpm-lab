Ex3/
│
├── src/
│   ├── domain/                 # Tầng lõi (Core), không chứa thư viện ngoài
│   │   ├── entities/           # Định nghĩa các đối tượng nghiệp vụ (Order.ts)
│   │   ├── repositories/       # Các bản thiết kế (Interface) cho Database
│   │   └── services/           # Các bản thiết kế cho hệ thống ngoài (IMailer.ts)
│   │
│   ├── application/            # Điều phối logic nghiệp vụ
│   │   └── use-cases/          # Nơi chứa CreateOrderSync.ts và CreateOrderAsync.ts
│   │
│   ├── infrastructure/         # Triển khai chi tiết công nghệ (Detail)
│   │   ├── database/           # MariaDB (Sequelize)
│   │   ├── messaging/          # RabbitMQ
│   │   └── services/           # Thực thi MailService (có delay 3s)
│   │
│   ├── presentation/           # Giao diện và API
│   │   ├── controllers/        # Điều hướng request từ Route tới Use Case
│   │   └── routes/             # Định nghĩa các đường dẫn (Endpoint)
│   │
│   └── main.ts                 # Điểm khởi tạo (Composition Root), nơi tiêm (Inject) Dependencies
│
├── views/                      # Thư mục chứa template EJS
├── docker-compose.yml          # Cấu hình MariaDB và RabbitMQ
└── package.json