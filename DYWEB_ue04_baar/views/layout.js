const hbs = require('handlebars');

const layout = hbs.compile(`<!DOCTYPE html>
    <html lang="en">
        <head>
            <meta charset="UTF-8">
            <title>{{title}} - AboutMe</title>
            <style>
                html {
                    font-family: sans-serif;
                    height: 100%;
                }
                body {
                    margin: 0;
                    box-sizing: border-box;
                    height: 100%;
                }
                nav {
                    display: inline;
                }
                li {
                    margin-bottom: 1em;
                }
                .centered {
                    text-align: center;
                }
                .subtle {
                    color: #999;
                }
                .small {
                    font-size: 0.875em;
                }
                .container {
                    position: relative;
                    margin: 0 0 0 1em;
                    max-width: 1024px;
                }
                #header, #header a {
                    background-color: #1a75ff;
                    color: white;
                    padding: 2em 0;
                }
                .cta {
                    text-align: center;
                }
                .cta a {
                    background-color: #D36;
                    border-radius: 5px;
                    color: #FFF;
                    display: inline-block;
                    font-size: 2em;
                    padding: 0.5em 2em;
                    text-decoration: none;
                    transition: background-color 0.3s ease-in;
                }
                .cta a:hover {
                    text-decoration: none;
                    background-color: #C25;
                    transition: background-color 0.3s ease-in; 
                }
            </style>
        </head>
        <body>
            <div id="header">
                <div class="container">
                    <span>AboutMe</span> &middot;
                    <nav><a href="/">Home</a></nav>
                </div>
            </div>
            <div class="container">
                {{> (lookup . 'bodyPartial') }}
            </div>
        </body>
    </html>`);

// We export the already compiled layout template. In fact, _layout_ is a function and
// other modules can call the function with the template data data they like.
module.exports = layout;