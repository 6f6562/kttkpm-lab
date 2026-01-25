const jwt = require('jsonwebtoken');

function verifyToken(req, res, next) {
    const authHeader = req.headers['authorization'];
    const token = authHeader && authHeader.split(' ')[1]; // Bearer TOKEN

    if (!token) {
        return res.status(401).json({ message: 'Access Denied: No Token Provided' });
    }

    try {
        const decoded = jwt.verify(token, process.env.ACCESS_TOKEN_SECRET);
        req.user = decoded; // { username, role, iat, exp }
        next();
    } catch (error) {
        return res.status(403).json({ message: 'Invalid Token' });
    }
}

function checkRole(requiredRole) {
    return (req, res, next) => {
        if (!req.user) {
            return res.status(401).json({ message: 'Unauthorized' });
        }
        if (req.user.role !== requiredRole) {
            return res.status(403).json({ message: `Access Denied: Requires ${requiredRole} role` });
        }
        next();
    };
}

module.exports = { verifyToken, checkRole };
