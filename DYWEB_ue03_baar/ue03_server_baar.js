/**
 * Created by Alexander Baar on 28.11.2016.
 */

/* Includes */
const   http        = require('http'),
        url         = require('url'),
        hbs         = require('handlebars'),
        fs          = require('fs'),
        htmlHandler = require("./htmlTemplates");

/* Routes */
const routes = [
    {
        rex: /^\/$/,
        methods: {
            'get': getStartPage
        }
    },
    {
        rex: /^\/new\/{0,1}$/,
        methods: {
            'get': getSignUp
        }
    }

];

/* Partials */
hbs.registerPartial('startPage',    htmlHandler.getStartPage());
hbs.registerPartial('signUp',       htmlHandler.getSignUp());
hbs.registerPartial('err500',       htmlHandler.getError500());
hbs.registerPartial('err400',       htmlHandler.getError400());
hbs.registerPartial('err405',       htmlHandler.getError405());

/* Functions */
function genError(req, res, error) {
    res.setHeader('Content-Type', 'text/html');
    res.statusCode = error;
    var param = {
            bodyPartial: "err" + error.toString(),
            title: "test"
        };
    res.write(layout(param));
    res.end();
}

function getStartPage(req, res) {
    res.setHeader('Content-Type', 'text/html');
    res.statusCode = 200;
    res.write(layout({title: "Start Page", bodyPartial: "startPage"}));
    res.end();
}

function getSignUp(req, res) {
    res.setHeader('Content-Type', 'text/html');
    res.statusCode = 200;
    res.write(layout({title: "Sign-Up", bodyPartial: "signUp"}));
    res.end();
}

/* Compile HTML Page */
const layout = hbs.compile(htmlHandler.getTemplate());

/* Server */
const server = http.createServer(function(req, res) {
    try {
        const parsedUrl = url.parse(req.url);
        const method = req.method.toLowerCase();
        const route = routes.find(route => {
            return route.rex.test(parsedUrl.pathname);
        });

        if(route) {
            const handler = route.methods[method] || genError(req, res, 405); //or 405
            handler(req, res);
        }
        else {
            genError(req, res, 400);
        }
    } catch (err) {
        genError(req, res, 500);
    }
});

server.listen(3000);