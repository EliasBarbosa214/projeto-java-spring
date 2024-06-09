$(document).ready(function() {
    carregarAutores();

    $('#formAdicionar').submit(function(event) {
        event.preventDefault();

        var nome = $('#nome').val();
        var nacionalidade = $('#nacionalidade').val();

        var novoAutor = {
            nomeCompleto: nome, // Corrigido para nome_completo
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
            autor: { id: autorId },
            titulo: titulo,
            genero: genero
        };

        $.ajax({
            type: 'POST',
            url: '/autor/' + autorId + '/libro/agregar',
            contentType: 'application/json',
            data: JSON.stringify(novoLibro),
            success: function(response) {
                alert(response);
                $('#autorId').val('');
                $('#tituloLivro').val('');
                $('#generoLivro').val('');
                carregarLivrosPorAutor(autorId);
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
                    var nomeCompleto = autor.nomeCompleto ? autor.nomeCompleto : 'Nome do autor não disponível';
                    var nacionalidade = autor.nacionalidad ? autor.nacionalidad : 'Nacionalidade não disponível';
                    $('#listaAutores').append('<li>ID: ' + autor.id + ' - Nome: ' + nomeCompleto + ' - Nacionalidade: ' + nacionalidade + ' <button onclick="eliminarAutor(' + autor.id + ')">Excluir</button> <button onclick="carregarLivrosPorAutor(' + autor.id + ')">Ver Livros</button></li>');
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
                    var disponibilidade = libro.disponivel ? 'Disponível' : 'Indisponível';
                    $('#listaLivros').append('<li>ID: ' + libro.id + ' - Título: ' + libro.titulo + ' - Gênero: ' + libro.genero + ' - Disponibilidade: ' + disponibilidade + '</li>');
                });
            } else {
                $('#listaLivros').append('<li>Este autor não tem livros cadastrados.</li>');
            }
        },
        error: function(error) {
            alert('Erro ao carregar lista de livros');
            console.log(error);
        }
    });
}

function emprestarLivro(idLivro) {
    $.ajax({
        type: 'POST',
        url: '/emprestimo/emprestar/' + idLivro,
        success: function(response) {
            alert(response);
        },
        error: function(error) {
            alert('Erro ao realizar empréstimo');
            console.log(error);
        }
    });
}

function devolverLivro(idLivro) {
    var idLivro = $('#idLivroEmprestimo').val();
    $.ajax({
        type: 'POST',
        url: '/emprestimo/devolver/' + idLivro,
        success: function(response) {
            alert(response);
        },
        error: function(error) {
            alert('Erro ao realizar devolução');
            console.log(error);
        }
    });
}
