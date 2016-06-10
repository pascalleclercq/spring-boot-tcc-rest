
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

setTimeout(function() {
    var options = {
        host: 'localhost',
        path: path,
        port: '9001',
        method: 'PUT',
        headers: {
            'Content-Type': 'application/tcc+json'
        }
    };

    var payload = {
        "participantLinks": [{
            "uri": "http://localhost:8080//flight/" + flight.getBookedFlight().id,
            "expires": "2017-05-30T09:30:10Z"
        }, {
            "uri": "http://localhost:9090/hotel/" + hotel.getReservation().id,
            "expires": "2017-05-30T09:30:10Z"
        }]
    };

    callback = function(response) {
        console.log(`STATUS: ${response.statusCode}`);
    }

    var req = http.request(options, callback);
    console.log('URL to call on coordinator http://' + options.host + options.path);
    console.log('With payload '+JSON.stringify(payload));
    req.write(JSON.stringify(payload));
    req.end();
    
}, 2000);