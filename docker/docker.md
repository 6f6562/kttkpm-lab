1. Kiểm tra và Quản lý Hình ảnh (Images)
docker --version: Kiểm tra phiên bản Docker đang cài đặt.

docker pull nginx: Tải image (ở đây là nginx) từ Docker Hub về máy.

docker images: Liệt kê tất cả các images hiện có trên máy.

docker rmi <image_id>: Xóa một image cụ thể.

docker image prune -a: Dọn dẹp, xóa tất cả các images không sử dụng.

2. Quản lý Container (Chạy, Dừng, Xóa)
docker run hello-world: Chạy một container từ image "hello-world" (thường dùng để test).

docker run -d nginx: Chạy container nginx ở chế độ chạy ngầm (detached mode).

docker ps: Liệt kê các container đang hoạt động.

docker ps -a: Liệt kê tất cả container (cả đang chạy và đã dừng).

docker stop <container_id>: Dừng một container đang chạy.

docker restart <container_id>: Khởi động lại container.

docker rm <container_id>: Xóa một container (phải dừng trước khi xóa).

docker container prune: Xóa tất cả các container đã dừng.

3. Tương tác với Container
docker logs <container_id>: Xem nhật ký (logs) của container.

docker logs -f my_nginx: Xem logs trực tiếp theo thời gian thực (follow).

docker exec -it <container_id> /bin/sh: Truy cập vào bên trong shell của container đang chạy.

docker inspect <container_id>: Xem thông tin chi tiết (cấu hình, IP,...) của container.

docker stats: Xem thông số sử dụng tài nguyên (CPU, RAM) của các container.

4. Cấu hình Nâng cao (Port, Volume, Network, Env)
docker run -d -p 8080:80 nginx: Chạy container và ánh xạ cổng (Port Forwarding): Cổng 8080 của máy thật vào cổng 80 của container.

docker run -d --name my_nginx nginx: Chạy container và đặt tên cho nó là "my_nginx".

docker run -d -e MY_ENV=hello_world nginx: Thiết lập biến môi trường (Environment variable) cho container.

Volume (Dữ liệu):

docker run -d -v mydata:/data nginx: Gắn một Volume để lưu trữ dữ liệu bền vững.

docker volume ls: Liệt kê các volumes.

docker volume prune: Xóa các volumes không dùng đến.

Network (Mạng):

docker network ls: Liệt kê các mạng Docker.

docker network create my_network: Tạo một mạng ảo mới.

docker run -d --network my_network --name my_container nginx: Chạy container trong một mạng cụ thể.

docker network connect my_network my_nginx: Kết nối một container đang chạy vào một mạng.

5. Build Image từ Dockerfile
FROM nginx & COPY index.html ...: Các chỉ thị trong file Dockerfile để tạo image tùy chỉnh.

docker build -t my_nginx_image .: Xây dựng (build) một image mới từ Dockerfile trong thư mục hiện tại với tên "my_nginx_image".

docker run -d -p 8080:80 my_nginx_image: Chạy container từ chính image bạn vừa build.