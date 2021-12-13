import app from './app';
import './database';

const PORT = 3333;

app.listen(PORT, () => console.log(`Server Running in port ${PORT}`));
