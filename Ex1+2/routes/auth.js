const express = require('express');
const router = express.Router();
const jwt = require('jsonwebtoken');
const { verifyToken, checkRole } = require('../middleware/auth');

// Mock Database for Refresh Tokens
let refreshTokens = [];

// Generate Tokens
const generateAccessToken = (user) => {
    return jwt.sign(user, process.env.ACCESS_TOKEN_SECRET, { expiresIn: '5m' }); // Short-lived
};

const generateRefreshToken = (user) => {
    return jwt.sign(user, process.env.REFRESH_TOKEN_SECRET); // Long-lived
};

/* POST /login */
router.post('/login', (req, res) => {
    const { username } = req.body;

    // Mock Authentication Logic
    // If username is "admin", give admin role. Otherwise "guest".
    const user = {
        username: username,
        role: username === 'admin' ? 'admin' : 'guest'
    };

    const accessToken = generateAccessToken(user);
    const refreshToken = generateRefreshToken(user);

    refreshTokens.push(refreshToken); // Store refresh token

    res.json({
        accessToken,
        refreshToken,
        message: `Logged in as ${user.role}`
    });
});

/* POST /refresh */
router.post('/refresh', (req, res) => {
    const { token } = req.body;

    if (!token) return res.status(401).json({ message: 'Refresh Token required' });
    if (!refreshTokens.includes(token)) return res.status(403).json({ message: 'Invalid Refresh Token' });

    jwt.verify(token, process.env.REFRESH_TOKEN_SECRET, (err, user) => {
        if (err) return res.status(403).json({ message: 'Invalid Refresh Token' });

        // User object contains extra JWT fields (iat, exp), we need clean object
        const cleanUser = { username: user.username, role: user.role };

        const accessToken = generateAccessToken(cleanUser);
        res.json({ accessToken });
    });
});

/* GET /profile (Protected) */
router.get('/profile', verifyToken, (req, res) => {
    res.json({
        message: 'This is a protected profile.',
        user: req.user
    });
});

/* GET /admin (Admin Only) */
router.get('/admin', verifyToken, checkRole('admin'), (req, res) => {
    res.json({
        message: 'Welcome Admin. You have access to sensitive data.',
        user: req.user
    });
});

/* POST /logout */
router.post('/logout', (req, res) => {
    const { token } = req.body;
    refreshTokens = refreshTokens.filter(t => t !== token);
    res.json({ message: 'Logged out successfully' });
});

module.exports = router;
