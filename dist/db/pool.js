import { Pool } from "pg";
import { env } from "../config/env";
export const pool = new Pool({
    connectionString: env.DATABASE_URL,
});
export async function query(text, params) {
    const res = await pool.query(text, params);
    return res;
}
