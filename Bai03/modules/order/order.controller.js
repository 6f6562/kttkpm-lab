const express = require("express");
const router = express.Router();

const service = require("./order.service");

router.post("/", async (req, res) => {
    try {
        const order = await service.createOrder(req.body.item);
        res.status(201).json(order);
    } catch (e) {
        res.status(400).json({ error: e.message });
    }
});

router.get("/", async (req, res) => {
    try {
        res.json(await service.getOrders());
    } catch (e) {
        res.status(500).json({ error: e.message });
    }
});

router.get("/:id", async (req, res) => {
    try {
        res.json(await service.getOrderById(req.params.id));
    } catch (e) {
        res.status(404).json({ error: e.message });
    }
});

module.exports = router;
