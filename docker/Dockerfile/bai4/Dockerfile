# 1. Sử dụng Alpine Linux để giảm 80% dung lượng image
FROM nginx:alpine

# 2. Thiết lập thư mục làm việc (tốt cho việc quản lý)
WORKDIR /usr/share/nginx/html

# 3. Xóa các file mặc định của Nginx để đảm bảo môi trường sạch
RUN rm -rf ./*

# 4. Copy file index.html (Sử dụng dấu chấm vì đã WORKDIR ở trên)
COPY index.html .

# 5. Tùy chọn: Copy file cấu hình tối ưu (Gzip, Cache)
# COPY nginx.conf /etc/nginx/conf.d/default.conf

# 6. Giới hạn quyền (Chạy với user non-root nếu cần bảo mật cao)
# Mặc định alpine-nginx đã khá tối ưu.

EXPOSE 80