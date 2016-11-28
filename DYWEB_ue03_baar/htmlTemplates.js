/**
 * Created by Baar Alexander on 28.11.2016.
 */

(function() {

    /* Main HTML Template. */
    module.exports.getTemplate = function() {
        return `<!DOCTYPE html>
                <html lang="en">
                    <head>
                        <meta charset="UTF-8">
                        <title>Titel - {{title}}</title>
                        <style>
                            html {
                                font-family: sans-serif;
                            }
                            ul {
                                list-style-type: none;
                                margin: 0;
                                padding: 0;
                                overflow: hidden;
                                background-color: #333;
                            }
                            
                            li {
                                float: left;
                            }
                            
                            li a {
                                display: block;
                                color: white;
                                text-align: center;
                                padding: 14px 16px;
                                text-decoration: none;
                            }
                            
                            li a:hover {
                                background-color: #111;
                            }
                        </style>
                    </head>
                    <body>
                        {{> (lookup . 'bodyPartial') }}
                    </body>
                </html>`;
    }

    /* Front/Start Page. */
    module.exports.getStartPage = function() {
        return `<ul>
                  <li><a href="home">Home</a></li>
                  <li style="float:right"><a class="active" href="login">Login</a></li>
                  <li style="float:right"><a class="active" href="singup">Sign-Up</a></li>
                </ul>`;
    }

    /* Sing-Up Page. */
    module.exports.getSignUp = function() {
        return `<p>Sign-Up Page</p>`;
    }

    /* Error 500. */
    module.exports.getError500 = function() {
        return `<h2>Error 500<h2>
                <p>PlaceHolder</p>`;
    }

    /* Error 400. */
    module.exports.getError400 = function() {
        return `<h2>Error 400<h2>
                <p>Placeholder</p>`;
    }

    /* Error 405. */
    module.exports.getError405 = function() {
        return `<h2>Error 405<h2>
                <p>Placeholder</p>`;
    }

}());
