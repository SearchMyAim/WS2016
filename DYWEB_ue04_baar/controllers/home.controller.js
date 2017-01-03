const homeModel = require('./../models/home.model');
const homeView = require('./../views/home.view');

function handleHomeGet (req, res) {
    res.send(homeView(homeModel));
}

// We're not restricted to only export functions. We can also export an object
// that holds references to multiple functions.
module.exports = {
    getHome: handleHomeGet
};