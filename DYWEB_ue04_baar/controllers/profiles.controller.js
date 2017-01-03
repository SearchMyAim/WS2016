const util = require('util'),
    profiles = require('./../models/profiles.model.js'),
    profileViews = require('./../views/profile.views.js');

function handleNewProfileFormGet (req, res) {
    res.send(profileViews.newProfileView());
}

/**
 * After creation of a new User this method is
 * @param req
 * @param res
 */
function handleNewProfilePost (req, res) {
    res.setHeader('Content-Type', 'text/html');
    res.setHeader('Location', "/profiles/" + req.fields.nickname);
    res.status(303).end();
}

function handleProfileGet (req, res) {
    // TODO obviously we need to create a new view that displays the profile data;
    res.status(404).send(`Ups, seems that viewing a profile is not implemented yet. 
        We saw that you wanted to take a look at user '${req.params.nickname}', 
        students are working on it!`);
}

// A collection of request handlers that shall be connected to routes within the router module
module.exports = {
    getNewProfileForm: handleNewProfileFormGet,
    postNewProfile: handleNewProfilePost,
    getProfile: handleProfileGet
};