$(document).ready(function() {
    carregarAutores();

    $('#formAdicionar').submit(function(event) {
        event.preventDefault();

        var nome = $('#nome').val();
        var nacionalidade = $('#nacionalidade').val();

        var novoAutor = {
            nombre_completo: nome,
            nacionalidad: nacionalidade
        };

        $.ajax({
            type: 'POST',
            url: '/autor/agregar',
            contentType: 'application/json',
            data: JSON.stringify(novoAutor),
            success: function(response) {
                alert(response);
                $('#nome').val('');
                $('#nacionalidade').val('');
                carregarAutores();
            },
            error: function(error) {
                alert('Erro ao adicionar autor');
                console.log(error);
            }
        });
    });

    $('#formAdicionarLivro').submit(function(event) {
        event.preventDefault();

        var autorId = $('#autorId').val();
        var titulo = $('#tituloLivro').val();
        var genero = $('#generoLivro').val();

        var novoLibro = {
            titulo: titulo,
            genero: genero
        };

        $.ajax({
            type: 'POST',
            url: '/autor/' + autorId + '/libros',
            contentType: 'application/json',
            data: JSON.stringify(novoLibro),
            success: function(response) {
                alert(response);
                $('#autorId').val('');
                $('#tituloLivro').val('');
                $('#generoLivro').val('');
                carregarLibrosPorAutor(autorId);
            },
            error: function(error) {
                alert('Erro ao adicionar livro');
                console.log(error);
            }
        });
    });
});

function carregarAutores() {
    $.ajax({
        type: 'GET',
        url: '/autor/get',
        success: function(response) {
            $('#listaAutores').empty();
            if (response && response.length > 0) {
                response.forEach(function(autor) {
                    $('#listaAutores').append('<li>' + autor.nombre_completo + ' - ' + autor.nacionalidad + ' <button onclick="eliminarAutor(' + autor.id + ')">Excluir</button> <button onclick="carregarLivrosPorAutor(' + autor.id + ')">Ver Libros</button></li>');
                });
            } else {
                $('#listaAutores').append('<li>Nenhum autor encontrado</li>');
            }
        },
        error: function(error) {
            alert('Erro ao carregar lista de autores');
            console.log(error);
        }
    });
}

function eliminarAutor(id_autor) {
    $.ajax({
        type: 'DELETE',
        url: '/autor/eliminar/' + id_autor,
        success: function(response) {
            alert(response);
            carregarAutores();
        },
        error: function(error) {
            alert('Erro ao excluir autor');
            console.log(error);
        }
    });
}

function carregarLivrosPorAutor(autorId) {
    $.ajax({
        type: 'GET',
        url: '/autor/' + autorId + '/libros',
        success: function(response) {
            $('#listaLivros').empty();
            if (response && response.length > 0) {
                response.forEach(function(libro) {
                    $('#listaLivros').append('<p>Título: ' + libro.titulo + ', Gênero: ' + libro.genero + '</p>');
                });
            } else {
                $('#listaLivros').append('<p>Este autor não tem libros cadastrados.</p>');
            }
        },
        error: function(error) {
            alert('Erro ao carregar lista de libros');
            console.log(error);
        }
    });
}
