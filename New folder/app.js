var mysql = require('mysql');
var request = require('request');
var fs = require('fs');
var csv = require('csvtojson');

var GoogleMapsAPIKey = "AIzaSyBWzDr8kIMKTfXBuZwwyrthVDGJhWGz5FA";

var headersAdded = false;

var connection = mysql.createConnection({
    host: '',
    user: 'root',
    password: 'Kaushik@1993',
    database: 'auctionsoftware'
});

connection.connect(function (err) {
    if (!err) {
        console.log("Database is connected ... nn");
        csv()
            .fromStream(request.get('https://raw.githubusercontent.com/copart-internship/copart-internship.github.io/master/Documents/VehicleDetails.csv'))
            .on('json', function (csvRow) {
                if (!headersAdded) {
                    //This is header row
                    headersAdded = true;
                    rowNames = Object.keys(csvRow);
                    var query = "CREATE TABLE TABLE_NAME(";
                    rowNames.forEach(function (name) {
                        name = name.replace(/\s/g, '');
                        name = name.replace("/", '');
                        query += (name + " VARCHAR(255),");
                    });
                    query += "Lat VARCHAR(255),Lng VARCHAR(255))";
                    connection.query(query, function (err, rows, fields) {
                        if (err) {
                            console.log("Unable to create the table.");
                        }
                    });
                }
                var query = "INSERT INTO TABLE_NAME VALUES(";
                rowNames.forEach(function (name) {
                    if (name === "Est")
                        query += ("'" + csvRow[name][" Retail Value"] + "',");
                    else
                        query += ("'" + csvRow[name] + "',");
                });
                var MapsURL = "https://maps.googleapis.com/maps/api/geocode/json?address=LOCATION&key=APIKEY";
                MapsURL = MapsURL.replace('LOCATION', csvRow['Location']);
                MapsURL = MapsURL.replace('APIKEY', GoogleMapsAPIKey);
                request.get({url: MapsURL, json: true}, function (err, response, profile) {
                    if (!err){
                        var results = response.body.results;
                        if (results[0]!=undefined){
                            var location = response.body.results[0].geometry.location;
                            query += ("'" + (location.lat).toString() + "','" + (location.lng).toString() + "')");
                            connection.query(query, function (err, rows, fields) {
                                if (err) {
                                    console.log("Unable to save the row.");
                                }
                            });
                        }
                    }
                    else{
                        console.log(err)
                    }
                });
            });

    } else {
        console.log("Error connecting database ... nn");
    }
});

var rowNames = [];
