# Bai01 — Docker multi-stage + Postgres (volume)

## Chạy

Từ thư mục `Bai01`:

```bash
docker compose up --build
```

## Trình duyệt hoặc curl

- [http://localhost:3001/](http://localhost:3001/) — danh sách user từ Postgres  
- [http://localhost:3001/health](http://localhost:3001/health) — kiểm tra app + kết nối DB  

## Ghi chú cổng

Máy host dùng cổng **3001** → container vẫn lắng nghe **3000** bên trong. Đổi trong `docker-compose.yml` nếu 3001 bị chiếm.

## Dữ liệu Postgres

- Volume tên `postgres_data`: lần đầu chạy, script `init/01-schema.sql` tạo bảng + seed.
- `docker compose down` — tắt container, **dữ liệu vẫn còn** trên volume.
- `docker compose down -v` — tắt và **xóa volume** (mất data).

## Chỉ build image app

```bash
docker build -t bai01-app ./app
```
