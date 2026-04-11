# Bai02 — Demo phân vùng CSDL (Sequelize + MariaDB)

**Yêu cầu:** Node 20+, MariaDB hoặc Docker.

## 1) Chạy nhanh bằng Docker (khuyến nghị)

Từ thư mục `Bai02`:

```bash
docker compose up -d db
```

Đợi DB healthy, seed một lần:

```bash
docker compose run --rm app npm run seed
```

Chạy API:

```bash
docker compose up app
```

- Trình duyệt: [http://localhost:3002/](http://localhost:3002/) (host **3002** → container **3000**)
- MariaDB trên máy host: `localhost:3307` (user `root` / pass `root`, database `partition_demo`)

## 2) Chạy local (MariaDB / MySQL đã cài)

Tạo database `partition_demo`, copy `.env.example` → `.env` và chỉnh nếu cần. Ứng dụng **tự đọc `.env`** (gói `dotenv`).

```bash
npm install
npm run seed
npm start
```

Mặc định: [http://localhost:3000/](http://localhost:3000/)

### Lỗi `auth_gssapi_client` / `ER_AUTHENTICATION_PLUGIN_NOT_SUPPORTED` (thường gặp trên Windows)

MariaDB cài trên Windows đôi khi cấu hình `root` đăng nhập bằng **GSSAPI** (Windows). Driver Node **không hỗ trợ** plugin đó.

**Cách xử lý (chọn một):**

1. **Dùng Docker** (không phụ thuộc MariaDB trên máy) — xem mục **Chạy nhanh bằng Docker** ở trên.

2. **Tạo user chỉ dùng mật khẩu** (chạy trong HeidiSQL / DBeaver / `mysql` client, quyền admin):

   ```sql
   CREATE DATABASE IF NOT EXISTS partition_demo;
   CREATE USER IF NOT EXISTS 'bai02'@'localhost' IDENTIFIED BY 'bai02_local';
   GRANT ALL PRIVILEGES ON partition_demo.* TO 'bai02'@'localhost';
   FLUSH PRIVILEGES;
   ```

   Trong `.env`:

   ```env
   DB_USER=bai02
   DB_PASSWORD=bai02_local
   DB_DIALECT=mysql
   ```

3. **Đổi plugin cho `root`** (nếu bạn buộc dùng root — cú pháp tùy phiên bản MariaDB):

   ```sql
   ALTER USER 'root'@'localhost' IDENTIFIED VIA mysql_native_password USING PASSWORD('mật_khẩu_của_bạn');
   FLUSH PRIVILEGES;
   ```

4. Thử `DB_HOST=127.0.0.1` thay vì `localhost` nếu server có user `@'127.0.0.1'` với mật khẩu thường.

File tham khảo: [`scripts/windows-mariadb-user.sql`](scripts/windows-mariadb-user.sql).

## 3) Seed

| Lệnh | Ý nghĩa |
|------|---------|
| `npm run seed` | Xóa bảng (`sync` force) rồi nạp dữ liệu mẫu |
| `npm run seed:soft` | Không xóa bảng (có thể trùng bản ghi nếu chạy lại) |

## 4) API thử

- `GET /users/gender/M` hoặc `/users/gender/F`
- `GET /users/age/25` hoặc `/users/age/35`
- `GET /users/vertical`

## 5) Đổi tên thư mục

Thư mục dự án là **Bai02** (trước đây: `Bai02_nodejs-partition-demo`).
