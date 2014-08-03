var WebSocket = require('ws'),
    client = new WebSocket("ws://localhost:7204/myo/1"),
    server = new WebSocket.Server({ port: 8000 });

var connected_client = null;

server.on('connection', function(ws) {
    connected_client = ws;
});

client.on('message', function(message) {
    if (connected_client != null) {
        connected_client.send(message);
    }
});
