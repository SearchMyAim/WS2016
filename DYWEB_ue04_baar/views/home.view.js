const hbs = require('handlebars');
const layout = require('./layout');

hbs.registerPartial('homepage',
    `<h1 class="centered">AboutMe &ndash; the easiest way to create your personal homepage!</h1>
     <h2>The path of the righteous man is beset on all sides by the iniquities of the selfish and the tyranny of evil men.</h2>
     <div class="cta"><a href="new">Sign Up!</a></div>`);

module.exports = function (data) {

    // Note I: Object.assign lets us merge two objects
    // Note II: we extend the model data that's been passed through the _data_ parameter
    // with a presentation-specific property, namely _bodyPartial_. Technically we could
    // set this property in the model directly, but we want the model layer to be independent
    // how it's being viewed. If we decide to change how views are rendered later on and
    // _bodyPartial_ makes no sense any longer, we can keep the model untouched.

    return layout(Object.assign(data, { bodyPartial: 'homepage' }));
};