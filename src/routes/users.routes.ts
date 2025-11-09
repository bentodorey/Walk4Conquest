import { Router } from "express";
import { requireAuth } from "../middleware/auth.js";
import { query } from "../db/pool.js";


const r = Router();

r.get("/me", requireAuth, async (req: any, res) => {
  const userId = req.user.sub;
  const { rows } = await query("SELECT id, email, display_name, role, created_at FROM users WHERE id=$1", [userId]);
  res.json(rows[0]);
});

r.patch("/me", requireAuth, async (req: any, res) => {
  const userId = req.user.sub;
  const { display_name } = req.body;
  if (!display_name) return res.status(400).json({ error: "display_name required" });
  const { rows } = await query(
    "UPDATE users SET display_name=$1, updated_at=NOW() WHERE id=$2 RETURNING id, email, display_name, role",
    [display_name, userId]
  );
  res.json(rows[0]);
});

export default r;

