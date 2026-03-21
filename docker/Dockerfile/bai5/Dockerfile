# Giai doan 1: BUILD
FROM golang:1.21-alpine AS builder
WORKDIR /app

# Copy file module va download dependencies
COPY go.mod ./
RUN go mod download

# Copy ma nguon va build binary
COPY . .
RUN CGO_ENABLED=0 GOOS=linux go build -o server .

# Giai doan 2: RUNNER
FROM scratch

WORKDIR /root/
COPY --from=builder /app/server .

EXPOSE 8080

CMD ["./server"]
