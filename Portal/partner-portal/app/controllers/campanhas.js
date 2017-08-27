var fs = require("fs")
var request = require("request")

module.exports.getCampanhas = function (application, req, res) {
	var connection = application.config.dbConnection();
	var noticiasModel = new application.app.models.CampanhaDAO(connection);

	noticiasModel.getCampanhas(function (error, result) {				
		res.render("campanhas/index.ejs", { campanhas: result});
	});
}
module.exports.criarCampanha = function (application, req, res) {
	res.render("campanhas/criar-campanha", { validacao: {}, noticia: {} });
}

module.exports.salvar = function (application, req, res) {

	var campanha = req.body;

	campanha.idParceiro = 1

	/**
	 * Salvar imagem
	 * 
	 */

	

	var now = new Date().getTime()

	var s = JSON.stringify(campanha)
	console.log(s)
	
	var pathOrigem = req.files.arquivo.path
	var imageName = now + "_" + req.files.arquivo.originalFilename

	var pathDest = "./uploads/";
	fs.rename(pathOrigem, pathDest + imageName, function (err) {
		if (err) {
			console.log("Erro criado: "+err)
			res.status(500).json(err)
			return
		}
		campanha.fotopath = "http://172.13.0.77:3000/images/"+imageName
		
		var connection = application.config.dbConnection();
		var campanhasModel = new application.app.models.CampanhaDAO(connection);
		campanhasModel.criarCampanha(campanha, function (error, result) {
			sendNotification(campanha, function(result){
				res.redirect('/campanhas');
			})			
		});
	})
}

function sendNotification(campanha, callback)
{
	var notificationURL = "http://172.13.0.86:8080/gateway/rest/gateway/notifyAll"

	console.log("Enviando notification "+campanha)
	
	request({
        url: notificationURL,        
        method: 'POST',
        json: campanha
    }, function (error, response, body) {
        if (error) {
			console.log('Error sending notification: ', error)
			callback(false);
            //TODO - Verificar erro
        } else if (response.body.error) {
			console.log('Error: ', response.body.error)
			callback(false);
            //TODO - Verificar erro
        }else{
			console.log("Notificacao enviada com sucesso")
			callback(true);
		}
    })

}