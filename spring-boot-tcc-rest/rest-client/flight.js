var http = require('http');

var bookedFlight;
// book a flight
var optionsFlight = {
	host : 'localhost',
	path : '/jersey/flight',
	port : '8080',
	method : 'POST',
	headers : {
		'Content-Type' : 'application/json'
	}
};

var flight = {
	"departure" : 1470132000000,
	"departureAirport" : "Paris",
	"landing" : 1470139200000,
	"landingAirort" : "New-York"
};

callbackFlight = function(response) {
	var str = ''
	response.on('data', function(chunk) {
		str += chunk;
	});

	response.on('end', function() {
		bookedFlight = JSON.parse(str);
	});
}

var req = http.request(optionsFlight, callbackFlight);
// This is the data we are posting, it needs to be a string or a buffer
req.write(JSON.stringify(flight));
req.end();

module.exports = {
	getBookedFlight : function() {
		return bookedFlight;
	}
}
