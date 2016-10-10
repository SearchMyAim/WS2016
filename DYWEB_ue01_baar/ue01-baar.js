const http = require("http");
const url = require("url");

const server = http.createServer(function(request, response) {
    const parsedUrl = url.parse(request.url);

    var statusCode = 200;
    var contentType = 'text/html';
    var content = "notnull";
    var txth1 = "notnull";

    switch(parsedUrl.pathname) {
      case "/static-page":
        txth1 = "Static Page";
        content = "Baar Alexander";
        break;

      case "/hello-world":
        txth1 = "";
        content = "Hello World!";
        break;

      case "/date-time":
        txth1 = "";
        var date = new Date().toISOString();
        content = date.substring(0,10) + ' ' + date.substring(11,16);
        break;

      default:
        //404 Error, wrong URL hit.
        txth1 = "404 Error, Page not found!";
        content = "I've got a bad feeling about this. \n - Han Solo";
        statusCode = 404;
        break;
    }

    //Create HTML
    body = `<!DOCTYPE html>
    <html>
      <head>
        <meta charset = "utf-8">
      </head>
      <body>
        <h1>${txth1}</h1>
        <p>${content}</p>
      </body>
    </html>`;

    //Response to GET request with the created string.
    response.writeHead(statusCode, {
        'Content-Lengt': Buffer.byteLength(body),
        'Content-Type': contentType
    });
    response.write(body);
    response.end();
});

server.listen(3000);
