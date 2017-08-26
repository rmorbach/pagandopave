var request = require('request')

var notificationURL = "http://172.13.0.86:8080/gateway/rest/gateway/notifyAll"

var campanha = {
    "titulo": "minha campanha"
}
console.log("Enviando notification " + campanha)

request({
    url: notificationURL,
    method: 'POST',
    json: campanha
}, function (error, response, body) {
    console.log(response.httpStatus)
    if (error) {
        console.log('Error sending notification: ', error)
        //callback(false);
        //TODO - Verificar erro
    } else if (response.body.error) {
        console.log('Error: ', response.body.error)
        //callback(false);
        //TODO - Verificar erro
    } else {
        //callback(true);
    }
})
