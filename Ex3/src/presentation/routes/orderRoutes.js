const express = require('express');
const router = express.Router();
const OrderController = require('../controllers/OrderController');

// Define route for synchronous order creation
router.post('/sync', OrderController.createOrderSync.bind(OrderController));

// Optional: GET route to render the page initially
router.get('/', (req, res) => {
    res.render('index', { title: 'Order System' });
});

module.exports = router;
