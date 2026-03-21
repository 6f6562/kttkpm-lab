# Bai 2 - Docker hoa ung dung Python Flask

Ung dung Flask nay chay tren cong `5000` va tra ve chuoi:

`Hello, Docker Flask!`

## 1) Build image

Chay lenh tu thu muc goc project (`docker`):

```powershell
docker build -t flask-hello-app ./Dockerfile/bai2
```

## 2) Chay container

```powershell
docker run -d --name flask-hello-container -p 5000:5000 flask-hello-app
```

## 3) Kiem tra ket qua

- Mo trinh duyet: `http://localhost:5000`
- Hoac dung lenh:

```powershell
powershell -Command "(Invoke-WebRequest -UseBasicParsing http://localhost:5000).Content"
```

Ket qua mong doi: `Hello, Docker Flask!`

## 4) Xem container dang chay va log (tuy chon)

```powershell
docker ps
docker logs flask-hello-container
```

## 5) Dung va xoa container

```powershell
docker stop flask-hello-container
docker rm flask-hello-container
```

## 6) Xoa image

```powershell
docker rmi flask-hello-app
```

## 7) Xoa nhanh toan bo (tuy chon)

Neu muon dung va xoa nhanh ca container + image:

```powershell
docker rm -f flask-hello-container
docker rmi flask-hello-app
```
