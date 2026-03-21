# Stage 1: Kiểm tra lỗi cú pháp (Builder)
FROM node:20-alpine AS builder
WORKDIR /app
COPY app.js .
# Kiểm tra xem code có lỗi không trước khi đóng gói
RUN node --check app.js

# Stage 2: Chạy ứng dụng (Runner)
FROM node:20-alpine AS runner

# Thiết lập biến môi trường
ENV NODE_ENV=production \
    PORT=3000

WORKDIR /app

# Copy file từ stage builder sang stage runner với quyền user node
COPY --from=builder --chown=node:node /app/app.js ./app.js

# Chuyển sang user node để tránh chạy quyền root (Bảo mật)
USER node

EXPOSE 3000

# Chạy ứng dụng
CMD ["node", "app.js"]