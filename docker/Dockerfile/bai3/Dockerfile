# Giai doan 1: BUILD (su dung Node de bien dich React)
FROM node:18-alpine AS build-stage
WORKDIR /app

# Copy file quan ly thu vien truoc de tan dung cache
COPY package*.json ./
RUN npm install

# Copy toan bo source code va build
COPY . .
RUN npm run build

# Giai doan 2: PRODUCTION (su dung Nginx de phuc vu file tinh)
FROM nginx:stable-alpine AS production-stage

# Vite build ra /app/dist. Neu dung CRA, doi "dist" thanh "build"
COPY --from=build-stage /app/dist /usr/share/nginx/html

# Mo cong mac dinh cua Nginx
EXPOSE 80

# Chay Nginx o foreground
CMD ["nginx", "-g", "daemon off;"]
