-- MySQL Script generated by MySQL Workbench
-- Sat May 14 14:48:14 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema tb_temas
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema tb_temas
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `tb_temas` DEFAULT CHARACTER SET utf8 ;
USE `tb_temas` ;

-- -----------------------------------------------------
-- Table `tb_temas`.`tb_temas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tb_temas`.`tb_temas` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `descriçao` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tb_temas`.`tb_usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tb_temas`.`tb_usuario` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NOT NULL,
  `usuario` VARCHAR(255) NOT NULL,
  `senha` VARCHAR(255) NOT NULL,
  `foto` VARCHAR(255) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tb_temas`.`tb_postagens`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tb_temas`.`tb_postagens` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(255) NOT NULL,
  `texto` VARCHAR(255) NOT NULL,
  `data` DATE NULL,
  `tb_temas_id` BIGINT NULL,
  `tb_usuario_id` BIGINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tb_postagens_tb_temas_idx` (`tb_temas_id` ASC) VISIBLE,
  INDEX `fk_tb_postagens_tb_usuario1_idx` (`tb_usuario_id` ASC) VISIBLE,
  CONSTRAINT `fk_tb_postagens_tb_temas`
    FOREIGN KEY (`tb_temas_id`)
    REFERENCES `tb_temas`.`tb_temas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_postagens_tb_usuario1`
    FOREIGN KEY (`tb_usuario_id`)
    REFERENCES `tb_temas`.`tb_usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

SELECT * from tb_temas inner join tb_postagem
on tb_temas.id = tb_postagem.tb_temas_id WHERE tb_temas.descricao = "java";
