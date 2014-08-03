var WebSocket = require('ws');
	client = new WebSocket("ws://localhost:7204/myo/1"),
	herokuAddr = "ws://yc-2014-myo.herokuapp.com",
	heroku = new WebSocket(herokuAddr);

var herokuConnected = false;

heroku.on('open', function () {
	console.log("heroku connected");
	herokuConnected = true;
});

heroku.on('message', function (message) {
	console.log(message);
});

function restartHerokuSession(message) {
	herokuConnected = false;
	heroku = new WebSocket(herokuAddr);
}

heroku.on('close', restartHerokuSession);
heroku.on('error', restartHerokuSession);

client.on('message', function(message) {
	messageJson = JSON.parse(message);
	if (herokuConnected && messageJson[1]['type'] != "orientation") {
		console.log(messageJson[1]['type']);
	    heroku.send(message);
	}
});
