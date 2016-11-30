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
                  <li style="float:right"><a class="active" href="login">Login</a></li>
                  <li style="float:right"><a class="active" href="signup">Sign-Up</a></li>
                </ul>`;
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
                <h1>it's done</h1>
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
                    <h2>Error 500<h2>
                    <p>PlaceHolder</p>
                </div>`;
    }

    /* Error 400. */
    module.exports.getError400 = function() {
        return `<div class="error">
                    <ul>
                      <li><a href="/">Home</a></li>
                    </ul>
                    <h2>Error 400<h2>
                    <p>PlaceHolder</p>
                </div>`;
    }

    /* Error 405. */
    module.exports.getError405 = function() {
        return `<div class="error">
                    <ul>
                      <li><a href="/">Home</a></li>
                    </ul>
                    <h2>Error 405<h2>
                    <p>PlaceHolder</p>
                </div>`;
    }

}());
