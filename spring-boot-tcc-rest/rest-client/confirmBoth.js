var http = require('http');
var flight = require('./flight.js');	
var hotel = require('./hotel.js');	




setTimeout(function() {

	
	var options = {
			  host: 'localhost',
			  path: '/coordinator/confirm',
			  port: '9001',
			  method: 'PUT',
			  headers: {'Content-Type': 'application/tcc+json'}
			};
	
	var payload = {"participantLinks":
	    [{
	    	"uri": "http://localhost:8080//jersey/flight/"+flight.getBookedFlight().id,
	    	"expires": "2017-05-30T09:30:10Z"
	    },
	    {
	    	"uri": "http://localhost:9090//hotel/"+hotel.getReservation().id,
	    	"expires": "2017-05-30T09:30:10Z"
	    }
	    ]
	    };
	
	callback = function(response) {
		 console.log(`STATUS: ${response.statusCode}`);
		  var str = ''
		  response.on('data', function (chunk) {
		    str += chunk;
		  });

		  response.on('end', function () {			
		    console.log(str);
		  });
		}
	var req = http.request(options, callback);
	console.log(payload);
	req.write(JSON.stringify(payload));
	req.end();
}, 1000);