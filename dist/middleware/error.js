export function errorHandler(err, _req, res, _next) {
    console.error(err);
    if (err && err.status) {
        return res.status(err.status).json({ error: err.message });
    }
    res.status(500).json({ error: "Internal Server Error" });
}
