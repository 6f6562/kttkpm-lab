# Stage 1: Build (Cài đặt thư viện)
FROM python:3.9-slim AS builder
WORKDIR /app
COPY requirements.txt .
# Cài đặt vào một thư mục riêng để tí nữa copy cho dễ
RUN pip install --user --no-cache-dir -r requirements.txt

# Stage 2: Run (Image cuối cùng siêu nhẹ)
FROM python:3.9-slim AS runner
WORKDIR /app

# Chỉ copy những thư viện đã cài đặt từ stage builder
COPY --from=builder /root/.local /root/.local
COPY app.py .

# Cập nhật PATH để Python nhận diện thư viện trong .local
ENV PATH=/root/.local/bin:$PATH

EXPOSE 5000
CMD ["python", "app.py"]