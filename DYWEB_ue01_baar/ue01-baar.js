const http = require("http");
const url = require("url");

const server = http.createServer(function(request, response) {
    const parsedUrl = url.parse(request.url);
    var body = "Falsche UrL";
    var statusCode = 200;
    var contentType = 'plain';
    var content = "notnull";

    var year = new Date().getFullYear();

    switch(parsedUrl.pathname) {
      case "/static-page":
        content = "Baar Alexander";
        contentType = 'text/html';
        break;

        case "/hello-world":
          body = "Hello World!";
          contentType = 'text/plain'
          break;

        case "/date-time":
          content = new Date().getFullYear() + '-' + new Date().getMonth();
          contentType = 'text/html';
          break;

        default:
          //default types are defined at var definition
          statusCode = 404;
          break;
    }

    body = `<!DOCTYPE html>
    <html>
      <head>
        <meta charset = "utf-8">;
      </head>
      <body>
        <h1>Static Page</h1>
        <p>${content}</p>
      </body>
    </html>`;
    contentType =

    response.writeHead(statusCode, {
        'Content-Lengt': Buffer.byteLength(body),
        'Content-Type': contentType
    });
    response.write(body);
    response.end();
});

server.listen(3000);
