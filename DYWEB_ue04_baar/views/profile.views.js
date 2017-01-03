const hbs = require('handlebars');
const layout = require('./layout');

hbs.registerPartial('profile-form',
    `<h1>Neues Profil erstellen</h1>
     <form action="/profiles" method="post">
         <p><label>Kürzel: <br/><input type="text" name="nickname"></label></p>     
         <p><label>Vorname: <br/><input type="text" name="firstname"></label></p>     
         <p><label>Nachname: <br/><input type="text" name="lastname"></label></p>     
         <p><label>Beschreibung: <br/><textarea name="description"></textarea></label></p>     
         <p><label>Facebook: <br/><input type="text" name="facebookProfile"></label></p>     
         <p><label>Twitter: <br/><input type="text" name="twitterProfile"></label></p>     
         <p><label>Xing: <br/><input type="text" name="xingProfile"></label></p>     
         <p><button type="submit">Erstellen</button></p>     
     </form>
     `);

function renderNewProfileView () {
    const viewModel = { bodyPartial: 'profile-form' };
    return layout(viewModel);
}


// This module is a collection of views related to a single profile
// Note: for now we only have one view, but in the end there will be
// a couple of views being exported.
module.exports = {
    newProfileView: renderNewProfileView
};