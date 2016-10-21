const fs = require('fs');
const parse = require('csv-parse');
const handlebars = require('handlebars');

fs.readFile('deutsche-bundesliga-2016-2017-fixtures.csv', 'utf-8', function(err, data) {
    if(err) throw err;

    const parseOptions = {
        "columns": true,
        "auto_parse": true,
        "auto_parse_data": true
    };

    parse(data, parseOptions, function(err, result) {
        if(err) throw err;
        console.log(result.length);
    });
});

fs.readFile('template-example.hbs', 'utf-8', function(err, templateStr) {
    if(err) throw err;

    const myTemplate = handlebars.compile(templateStr);
    console.log(myTemplate({name: "Alex"}));

});