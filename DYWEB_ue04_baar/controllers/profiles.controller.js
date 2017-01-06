const util = require('util'),
    profiles = require('./../models/profiles.model.js'),
    profileViews = require('./../views/profile.views.js'),
    formidable  = require('formidable'),
    fs = require('fs');

/**
 *
 * @param req
 * @param res
 */
function handleNewProfileFormGet (req, res) {
    res.send(profileViews.newProfileView());
}

/**
 *
 * @param req
 * @param res
 */
function handleNewProfilePost (req, res) {
    if(profiles.profileExists(req.fields.nickName)) {
        res.status(200).send(profileViews.existingProfileCreate(req.fields.nickName));
    }
    else {
        handleExistingProfilePost(req, res);
    }
}


/**
 *
 * @param req
 * @param res
 */
function handleExistingProfilePost (req, res) {
    var imgName = "";

    if(req.files) {
        imgName = profiles.storeProfileImage(req.files, req.fields.nickName);
    }

    profiles.storeProfile(
        profiles.newProfile(
            req.fields.nickName,
            req.fields.firstName,
            req.fields.lastName,
            req.fields.description,
            req.fields.fbProfile,
            req.fields.twProfile,
            req.fields.xingProfile,
            imgName
        )
    );


    res.status(303).redirect('/profiles/' + req.fields.nickName);

}

function handleProfileGet (req, res) {
    if(!profiles.profileExists(req.params.nickname)) {
        res.status(404).send("handleProfileGet: profile doesn't exist.");
    }
    else {
        var data = profiles.getProfile(req.params.nickname);
        res.status(200).send(profileViews.existingProfileView(data, "User Profile"));
    }
}

function handleProfileEdit(req, res) {
    if(!profiles.profileExists(req.params.nickname)) {
        res.status(404).send("handleProfileEdit: Profile doesn't exist.");
    }
    else {
        var data = profiles.getProfile(req.params.nickname);
        res.setHeader('Content-Type', 'text/html');
        res.status(200).send(profileViews.editProfileView(data));
    }
}

function handleProfileImage(req, res) {
    res.setHeader('Content-Type', "image/png");
    res.status(200).send(profiles.getProfileImage(req.url));
}

// A collection of request handlers that shall be connected to routes within the router module
module.exports = {
    getNewProfileForm: handleNewProfileFormGet,
    postNewProfile: handleNewProfilePost,
    getProfile: handleProfileGet,
    editProfile: handleProfileEdit,
    editExistingProfile: handleExistingProfilePost,
    getImage: handleProfileImage
};