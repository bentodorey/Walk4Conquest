import { Router } from "express";
import { query } from "../db/pool.js";
import { hashPassword, verifyPassword } from "../utils/password.js";
import { signJwt } from "../middleware/auth.js";
const r = Router();
r.post("/register", async (req, res) => {
    const { email, password, display_name } = req.body;
    if (!email || !password || !display_name)
        return res.status(400).json({ error: "Missing fields" });
    const exists = await query("SELECT 1 FROM users WHERE email=$1", [email]);
    if (exists.rowCount)
        return res.status(409).json({ error: "Email already in use" });
    const hash = hashPassword(password);
    const { rows } = await query("INSERT INTO users (email, password_hash, display_name) VALUES ($1,$2,$3) RETURNING id, role", [email, hash, display_name]);
    const token = signJwt(rows[0].id, rows[0].role);
    res.status(201).json({ token });
});
r.post("/login", async (req, res) => {
    const { email, password } = req.body;
    if (!email || !password)
        return res.status(400).json({ error: "Missing fields" });
    const { rows } = await query("SELECT id, password_hash, role, display_name FROM users WHERE email=$1", [email]);
    if (!rows.length || !verifyPassword(password, rows[0].password_hash))
        return res.status(401).json({ error: "Invalid credentials" });
    const token = signJwt(rows[0].id, rows[0].role);
    res.json({ token, display_name: rows[0].display_name });
});
export default r;
