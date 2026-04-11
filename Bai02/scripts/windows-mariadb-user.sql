-- Chạy một lần với quyền admin (MariaDB/MySQL trên Windows) nếu gặp lỗi
-- auth_gssapi_client / ER_AUTHENTICATION_PLUGIN_NOT_SUPPORTED từ Node.

CREATE DATABASE IF NOT EXISTS partition_demo;

CREATE USER IF NOT EXISTS 'bai02'@'localhost' IDENTIFIED BY 'bai02_local';
GRANT ALL PRIVILEGES ON partition_demo.* TO 'bai02'@'localhost';

-- Một số bản cài còn có user cho kết nối TCP:
CREATE USER IF NOT EXISTS 'bai02'@'127.0.0.1' IDENTIFIED BY 'bai02_local';
GRANT ALL PRIVILEGES ON partition_demo.* TO 'bai02'@'127.0.0.1';

FLUSH PRIVILEGES;

-- Trong Bai02/.env đặt:
--   DB_USER=bai02
--   DB_PASSWORD=bai02_local
--   DB_DIALECT=mysql
-- (và DB_HOST=localhost hoặc 127.0.0.1 tùy cách bạn kết nối)
