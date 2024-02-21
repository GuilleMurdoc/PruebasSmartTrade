import khttp.get

fun obtainText() {
    val response = get("http://localhost:8080/texto")

    if (response.statusCode == 200) {
        val texto = response.text
        println("Texto obtenido: $texto")
        return texto
    } else {
        println("Error al obtener el texto. Código de estado: ${response.statusCode}")
    }
}

