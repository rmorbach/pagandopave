module.exports.index = function (application, req, res) {
	res.render("login/index", {validacao:null, dadosForm:{usuario:"admin"}});
}

module.exports.autenticar = function(application, req, res){
	res.redirect('/campanhas');
}