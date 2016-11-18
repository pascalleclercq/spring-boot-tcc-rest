var http = require('http');

var reservation = {};

// book a room in a hotel
var options = {
	host : 'localhost',
	path : '/hotel',
	port : '9090',
	method : 'POST',
	headers : {
		'Content-Type' : 'application/json'
	}
};

// book a hotel
var room = {
	"arrival" : "2016-06-09T22:27:01Z",
	"roomNumber" : "101",
	"numberOfPerson" : 2,
	"departure" : "2016-06-19T11:27:01Z"
};


readResponseData = function(response) {
	var str = ''
	response.on('data', function(chunk) {
		str += chunk;
	});

	response.on('end', function() {
		console.log('Booked a room in a hotel: ', str );
		reservation = JSON.parse(str);
	});
}

var req = http.request(options, readResponseData);
// This is the data we are posting, it needs to be a string or a buffer
req.write(JSON.stringify(room));
req.end();

module.exports = {
	getReservation : function() {
		return reservation;
	}
}
