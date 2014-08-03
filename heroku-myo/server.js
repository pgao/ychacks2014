var WebSocket = require('ws'),
    myoServer = new WebSocket.Server({ port: 8000}),
    androidServer = new WebSocket.Server({ port: 8001});

var myoClient = null, androidClient = null;

myoServer.on('connection', function(ws) {
  console.log("Received myo client");
  myoClient = ws;

  ws.on('message', function(message) {
    if (androidClient != null) {
      androidClient.send(message);
    }
  });
});

androidServer.on('connection', function(ws) {
  console.log("Received android client.");
  androidClient = ws;
});
