# Bai05 — Spring Boot + React + SQL Server

Tìm kiếm user theo tên: **debounce** trên React, backend gọi **stored procedure** `sp_search_users` trên SQL Server. Flyway tạo schema và dữ liệu mẫu.

## Yêu cầu môi trường

- JDK 17+
- Node.js (npm)
- Docker (tùy chọn, để chạy SQL Server trong container)

## 1. SQL Server

### Cách A — Docker (khuyến nghị)

Compose map SQL Server ra cổng host **`14333`** (tránh trùng SQL Server cài sẵn trên Windows thường dùng `1433`).

Từ thư mục `Bai05` (có thể `copy .env.example .env` rồi chỉnh mật khẩu nếu cần):

```bash
docker compose up -d
```

Đợi container **healthy** (khoảng 30–60 giây), rồi tạo database **một lần** (PowerShell / CMD, một dòng):

```powershell
docker exec -it bai05-sqlserver /opt/mssql-tools18/bin/sqlcmd -S localhost -U sa -P "YourStrong@Passw0rd" -C -Q "IF DB_ID(N'demo_search') IS NULL CREATE DATABASE demo_search;"
```

Bash (có thể xuống dòng với `\`):

```bash
docker exec -it bai05-sqlserver /opt/mssql-tools18/bin/sqlcmd \
  -S localhost -U sa -P "YourStrong@Passw0rd" -C \
  -Q "IF DB_ID(N'demo_search') IS NULL CREATE DATABASE demo_search;"
```

Sau đó chạy Spring Boot trên máy host — **bắt buộc** trỏ cổng **14333** và đúng mật khẩu SA (PowerShell, từ thư mục `Bai05`):

```powershell
$env:MSSQL_PORT = "14333"
$env:MSSQL_SA_PASSWORD = "YourStrong@Passw0rd"
cd DemoApplication
.\mvnw.cmd spring-boot:run
```

(Nếu đổi `MSSQL_SA_PASSWORD` trong `.env` của Compose, dùng **cùng** giá trị cho `$env:MSSQL_SA_PASSWORD`.)

Nếu đổi mật khẩu SA: đặt `MSSQL_SA_PASSWORD` trong file `.env` cùng cấp `docker-compose.yml` và export cùng giá trị khi chạy Spring (hoặc chỉnh `application.properties`).

### Cách B — SQL Server cài trên máy

Tạo database tên `demo_search`. Mặc định app kết nối `localhost:1433` — giữ `MSSQL_PORT` không set (hoặc `MSSQL_PORT=1433`). Chỉnh user / password qua `application.properties` hoặc biến môi trường `MSSQL_HOST`, `MSSQL_DATABASE`, `MSSQL_USER`, `MSSQL_SA_PASSWORD`.

Bật **SQL Server Authentication (Mixed Mode)** và đặt mật khẩu `sa` khớp với cấu hình app.

## 2. Backend (cổng 8080)

```bash
cd DemoApplication
```

Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

Linux / macOS:

```bash
./mvnw spring-boot:run
```

Nếu dùng **Docker** (SQL trên cổng host `14333`), đặt `MSSQL_PORT=14333` và `MSSQL_SA_PASSWORD` trước khi chạy — xem mục **1 · Cách A**.

Lần đầu chạy, **Flyway** áp migration: bảng `users`, procedure `sp_search_users`, dữ liệu mẫu.

**API tìm kiếm:**

```http
GET http://localhost:8080/api/search?keyword=...
```

## 3. Frontend (cổng mặc định 3000)

```bash
cd frontend
copy .env.example .env
npm install
npm start
```

Trên Linux / macOS, thay `copy` bằng `cp`. File `.env` là tùy chọn (đổi `REACT_APP_API_BASE` nếu API không chạy tại `http://localhost:8080`).

Ô tìm kiếm gọi API sau **debounce 500 ms**.

## Ghi chú

Mật khẩu SA trong ví dụ phải đủ mạnh theo yêu cầu SQL Server; chỉ nên dùng cho môi trường học tập / demo.

## Gặp lỗi `Login failed for user 'sa'` (18456) khi `spring-boot:run`

- **Đang kết nối nhầm instance:** Trên Windows, `localhost:1433` thường là SQL Server cài sẵn (mật khẩu `sa` khác với ví dụ). Dùng Docker của bài: đặt `$env:MSSQL_PORT = "14333"` như trên, hoặc dừng dịch vụ SQL Windows nếu bạn cố ý dùng container trên cổng 1433.
- **Sai mật khẩu:** Mật khẩu trong `application.properties` / `$env:MSSQL_SA_PASSWORD` phải **trùng** với `MSSQL_SA_PASSWORD` lúc tạo container. Nếu đổi mật khẩu sau khi đã chạy container lần đầu, volume Docker có thể giữ mật khẩu cũ — khi đó dùng đúng mật khẩu cũ, hoặc `docker compose down -v` rồi tạo lại (xóa dữ liệu trong volume).
- **Chưa tạo database `demo_search`:** Tạo DB bằng lệnh `sqlcmd` trong phần Docker ở trên rồi chạy lại app.
