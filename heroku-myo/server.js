var WebSocketServer = require("ws").Server
var http = require("http")
var express = require("express")
var app = express()
var port = process.env.PORT || 5000

app.use(express.static(__dirname + "/"))

var server = http.createServer(app)
server.listen(port)

var wss = new WebSocketServer({server: server})

var clients = [];

wss.on("connection", function(ws) {
  console.log("Received myo client");
  clients.push(ws);

  ws.on('message', function(message) {
    clients.forEach(function(client) {
      if (client != null)
        client.send(message, function(error) { });
    });
  });
})
