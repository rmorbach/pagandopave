var mysql = require('mysql');

var connMySQL = function(){
	return mysql.createConnection({
		host : 'localhost',
		user : 'pagandopave',
		password : 'pagandopave',
		database : 'pagandopave'
	});
}

module.exports = function () {
	return connMySQL;
}