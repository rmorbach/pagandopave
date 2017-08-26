var mysql = require('mysql');

var connMySQL = function(){
	return mysql.createConnection({
		host : '172.13.0.71',
		user : 'pagandopave',
		password : 'pagandopave',
		database : 'pagandopave'
	});
}

module.exports = function () {
	return connMySQL;
}