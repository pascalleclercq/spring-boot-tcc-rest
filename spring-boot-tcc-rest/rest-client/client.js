
var http = require('http');
var flight = require('./flight.js');
var hotel = require('./hotel.js');


var args = process.argv.slice(2);


var path = '/coordinator/confirm';
if (args[0] && args[0].toUpperCase() == 'CANCEL') {
	path = '/coordinator/cancel';
	console.log("test cancel operation on coordinator");
} else {
	console.log("test confirm operation on coordinator");
}

//later we confirm all (or cancel all).
setTimeout( function() {
    var options = {
        host: 'localhost',
        path: path,
        port: '9001',
        method: 'PUT',
        headers: {
            'Content-Type': 'application/tcc+json'
        }
    };
    
    var bookedFlight = flight.getBookedFlight();
    var roomReservation = hotel.getReservation();
    
    var payload = {
    		//TODO : Add expires in format ISO... for expiry
        "participantLinks": [{
            "uri": "http://localhost:8080/flight/" + bookedFlight.id,
            "expires": bookedFlight.expires
        }, {
            "uri": "http://localhost:9090/hotel/" + roomReservation.id,
            "expires": roomReservation.expires
        }]
    };

    displayStatus = function(response) {
        console.log(`STATUS: ${response.statusCode}`);
    }

    var req = http.request(options, displayStatus);
    console.log('URL to call on coordinator http://' + options.host + options.path);
    console.log('With payload '+JSON.stringify(payload));
    req.write(JSON.stringify(payload));
    req.end();
    
}, 2000);