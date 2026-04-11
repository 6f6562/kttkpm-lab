"use strict";

const http = require("http");
const { Client } = require("pg");

const port = Number(process.env.PORT) || 3000;
const databaseUrl = process.env.DATABASE_URL;

function json(res, status, body) {
  const data = JSON.stringify(body, null, 2);
  res.writeHead(status, {
    "Content-Type": "application/json; charset=utf-8",
    "Content-Length": Buffer.byteLength(data),
  });
  res.end(data);
}

async function withDb(fn) {
  if (!databaseUrl) {
    return { ok: false, error: "DATABASE_URL chưa được cấu hình" };
  }
  const client = new Client({ connectionString: databaseUrl });
  try {
    await client.connect();
    return await fn(client);
  } catch (e) {
    return { ok: false, error: e.message };
  } finally {
    await client.end().catch(() => {});
  }
}

const server = http.createServer(async (req, res) => {
  const url = req.url.split("?")[0];

  if (url === "/health") {
    const db = await withDb(async (c) => {
      await c.query("SELECT 1 AS ok");
      return { ok: true };
    });
    const status = db.ok ? 200 : 503;
    json(res, status, { status: db.ok ? "up" : "degraded", database: db });
    return;
  }

  if (url === "/" || url === "/users") {
    const db = await withDb(async (c) => {
      const r = await c.query(
        "SELECT id, name FROM demo_users ORDER BY id ASC"
      );
      return { ok: true, rows: r.rows };
    });
    const status = db.ok ? 200 : 503;
    json(res, status, {
      message: "Bai01 — Docker multi-stage + Postgres (volume)",
      hint: "Dữ liệu Postgres lưu trong volume: docker compose down không xóa volume thì data còn.",
      database: db.ok ? { users: db.rows } : db,
    });
    return;
  }

  res.writeHead(404);
  res.end("Not found");
});

server.listen(port, "0.0.0.0", () => {
  console.log(`HTTP listening on :${port}`);
});
