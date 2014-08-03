var WebSocket = require('ws');
	client = new WebSocket("ws://localhost:7204/myo/1"),
	heroku = new WebSocket("ws://yc-2014-myo.herokuapp.com");

var herokuConnected = false;

heroku.on('open', function () {
	console.log("heroku connected");
	herokuConnected = true;
});

heroku.on('message', function (message) {
	console.log(message);
});

client.on('message', function(message) {
	messageJson = JSON.parse(message);
	if (herokuConnected && messageJson[1]['type'] != "orientation") {
		console.log(messageJson[1]['type']);
	    heroku.send(message, function(error) { });
	}
    // console.log(message);
});
