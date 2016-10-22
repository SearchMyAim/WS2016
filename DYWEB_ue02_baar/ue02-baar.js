const http = require('http');
const url = require('url');
const querystring = require('querystring');
const fs = require('fs');
const parse = require('csv-parse');
const hbs = require('handlebars');

//File Reads
var helloTemplate;
fs.readFile('helloTemplate.hbs', 'utf-8', (err, data) => {
    helloTemplate = hbs.compile(data);
});

var fixtures;
fs.readFile('deutsche-bundesliga-2016-2017-fixtures.csv', 'utf-8', function(err, data) {
    if(err) throw err;

    const parseOptions = {
        "columns": true,
        "auto_parse": true,
        "auto_parse_data": true
    };

    parse(data, parseOptions, function(err, result) {
        if(err) throw err;
        fixtures = result;
    });
});

// Helper
hbs.registerHelper('list', function(items, options) {
    if(items == null) {
        return null;
    }
    var out = "<ul>";

    for(var i=0; i < items.length; i++) {
        out = out + "<li>" + options.fn(items[i]) + "</li>";
    }

    return out + "</ul>";
});

// Partials
hbs.registerPartial("footer", `<footer id="footer">(c) Baar 2016</footer>`);
hbs.registerPartial("nav", `
    <nav id="nav">
        <a href="/">Startseite</a>
        <a href="/matchday">Matchdays</a>
    </nav>`);

var server = http.createServer(function(request, response) {
    const parsedUrl = url.parse(request.url);

    var data;
    var body;
    var error = 0;
    var  pathname = parsedUrl.pathname.split("/");

    switch(pathname[1]) {

        case "matchday":
            data = [];
            var idx = 0;
            if((pathname.length > 2) && (pathname[2] > 0)) {
                //Extract all matches for the given 'pathname[2]' matchday
                for(var i = 0; i < fixtures.length; i++) {
                    if(fixtures[i].matchDay == pathname[2]) {
                        data[idx++] = fixtures[i];
                    }
                }

                //if no match on the given day has been found
                if(data.length == 0) {
                    error = 500;
                    break;
                }
                body = tableMatchDay(data);
            }
            else {
                data[idx++] = fixtures[0];
                for(var i = 1; i < fixtures.length; i++) {
                    if(fixtures[i].matchDay == data[idx - 1].matchDay) {
                        continue;
                    }
                    else {
                        data[idx++] = fixtures[i];
                    }
                }
                body = listMatchdays(data);
            }
            break;

        default:
            status = 404;
            break;
    }


    if(error == 0) {
        response.write(createHtml("Test", body, data));
    }
    else {
        response.status = error;
        response.write("Error");
    }

    response.end();
});

function createHtml(title, body, data) {
    return hbs.compile(`<!DOCTYPE html>
        <html>
            <head>
                <meta charset="utf-8">
                <title>HTML Seite mit Handlebars</title>            
            </head>
            <body>
                {{> nav}}
                ${body}    
                {{> footer}}
            </body>            
        </html>`)(data);
}

function listMatchdays(data) {
    return hbs.compile(`<p>Matchdays:</p>
                        {{#list this}} 
                            <a href="http://localhost:3000/matchday/{{matchDay}}"> {{matchDay}} </a>
                        {{/list}}`)
                        (data);
}

//Create Table for every match on this Matchday
function tableMatchDay(data) {
    return hbs.compile(`<table>
                            <tr>
                                <th>Matchday</th>
                                <th>Date</th>
                                <th>Home Club</th>
                                <th></th>
                                <th></th>
                                <th></th>
                                <th>Foreign Club</th>
                            </tr>
                            {{#each this}}
                                <tr> 
                                    <td><a href="http://localhost:3000/matchday/{{matchDay}}"> {{matchDay}} </a> </td>
                                    <td>{{date}}</td>
                                    <td>{{nameHomeClub}} </td>
                                    <td>{{goalsHomeClub}}</td>
                                    <td> - </td>
                                    <td>{{goalsAwayClub}}</td>
                                    <td>{{nameAwayClub}}</td>
                                </tr>
                            {{/each}}
                        </table>`)(data);
}

server.listen(3000);