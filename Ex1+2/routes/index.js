var express = require('express');
var router = express.Router();
var producer = require('../producer');
var axios = require('axios');
var amqp = require('amqplib');

const QUEUE_NAME = 'task_queue';
const RABBITMQ_URL = 'amqp://127.0.0.1:5672';
const MANAGEMENT_API = 'http://127.0.0.1:15672/api/queues/%2F/task_queue/get';

// Helper to peek at messages
async function getQueueMessages() {
  try {
    // 1. Ensure queue exists via AMQP first (prevents 404)
    const connection = await amqp.connect(RABBITMQ_URL);
    const channel = await connection.createChannel();
    await channel.assertQueue(QUEUE_NAME, { durable: true });
    await channel.close();
    await connection.close();

    // 2. Fetch messages via Management API
    const response = await axios.post(MANAGEMENT_API, {
      count: 100,
      ackmode: "ack_requeue_true",
      encoding: "auto",
      truncate: 50000
    }, {
      auth: { username: 'guest', password: 'guest' }
    });
    return response.data;
  } catch (error) {
    if (error.response && error.response.status === 404) {
      console.warn("Queue not found (404), returning empty list.");
      return [];
    }
    console.error("Management API Error:", error.message);
    return [];
  }
}

// Helper to manually consume one message
async function consumeOneMessage() {
  let connection, channel;
  try {
    connection = await amqp.connect(RABBITMQ_URL);
    channel = await connection.createChannel();
    await channel.assertQueue(QUEUE_NAME, { durable: true });

    const msg = await channel.get(QUEUE_NAME, { noAck: false });
    let content = null;

    if (msg) {
      content = msg.content.toString();
      channel.ack(msg);
    }

    await channel.close();
    await connection.close();
    return content;
  } catch (error) {
    console.error("Manual Consume Error:", error);
    if (channel) await channel.close();
    if (connection) await connection.close();
    throw error;
  }
}

/* GET home page. */
router.get('/', async function (req, res, next) {
  res.render('index', {
    title: 'Message Queue Simulator',
    status: null,
    messages: []
  });
});

/* GET inspect queue (Peek). */
router.get('/get', async function (req, res, next) {
  const messages = await getQueueMessages();
  res.render('index', {
    title: 'Message Queue Simulator',
    status: `Retrieved ${messages.length} messages`,
    messages: messages
  });
});

/* POST send message. */
router.post('/send', async function (req, res, next) {
  const message = req.body.message || 'Hello World';
  const result = await producer.sendMessage({ text: message, timestamp: new Date() });

  res.render('index', {
    title: 'Message Queue Simulator',
    status: result.status === 'success' ? `Sent: "${message}"` : `Error: ${result.message}`,
    messages: []
  });
});

/* POST consume one message. */
router.post('/consume', async function (req, res, next) {
  try {
    const content = await consumeOneMessage();
    const messages = await getQueueMessages(); // Refresh list to show it's gone

    res.render('index', {
      title: 'Message Queue Simulator',
      status: content ? `Consumed: "${content}"` : 'Queue is empty!',
      messages: messages
    });
  } catch (error) {
    res.render('index', {
      title: 'Message Queue Simulator',
      status: `Error: ${error.message}`,
      messages: []
    });
  }
});

module.exports = router;
