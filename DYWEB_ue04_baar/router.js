const formidable = require('express-formidable'),
    homeController = require('./controllers/home.controller'),
    profilesController = require('./controllers/profiles.controller.js');

module.exports = function (app) {
    app.get('/', homeController.getHome);
    app.get('/new', profilesController.getNewProfileForm);

    // Note: express-formidable is formidable as we know it wrapped as
    // a express.js middleware function.
    // It's important that the registration of the formidable middleware
    // is before _profileController.postNewProfile_ because this determines
    // the order in which the handlers (e.g. middleware functions) are processed
    // by express.js when a POST request on '/new' is received by the server.
    //
    // How does the magic work?
    // The formidable middleware will parse the request body and add the new properties _fields_
    // and _files_ to the _req_ object. _postNewProfile_ is executed afterwards and
    // and gets passed the enriched _req_ object with the new properties.
    app.post('/profiles', formidable());
    app.post('/profiles', profilesController.postNewProfile);
    app.post('/profiles/:nickname/edit', formidable());
    app.post('/profiles/:nickname/edit', profilesController.editExistingProfile);

    // Note: ":nickname" is an express.js route parameter. We no longer need to parse
    // the nickname from the URI path ourselves!
    app.get('/profiles/:nickname', profilesController.getProfile);
    app.get('/profiles/:nickname/edit', profilesController.editProfile);
    app.get('/profiles/:nickname/image.*', profilesController.getImage);
};