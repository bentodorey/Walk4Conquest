import { Request, Response, NextFunction } from "express";
import * as jwt from "jsonwebtoken";
import { env } from "../config/env.js";

export function requireAuth(req: Request, res: Response, next: NextFunction) {
  const header = (req.headers.authorization || "") as string;
  const token = header.startsWith("Bearer ") ? header.slice(7) : null;

  if (!token) return res.status(401).json({ error: "Missing token" });

  try {
    const payload = jwt.verify(token, env.JWT_SECRET) as { sub: string; role?: string };
    (req as any).user = payload;
    return next();
  } catch {
    return res.status(401).json({ error: "Invalid token" });
  }
}

export function signJwt(sub: string, role: string) {
  return jwt.sign({ sub, role }, env.JWT_SECRET, { expiresIn: "7d" });
}

export function requireRole(role: string) {
  return (req: Request, res: Response, next: NextFunction) => {
    const u = (req as any).user as { sub: string; role?: string } | undefined;
    if (!u) return res.status(401).json({ error: "Unauthorized" });
    if (u.role !== role) return res.status(403).json({ error: "Forbidden" });
    next();
  };
}
