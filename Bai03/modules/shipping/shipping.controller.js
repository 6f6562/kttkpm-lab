const express = require("express");
const router = express.Router();

const repo = require("../order/order.repo");
const service = require("./shipping.service");

router.get("/", async (req, res) => {
    try {
        const orders = await repo.getAll();
        res.json(orders);
    } catch (e) {
        res.status(500).json({ error: e.message });
    }
});

router.post("/:id", async (req, res) => {
    try {
        const result = await service.shipOrder(req.params.id);
        res.json(result);
    } catch (e) {
        res.status(400).json({ error: e.message });
    }
});

module.exports = router;
