# Bai 4 - Trien khai website tinh voi Nginx

Muc tieu: Nhung ma nguon tinh (HTML/CSS/JS) vao Nginx trong Docker de phuc vu noi dung qua HTTP.

Trong bai nay, trang web tinh nam trong file `index.html` va duoc copy vao:
`/usr/share/nginx/html/index.html` ben trong container.

## 1) Cau truc file

Trong thu muc `Dockerfile/bai4`:

- `index.html`: Noi dung trang web
- `Dockerfile`: Cau hinh image Nginx

## 2) Build image

Chay lenh tu thu muc goc project (`docker`):

```powershell
docker build -t my-static-web ./Dockerfile/bai4
```

## 3) Chay container

```powershell
docker run -d -p 8080:80 --name web-nginx my-static-web
```

Y nghia:

- `-p 8080:80`: Map cong 8080 may ban vao cong 80 cua container Nginx
- `--name web-nginx`: Dat ten container

## 4) Kiem tra ket qua

- Mo trinh duyet: `http://localhost:8080`
- Hoac dung PowerShell:

```powershell
powershell -Command "(Invoke-WebRequest -UseBasicParsing http://localhost:8080).StatusCode"
```

Ket qua mong doi: ma trang thai `200` va hien thi noi dung trong `index.html`.

## 5) Dung va xoa container

```powershell
docker stop web-nginx
docker rm web-nginx
```

## 6) Xoa image (tuy chon)

```powershell
docker rmi my-static-web
```
