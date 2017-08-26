function CampanhaDAO(connection){
	this._connection = connection;
}

CampanhaDAO.prototype.getCampanhas = function(callback){
	this._connection.query('select * from campanha order by id desc', callback);
}

CampanhaDAO.prototype.getCampanha = function(id_campanha, callback){
	console.log(id_campanha.id_campanha);
	this._connection.query('select * from campanha where id = ' + id_campanha.id_campanha, callback);
}

CampanhaDAO.prototype.criarCampanha = function(campanha, callback){
	this._connection.query('insert into campanha set ? ', campanha, callback)
}


module.exports = function(){
	return CampanhaDAO;
}