-- Criação do campo para ser utilizado no sistema.

ALTER TABLE `libro`
ADD COLUMN `numero_emprestimos` INT NOT NULL DEFAULT 0;