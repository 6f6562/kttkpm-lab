# Bài 03 — Kiến trúc theo dịch vụ trong một monolith (một database)

Ứng dụng minh họa **tách module theo nghiệp vụ** (Order, Payment, Shipping) nhưng vẫn chạy **một process** và dùng **chung một MongoDB** — đúng tinh thần “mono → các function/service module → 1 DB” trong ghi chú tuần 05.

## Yêu cầu môi trường

- [Node.js](https://nodejs.org/) (khuyến nghị LTS)
- [MongoDB](https://www.mongodb.com/try/download/community) chạy local (mặc định `mongodb://127.0.0.1:27017`)

## Cài đặt

```bash
cd Bai03
npm install
```

## Cấu hình

- Chuỗi kết nối mặc định: `mongodb://127.0.0.1:27017/ktvtkpm`
- Đổi bằng biến môi trường (tùy chọn):

```bash
set MONGO_URI=mongodb://127.0.0.1:27017/ten_db_cua_ban
npm start
```

Trên PowerShell có thể dùng `$env:MONGO_URI="..."` trước khi chạy `npm start`.

## Chạy server

```bash
npm start
```

- API: `http://localhost:3000`
- Giao diện demo (HTML tĩnh): `http://localhost:3000/`
- Kiểm tra nhanh: `GET http://localhost:3000/api/health`

## Dữ liệu mẫu (tùy chọn)

```bash
npm run seed
```

Script sẽ **xóa toàn bộ** document trong collection `orders` rồi chèn vài đơn với trạng thái khác nhau (`CREATED`, `PAID`, `SHIPPED`) để thử luồng.

## Luồng nghiệp vụ

| Bước | Trạng thái | API gợi ý |
|------|------------|-----------|
| 1. Tạo đơn | `CREATED` | `POST /orders` — body: `{ "item": "Tên sản phẩm" }` |
| 2. Thanh toán | `PAID` | `POST /payments/:orderId` |
| 3. Giao hàng | `SHIPPED` | `POST /shipping/:orderId` |

Tra cứu:

- `GET /orders` — danh sách đơn (mới nhất trước)
- `GET /orders/:id` — một đơn theo MongoDB `_id`

Quy tắc:

- Chỉ thanh toán khi đơn đang `CREATED`.
- Chỉ giao hàng khi đơn đang `PAID`.

Trạng thái `DELIVERED` được khai báo trong schema để mở rộng sau (hiện API chưa đổi sang trạng thái này).

## Cấu trúc thư mục

```
Bai03/
├── config/db.js          # Kết nối MongoDB (URI tập trung)
├── server.js             # Express + mount các router module
├── seed.js               # Nạp dữ liệu mẫu
├── public/index.html     # Trang demo gọi API
└── modules/
    ├── order/            # Model, repo, service, controller
    ├── payment/          # Service + controller (dùng chung order.repo)
    └── shipping/         # Tương tự payment
```

**Ý chính:** Payment và Shipping **không có bảng/collection riêng**; chúng cùng cập nhật trạng thái đơn trong một collection `orders` — mô hình “nhiều bounded context trong code, một nguồn dữ liệu”.

## Gọi API bằng curl (PowerShell)

Tạo đơn:

```powershell
curl -Method POST -Uri http://localhost:3000/orders -ContentType "application/json" -Body '{"item":"Tra sua"}'
```

Sau đó copy `_id` từ JSON trả về, thay vào `:id`:

```powershell
curl -Method POST -Uri http://localhost:3000/payments/<id>
curl -Method POST -Uri http://localhost:3000/shipping/<id>
```

## Khắc phục sự cố thường gặp

- **Không kết nối được MongoDB:** Bật dịch vụ MongoDB, kiểm tra cổng `27017` và `MONGO_URI`.
- **Thanh toán / giao hàng báo lỗi:** Đảm bảo đúng thứ tự trạng thái `CREATED` → `PAID` → `SHIPPED`.
