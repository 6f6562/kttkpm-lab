import { useState, useEffect } from "react";
import axios from "axios";

const API_BASE = (process.env.REACT_APP_API_BASE || "http://localhost:8080").replace(
  /\/$/,
  ""
);

export default function App() {
  const [keyword, setKeyword] = useState("");
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  // Debounce; cờ active tránh ghi state từ request cũ khi gõ nhanh
  useEffect(() => {
    const q = keyword.trim();
    if (q === "") {
      setUsers([]);
      setError(null);
      setLoading(false);
      return undefined;
    }

    let active = true;
    const timer = setTimeout(async () => {
      try {
        setLoading(true);
        setError(null);
        const res = await axios.get(`${API_BASE}/api/search`, {
          params: { keyword: q },
        });
        if (!active) return;
        setUsers(Array.isArray(res.data) ? res.data : []);
      } catch (err) {
        if (!active) return;
        console.error("Error:", err);
        setError("Không gọi được API. Kiểm tra backend và CORS.");
        setUsers([]);
      } finally {
        if (active) setLoading(false);
      }
    }, 500);

    return () => {
      active = false;
      clearTimeout(timer);
    };
  }, [keyword]);

  return (
    <div style={styles.container}>
      <h2>SQL Server — tìm theo tên (debounce + stored procedure)</h2>

      <input
        style={styles.input}
        placeholder="Nhập tên user để tìm..."
        value={keyword}
        onChange={(e) => setKeyword(e.target.value)}
        autoComplete="off"
      />

      {loading && <p>Đang tải...</p>}
      {error && <p style={styles.error}>{error}</p>}

      {!loading && keyword.trim() === "" && (
        <p style={styles.hint}>Gõ từ khóa; sau 500 ms sẽ gọi API.</p>
      )}

      {!loading && keyword.trim() !== "" && users.length === 0 && !error && (
        <p style={styles.hint}>Không có kết quả.</p>
      )}

      <ul style={styles.list}>
        {users.map((u) => (
          <li key={u.id} style={styles.item}>
            <b>{u.name}</b> — {u.email}
          </li>
        ))}
      </ul>
    </div>
  );
}

// =========================
// SIMPLE CSS IN JS
// =========================
const styles = {
  container: {
    padding: 30,
    fontFamily: "Arial",
  },
  input: {
    width: 300,
    padding: 10,
    marginBottom: 20,
    borderRadius: 6,
    border: "1px solid #ccc",
  },
  list: {
    listStyle: "none",
    padding: 0,
  },
  item: {
    padding: 8,
    borderBottom: "1px solid #eee",
  },
  hint: {
    color: "#666",
    fontSize: 14,
  },
  error: {
    color: "#b00020",
    fontSize: 14,
  },
};