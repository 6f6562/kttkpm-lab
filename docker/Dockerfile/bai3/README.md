# Bai 3 - Docker hoa ung dung React (Vite)

Day la ung dung React Vite Hello World don gian, da san sang de build va chay bang Docker.

## 1) Chay local (tuy chon)

Trong thu muc `Dockerfile/bai3`:

```powershell
npm install
npm run dev
```

Mo trinh duyet tai `http://localhost:5173`.

## 2) Build image

Chay lenh tu thu muc goc project (`docker`):

```powershell
docker build -t react-app-prod ./Dockerfile/bai3
```

## 3) Chay container

```powershell
docker run -d --name react-app-prod-container -p 8080:80 react-app-prod
```

## 4) Kiem tra ket qua

Mo trinh duyet: `http://localhost:8080`

- Hoac dung lenh:

```powershell
powershell -Command "(Invoke-WebRequest -UseBasicParsing http://localhost:8080).StatusCode"
```

Ket qua mong doi: ma trang thai `200` va giao dien hien thi dong:
`Hello World from React + Vite + Docker!`

## 5) Luu y `.dockerignore`

File `.dockerignore` da loai bo:

- `node_modules`
- `build`, `dist`
- `.git`

Giup build nhanh hon va giam kich thuoc context.

## 6) Dung va xoa container

```powershell
docker stop react-app-prod-container
docker rm react-app-prod-container
```

## 7) Xoa image (tuy chon)

```powershell
docker rmi react-app-prod
```

