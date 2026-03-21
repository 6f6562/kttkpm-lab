# Bai 1 - Docker hoa ung dung Node.js

Ung dung nay chay tren cong `3000` va tra ve chuoi:

`Hello, Docker!`

## 1) Build image

Chay lenh tu thu muc goc project (`docker`):

```powershell
docker build -t hello-docker-bai1 ./Dockerfile/bai1
```

## 2) Chay container

```powershell
docker run -d --name hello-docker-bai1 -p 3000:3000 hello-docker-bai1
```

## 3) Kiem tra ket qua

- Mo trinh duyet: `http://localhost:3000`
- Hoac dung lenh:

```powershell
powershell -Command "(Invoke-WebRequest -UseBasicParsing http://localhost:3000).Content"
```

Ket qua mong doi: `Hello, Docker!`

## 4) Dung va xoa container (don dep)

```powershell
docker stop hello-docker-bai1
docker rm hello-docker-bai1
```

## 5) Xoa image (tuy chon)

```powershell
docker rmi hello-docker-bai1
```
