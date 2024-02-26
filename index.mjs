import express from 'express';
import http from 'http';
import bodyParser from 'body-parser';

const PORT = 8080;
const app = express();

app.use(bodyParser.urlencoded({ extended: true, }));
app.use(express.static('buildtools/'));

http.createServer(app).listen(PORT, (req, res) => {
    console.log('ur mom');
});