# Bai 5 - Docker hoa ung dung Go (Static Binary + Scratch)

Bai tap nay minh hoa ky thuat multi-stage build cho Go:

- Stage `builder`: bien dich ma nguon thanh binary
- Stage `runner`: dung `scratch` (image rong) de chay binary

## 1) Cac file trong bai

- `main.go`: ung dung HTTP server Go
- `go.mod`: khai bao module `hello-go`
- `Dockerfile`: multi-stage build toi uu

## 2) Build image

Chay lenh tu thu muc goc project (`docker`):

```powershell
docker build -t hello-go-app ./Dockerfile/bai5
```

## 3) Chay container

```powershell
docker run -d --name hello-go-app-container -p 8080:8080 hello-go-app
```

## 4) Kiem tra ket qua

Mo trinh duyet: `http://localhost:8080`

Hoac dung PowerShell:

```powershell
powershell -Command "(Invoke-WebRequest -UseBasicParsing http://localhost:8080).Content"
```

Ket qua mong doi:

`Hello, Docker Go!`

## 5) Tai sao dung `scratch`?

- Image cuc nhe vi khong co he dieu hanh
- Giam be mat tan cong
- Phu hop voi Go static binary (`CGO_ENABLED=0`)

## 6) Dung va xoa container

```powershell
docker stop hello-go-app-container
docker rm hello-go-app-container
```

## 7) Xoa image (tuy chon)

```powershell
docker rmi hello-go-app
```
