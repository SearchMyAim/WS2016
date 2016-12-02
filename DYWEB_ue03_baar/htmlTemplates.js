/**
 * Created by Baar Alexander on 28.11.2016.
 */
const fs = require('fs');

(function() {

    /* Main HTML Template. */
    module.exports.getTemplate = function() {
        return String(fs.readFileSync('./templates/startpage.html'));
    }

    /* Front/Start Page. */
    module.exports.getStartPage = function() {
        return `<ul>
                  <li><a href="/">Home</a></li>
                  <!-- <li style="float:right"><a class="active" href="login">Login</a></li> -->
                  <li style="float:right"><a class="active" href="signup">Sign-Up</a></li>
                </ul>
                <div class="frontpage">
                    <h1>Welcome to <br /> About Me</h1>
                </div>`;
    }

    /* Sing-Up Page. */
    module.exports.getSignUp = function() {
        return String(fs.readFileSync('./templates/signup.html'));
    }

    /* User creation successful. */
    module.exports.getUserSuccess = function() {
        return `<ul>
                  <li><a href="/">Home</a></li>
                </ul>
                <h1>Creation Successful</h1>
                <h3>May the force be with you. - Obi-Wan</h3>
                <form action="{{action}}" method="post">
                    <button type="submit">Profil</button>
                </form>`;
    }

    /* User Profil Page. */
    module.exports.getUserProfil = function() {
        return String(fs.readFileSync('./templates/userProfil.html'));
    }

    /* Error 500. */
    module.exports.getError500 = function() {
        return `<div class="error">
                    <ul>
                      <li><a href="/">Home</a></li>
                    </ul>
                    <h2>Error 500</h2>
                    <h3>Internal Server Error</h3>
                    <p>How did you get over here? - Obi-Wan-Kenobi</p>
                </div>`;
    }

    /* Error 400. */
    module.exports.getError400 = function() {
        return `<div class="error">
                    <ul>
                      <li><a href="/">Home</a></li>
                    </ul>
                    <h2>Error 400</h2>
                    <h3>Bad Request</h3>
                    <p>I've got a bad feeling about this. - Anakin Skywalker</p>
                </div>`;
    }

    /* Error 400. */
    module.exports.getError404 = function() {
        return `<div class="error">
                    <ul>
                      <li><a href="/">Home</a></li>
                    </ul>
                    <h2>Error 404</h2>
                    <h3>Not Found</h3>
                    <p>If you’re saying that coming here was a bad idea, I’m starting to agree with you. - Luke Skywalker</p>
                </div>`;
    }

    /* Error 405. */
    module.exports.getError405 = function() {
        return `<div class="error">
                    <ul>
                      <li><a href="/">Home</a></li>
                    </ul>
                    <h2>Error 405</h2>
                    <h3>Method Not Allowed</h3>
                    <p>If once you start down the dark side, forever will it dominate your destiny, consume you it will, as it did Obi-Wan's apprentice. - Yoda</p>
                </div>`;
    }

}());
