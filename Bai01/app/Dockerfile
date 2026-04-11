# Bai01: 4-stage build — nhỏ gọn runtime, tách deps / source / build / chạy

# -----------------------------
# Stage 1: cài dependency production
# -----------------------------
FROM node:20-alpine AS dependencies
WORKDIR /app
COPY package*.json ./
RUN npm ci --omit=dev && npm cache clean --force

# -----------------------------
# Stage 2: mã nguồn ứng dụng
# -----------------------------
FROM node:20-alpine AS source
WORKDIR /app
COPY package*.json ./
COPY index.js ./

# -----------------------------
# Stage 3: bước build (giả lập / mở rộng sau: webpack, tsc...)
# -----------------------------
FROM source AS build
RUN echo "build stage OK" && test -f index.js

# -----------------------------
# Stage 4: image chạy thực tế — chỉ copy phần cần thiết
# -----------------------------
FROM node:20-alpine AS runtime
WORKDIR /app
ENV NODE_ENV=production
RUN addgroup -g 1001 -S nodejs && adduser -S nodejs -u 1001 -G nodejs

COPY --from=dependencies --chown=nodejs:nodejs /app/node_modules ./node_modules
COPY --from=build --chown=nodejs:nodejs /app/index.js ./
COPY --from=build --chown=nodejs:nodejs /app/package.json ./

USER nodejs
EXPOSE 3000
HEALTHCHECK --interval=30s --timeout=5s --start-period=15s --retries=3 \
  CMD node -e "require('http').get('http://127.0.0.1:3000/health',r=>process.exit(r.statusCode===200?0:1)).on('error',()=>process.exit(1))"

CMD ["node", "index.js"]
