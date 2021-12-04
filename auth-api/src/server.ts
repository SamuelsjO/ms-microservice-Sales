import app from './app';
import './database';
import base64topdf from 'pdf-to-base64';

const PORT = 3333;

app.get('/api/status', (req, res) => {
  return res.status(200).json({
    service: 'auth-api',
    status: 'up',
    httpStatus: 200,
  });
});

const hash = base64topdf('sample.pdf');

// console.log('passou aqui  :>> ', hash);

app.listen(PORT, () => console.log(`Server Running in port ${PORT}`));
