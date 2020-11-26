var exec = require('cordova/exec');

module.exports.initialize = function(arg0, success, error) {
    exec(success, error, 'Pinpad', 'initialize', [arg0])
}

module.exports.activate = function(arg0, success, error) {
    exec(success, error, 'Pinpad', 'activate', [arg0])
}
