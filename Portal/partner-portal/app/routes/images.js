var fs = require("fs")

module.exports = function(app)
{
    app.get('/images/:image', function (req, res) {
        var image = req.params.image
        console.log(image)
        var imageBinary = fs.readFile("./uploads/" + image, function (err, content) {
            if (err) {
                console.log("Erro ao ler arquivo")
            }
            res.writeHead(200, {
                'Content-type': 'image/png'
            })
            res.end(content)
        })
        console.log("Entrou no uploads")
    })
}