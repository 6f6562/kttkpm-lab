const express = require("express");
const router = express.Router();

const service = require("./payment.service");

router.post("/:id", async (req, res) => {
    try {
        const result = await service.payOrder(req.params.id);
        res.json(result);
    } catch (e) {
        res.status(400).json({ error: e.message });
    }
});

module.exports = router;
