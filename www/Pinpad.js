var exec = require('cordova/exec');

module.exports.add = function(arg0, success, error) {
    exec(success, error, 'Pinpad', 'add', [arg0])
}

module.exports.activate = function(arg0, success, error) {
    exec(success, error, 'Pinpad', 'activate', [arg0])
}
