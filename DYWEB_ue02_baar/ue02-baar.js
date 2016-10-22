const http = require('http');
const url = require('url');
const querystring = require('querystring');
const fs = require('fs');
const parse = require('csv-parse');
const hbs = require('handlebars');

// File Reads
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
    if(request.method != "GET") {
        response.error = 405;
        response.write("Not supported request method!");
        return;
    }
    const parsedUrl = url.parse(request.url);

    var data;
    var title = "";
    var body = "";
    var error = 0;
    var pathname = parsedUrl.pathname.split("/");
    var query = querystring.parse(parsedUrl.query);

    try {
        switch (pathname[1]) {
            case "":
                title = "<h1>Startseite</h1>";
                body = `<body><p>It's against my programming to impersonate a deity.</p>
                        <p>- C-3PO, Star Wars Episode VI: Return of the Jedi</p></body>`;
                break;

            case "matchday":
                data = [];
                var mDay;
                var idx = 0;

                if (query.matchDay != null) {
                    mDay = query.matchDay;
                }
                else if ((pathname.length > 2) && (pathname[2] > 0)) {
                    mDay = pathname[2];
                }
                else {
                    data[idx++] = fixtures[0];
                    for (var i = 1; i < fixtures.length; i++) {
                        if (fixtures[i].matchDay == data[idx - 1].matchDay) {
                            continue;
                        }
                        else {
                            data[idx++] = fixtures[i];
                        }
                    }
                    body = `<form action="/matchday" method="get">
                            <input type="text" name="matchDay">
                            <button type="submit">Filter</button>
                        </form>`;
                    body += listMatchdays(data);
                    break;
                }

                //Extract all matches for the given  matchday ('pathname[2]')
                for (var i = 0; i < fixtures.length; i++) {
                    if (fixtures[i].matchDay == mDay) {
                        data[idx++] = fixtures[i];
                    }
                }

                //if no match on the given day has been found
                if (data.length == 0) {
                    error = 404;
                    break;
                }
                body = tableMatchDay(data);
                break;

            default:
                status = 404;
                break;
        }
    } catch (e) {
        response.status = 500;
        response.write( "Lost a planet, Master Obi-Wan has. How embarrassing. How embarrassing." +
                        "\n- YODA, Star Wars Episode II: Attack of the Clones");
        response.end();
        return;
    }

    if(error == 0) {
        response.write(createHtml(title, body, data));
    }
    else {
        response.status = error;
        response.write("Error 404. Page not found!" +
                       "\n\nWould it help if I got out and pushed?" +
                       "\n- PRINCESS LEIA, Star Wars Episode V: The Empire Strikes Back");
    }

    response.end();
});

/**
 * Create and compile (handlebars) the given data to an finalized HTML document.
 * @param title string object which will be placed above body.
 * @param body html body as string.
 * @param data object for handlebars compiler.
 * @returns {*}
 */
function createHtml(title, body, data) {
    return hbs.compile(`<!DOCTYPE html>
        <html>
            <head>
                <meta charset="utf-8">
                <title>HTML Seite mit Handlebars</title>   
                <style>
                    body {      background-color: #EAEAEA;
                                font-family: Arial, Helvetica, sans-serif; }
                    footer {    background-color: #FFA42D;
                                padding: 10px;
                                margin: 15px 0px 15px 0px;
                                text-align: center; }
                    nav {       background-color: #FFA42D;
                                padding: 10px;
                                margin: 15px 0px 15px 0px; }
                    #main {     padding: 15px 0px 15px 20px;                                
                                background-color: #C0C3CD; }
                </style>
            </head>
            <body>            
                {{> nav}}
                ${title}
                <div id="main">
                    ${body}    
                </div>
                {{> footer}}
            </body>            
        </html>`)(data);
}

/**
 * Create a list of matchDays.
 * @param data
 * @returns {*}
 */
function listMatchdays(data) {
    return hbs.compile(`<p>Matchdays:</p>
                        {{#list this}} 
                            <a href="http://localhost:3000/matchday/{{matchDay}}"> {{matchDay}} </a>
                        {{/list}}`)
                        (data);
}

/**
 * Create Table for every match on this Matchday.
 * @param data
 * @returns {*}
 */
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