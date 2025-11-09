import express from "express";
import helmet from "helmet";
import cors from "cors";
import { env } from "./config/env.js";
import { errorHandler} from "./middleware/error.js";
import usersRoutes from "./routes/users.routes.js";
import swaggerUi from "swagger-ui-express";
import YAML from "yamljs";
import path from "path";

const app = express();

app.use(helmet());
app.use(cors());
app.use(express.json());

// Swagger / OpenAPI
const spec = YAML.load(path.join(process.cwd(), "src", "docs", "openapi.yaml"));
app.use("/docs", swaggerUi.serve, swaggerUi.setup(spec));

app.get("/", (_req, res) => {
  res.json({ ok: true, service: "Walk4Conquest API" });
});

app.use("/users", usersRoutes);

// app.use("/auth", authRoutes);
// app.use("/teams", teamsRoutes);
// app.use("/challenges", challengesRoutes);
// app.use("/activities", activitiesRoutes);
// app.use("/leaderboard", leaderboardRoutes);

app.use(errorHandler);

app.listen(env.PORT, () => {
  console.log(`API running at http://localhost:${env.PORT}`);
});
