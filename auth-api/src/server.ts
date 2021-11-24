import app from './app';
import './database';

const PORT = 3333;

app.get('/api/status', (req, res) => {
  return res.status(200).json({
    service: 'auth-api',
    status: 'up',
    httpStatus: 200,
  });
});

app.listen(PORT, () => console.log(`Server Running in port ${PORT}`));
