var exec = require('cordova/exec');

module.exports.connect = function(arg0, success, error) {
    exec(success, error, 'Pinpad', 'connect', [arg0])
}

module.exports.initialize = function(arg0, success, error) {
    exec(success, error, 'Pinpad', 'initialize', [arg0])
}

module.exports.activate = function(arg0, success, error) {
    exec(success, error, 'Pinpad', 'activate', [arg0])
}
