/**
 * Created by Alexander Baar on 28.11.2016.
 */

/* Includes */
const   http        = require('http'),
        url         = require('url'),
        hbs         = require('handlebars'),
        fs          = require('fs'),
        htmlHandler = require("./htmlTemplates"),
        formidable  = require('formidable'),
        util        = require('util'),
        glob        = require('glob');

/* Sub-Directories must exist. */
const usrDir = "./usrDir/";
if(!fs.existsSync(usrDir)) {
    fs.mkdirSync(usrDir);
}

/* Routes */
const routes = [
    {
        rex: /^\/$/,
        methods: {
            'get': getStartPage
        }
    },
    {
        rex: /^\/signup\/{0,1}$/,
        methods: {
            'get': getSignUp,
            'post': getSignUp
        }
    },
    {
        rex: /^\/newuser\/{0,1}$/,
        methods: {
            'post': postNewUser
        }
    },
    {
        rex: /^\/[a-zA-Z0-9_]{1,255}$/,
        methods: {
            'post': userProfil,
            'get': userProfil
        }
    },
    {
        rex: /^\/[a-zA-Z0-9_]{1,255}\/edit$/,
        methods: {
            'post': getSignUp,
            'get': getSignUp
        }
    },
    {
        rex: /^\/usrDir\/data_[a-zA-Z0-9_]{1,255}\/image.(jpg|jpeg|png)$/,
        methods: {
            'get': getImg
        }
    },
    {
        rex: /^\/favicon.ico$/,
        methods: {
            'get': getFavicon
        }
    },
    {
        rex: /^\/pics\/[a-zA-Z]{1,25}_icon.png$/,
        methods: {
            'get': getIcon
        }
    }



];

/* Partials */
hbs.registerPartial('startPage',    htmlHandler.getStartPage());
hbs.registerPartial('signUp',       htmlHandler.getSignUp());
hbs.registerPartial('newUser',      htmlHandler.getUserSuccess());
hbs.registerPartial('userProfil',   htmlHandler.getUserProfil());
hbs.registerPartial('err500',       htmlHandler.getError500());
hbs.registerPartial('err400',       htmlHandler.getError400());
hbs.registerPartial('err404',       htmlHandler.getError404());
hbs.registerPartial('err405',       htmlHandler.getError405());

/* Functions */
function genError(req, res, error) {
    res.setHeader('Content-Type', 'text/html');
    res.statusCode = error;
    res.write(layout({
        bodyPartial: "err" + error.toString(),
        title: "test"
    }));
    res.end();
}

function getStartPage(req, res) {
    res.setHeader('Content-Type', 'text/html');
    res.statusCode = 200;
    res.write(layout({
        title: "Start Page",
        bodyPartial: "startPage"
    }));
    res.end();
}

function getSignUp(req, res) {
    var name = url.parse(req.url).pathname.split("/")[1];
    var username = null;
    if(name != 'signup') {
        username = name;
    }
    res.setHeader('Content-Type', 'text/html');
    res.statusCode = 200;
    res.write(layout({
        title: "Sign-Up",
        bodyPartial: "signUp",
        action: "newuser",
        username: username
    }));
    res.end();
}

/**
 *
 * @param req
 * @param res
 */
function postNewUser(req, res) {
    var form = new formidable.IncomingForm();
    form.parse(req, (err, fields, files) => {
        if(err) throw err;
        var name = fields.Username;
        var path = usrDir + 'data_' + name + "/";
        if(!fs.existsSync(path)) {
            fs.mkdirSync(path);
        }

        form.uploadDir = path;
        const fileName = files.imagefile.name;
        const currentPath = files.imagefile.path;
        fs.renameSync(currentPath, form.uploadDir + "image" + getFileExt(fileName).toLowerCase());

        fs.writeFile(path + name + '.json', JSON.stringify(fields), 'utf-8', (err) => {
            if(err) throw err;

            res.setHeader('Content-Type', 'text/html');
            res.setHeader('Location', "/" + name);
            res.statusCode = 303;
            res.write(layout({
                title: "User created",
                bodyPartial: "newUser",
                action: name
            }));
            res.end();
        });
    });
}

/**
 *
 * @param req
 * @param res
 * @returns {*}
 */
function userProfil(req, res) {
    var name = url.parse(req.url).pathname.split("/")[1];
    var path = usrDir + 'data_' + name + "/";
    var file = name + '.json';

    if(!fs.existsSync(path + file)) {
        return genError(req, res, 404);
    }
    fs.readFile(path + file, 'utf-8', (err, data) => {
        if(err) throw err;
        var fields = JSON.parse(data);

        var temp = glob.sync(path + "image.*");
        var img = null;
        if(temp != null) {
            img = temp[0];
        }
        res.setHeader('Content-Type', 'text/html');
        res.statusCode = 200;
        res.write(layout({
            title: "User Profil",
            bodyPartial: 'userProfil',
            action: name + "/edit",
            data: fields,
            imgSrc: img
        }));
        res.end();
    });
}

/**
 *
 * @param req
 * @param res
 */
function getImg(req, res) {
    var parsedUrl = url.parse(req.url);

    var result = /image\.(jpg|jpeg|png)$/.exec(parsedUrl.pathname);
    var fileName = result[0];
    var fileExt = result[1];
    var path = "." + parsedUrl.pathname;

    if(fileName) {
        fs.readFile(path, '', (err, data) => {
            if (err) {
                genError(req, res, 404);
            } else {
                var contentType = fileExt === "jpeg" || fileExt === "jpg" ? "image/jpeg" : "image/png";
                res.statusCode = 200;
                res.setHeader('Content-Type', contentType);
                res.write(data);
                res.end();
            }
        });
    }
    else {
        genError(req, res, 404);
    }
}

/**
 *
 * @param req
 * @param res
 */
function getFavicon(req, res) {
    fs.readFile("./pics/favicon.ico", '', (err, data) => {
       if(err) throw err;
       res.statusCode = 200;
       res.setHeader('Content-Type', 'image/x-icon');
       res.write(data);
       res.end();
    });
}

/**
 *
 * @param req
 * @param res
 */
function getIcon(req, res) {
   var parsedUrl = url.parse(req.url);
   var icon = parsedUrl.pathname.split("/")[2];
   fs.readFile("./pics/" + icon, '', (err, data) => {
       if(err) throw err;
       res.statusCode = 200;
       res.setHeader('Content-Type', 'image/png');
       res.write(data);
       res.end();
   });

}


/**
 *
 * @param fileName
 * @returns {string}
 */
function getFileExt (fileName) {
    return fileName ? fileName.substr(fileName.lastIndexOf(".")) : "";
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
            if(route.methods[method] == null) {
                genError(req, res, 405);
            }
            else {
                const handler = route.methods[method];
                handler(req, res);
            }
        }
        else {
            genError(req, res, 400);
        }
    } catch (err) {
        genError(req, res, 500);
    }
}).listen(3000);
