module.exports = function(application){

    application.get('/campanhas', function(req, res){
        application.app.controllers.campanhas.getCampanhas(application, req, res);
    })

	application.get('/criarCampanha', function(req, res){
		application.app.controllers.campanhas.criarCampanha(application, req, res);
	});

	application.post('/criarCampanha/salvar', function(req, res){
		application.app.controllers.campanhas.salvar(application, req, res);
	});
}